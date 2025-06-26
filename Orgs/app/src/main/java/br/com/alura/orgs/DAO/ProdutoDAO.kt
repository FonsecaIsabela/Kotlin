package br.com.alura.orgs.DAO

import br.com.alura.orgs.model.Produto
import java.math.BigDecimal
//import kotlin.collections.mutableListOf

class ProdutoDAO {

    fun adiciona(produto: Produto) { //funcao para adicionar produto
        produtos.add(produto)
    }

    fun buscaTodos() : List<Produto> {
        return produtos.toList()  //retornando lista de produtos
    }
    //quando quiser um repositório global compartilhado entre todas as
    //instâncias (ex: catálogo de produtos da loja).
    companion object {
        val produtos = mutableListOf<Produto>(
            Produto(nome = "Salada",
               descricao = "Morango, uva, laranja",
                valor = BigDecimal("19.88")
            )
        ) //criando lista
    }
}