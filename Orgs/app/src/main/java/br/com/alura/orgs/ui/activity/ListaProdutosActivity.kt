package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.DAO.ProdutoDAO
import br.com.alura.orgs.databinding.ActivityListaProdutosBinding
import br.com.alura.orgs.ui.dialog.FormularioImagemDialog
import br.com.alura.orgs.ui.recyclerview.adapter.ListaProdutosAdapter


// heranca
class ListaProdutosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaProdutosBinding

    private val dao = ProdutoDAO()

    // Cria uma instância do DAO que fornece os dados dos produtos
    private val adapter = ListaProdutosAdapter(this, produtos = dao.buscaTodos())
    // Define o adaptador do RecyclerView, passando o contexto e a lista de produtos buscados do DAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Formulário"
        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
    }

    private fun configuraFab() {
        val fab = binding.activityListaProdutosFloatingActionButton
        // Obtém a referência para o botão flutuante (FloatingActionButton) no layout
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

//    private fun vaiParaDetalhesProduto() {
//        val intent = Intent(this, DetalhesProdutoActivity::class:java)
//        startActivity(intent)
//    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        // Cria uma intent para abrir a tela do formulário de produto
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recycleView = binding.activityListaProdutosRecyclerView
        // Escreve no Log do Android todos os produtos retornados pelo DAO
        recycleView.adapter = adapter
    }

}