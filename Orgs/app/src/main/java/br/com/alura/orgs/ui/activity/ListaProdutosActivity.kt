package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.alura.orgs.R
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.databinding.ActivityListaProdutosBinding
import br.com.alura.orgs.model.Produto
import br.com.alura.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


// heranca
class ListaProdutosActivity : AppCompatActivity() {

    private val adapter = ListaProdutosAdapter(context = this)
    // Define o adaptador do RecyclerView, passando o contexto

    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    private val produtoDao by lazy {
        var db = AppDatabase.instancia(this)
        db.produtoDao()
    }

    private val usuarioDao by lazy {
        AppDatabase.instancia(this).usuarioDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Lista de produtos"
        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            launch {
                produtoDao.buscaTodos().collect { produtos ->
                    adapter.atualiza(produtos)
                }
            }
            intent.getStringExtra("CHAVE_USUARIO_ID")?.let { usuarioId ->
                usuarioDao.buscaPorId(usuarioId).collect {
                    Log.i("ListaProdutos", "onCreate: $it")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ordenar_produtos, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        lifecycleScope.launch {
            val produtosOrdenados: List<Produto>? = when (item.itemId) {
                R.id.menu_ordenar_nome_asc ->
                    produtoDao.buscaTodosOrdenadorPorNomeAsc()

                R.id.menu_ordenar_nome_desc ->
                    produtoDao.buscaTodosOrdenadorPorNomeDesc()

                R.id.menu_ordenar_descricao_asc ->
                    produtoDao.buscaTodosOrdenadorPorDescricaoAsc()

                R.id.menu_ordenar_descricao_desc ->
                    produtoDao.buscaTodosOrdenadorPorDescricaoDesc()

                R.id.menu_ordenar_valor_asc ->
                    produtoDao.buscaTodosOrdenadosPorValorAsc()

                R.id.menu_ordenar_valor_desc ->
                    produtoDao.buscaTodosOrdenadosPorValorDesc()

                R.id.menu_sem_ordenar ->
                    produtoDao.buscaTodos().first()

                else -> null
            }
            produtosOrdenados?.let {
                adapter.atualiza(it)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configuraFab() {
        val fab = binding.activityListaProdutosFloatingActionButton
        // Obtém a referência para o botão flutuante (FloatingActionButton) no layout
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        // Cria uma intent para abrir a tela do formulário de produto
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recycleView = binding.activityListaProdutosRecyclerView
        // Escreve no Log do Android todos os produtos retornados pelo DAO
        recycleView.adapter = adapter
        adapter.quandoClicaNoItem = {
            val intent = Intent(this, DetalhesProdutoActivity::class.java).apply {
                // envio do produto por meio do extra
                putExtra(CHAVE_PRODUTO_ID, it.id)
            }
            startActivity(intent)
        }
        adapter.quandoClicaNoEditar = {
            Log.i("ListaProdutosActivity", "Clicou no editar $it")
        }
        adapter.quandoClicaNoRemover = {
            Log.i("ListaProdutosActivity", "Clicou no remover $it")
        }
    }
}