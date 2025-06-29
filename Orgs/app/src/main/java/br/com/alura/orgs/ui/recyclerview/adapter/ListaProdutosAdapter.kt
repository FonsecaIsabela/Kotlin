package br.com.alura.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.databinding.ProdutoItemBinding
import br.com.alura.orgs.extensions.tentaCarregarImagem
import br.com.alura.orgs.model.Produto
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

        private val produtos = produtos.toMutableList()

        class ViewHolder(private val binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root){

            fun vincula(produto: Produto) {
                val nome = binding.produtoItemNome
                nome.text = produto.nome
                val descricao = binding.produtoItemDescricao
                descricao.text = produto.descricao
                val valor = binding.produtoItemValor
                val valorEmMoeda: String =
                    formataParaMoedaBrasileira(produto.valor)
                valor.text = valorEmMoeda

                val visibilidade = if (produto.imagem != null) {
                    View.VISIBLE
                } else {
                    View.GONE
                }

                binding.produtoItemImageView.visibility = visibilidade
                binding.produtoItemImageView.tentaCarregarImagem()

            }

            private fun formataParaMoedaBrasileira(valor: BigDecimal) : String {
                val formatador: NumberFormat = NumberFormat
                    .getNumberInstance(Locale("pt", "br"))
                return formatador.format(valor)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflate, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = produtos.size

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
