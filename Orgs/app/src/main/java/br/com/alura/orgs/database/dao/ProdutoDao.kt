package br.com.alura.orgs.database.dao

import androidx.room.*
import br.com.alura.orgs.model.Produto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos(): Flow<List<Produto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(vararg produto: Produto)

    @Delete
    suspend fun remove(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :id")
    fun buscaPorId(id: Long): Flow<Produto?>

//CONFIGURAÇÃO DE ORDENAÇÃO --> NÃO ESTÁ SENDO USADO MAIS

//    @Query("SELECT * FROM Produto ORDER BY nome ASC")
//    fun buscaTodosOrdenadorPorNomeAsc(): List<Produto>
//
//    @Query("SELECT * FROM Produto ORDER BY nome DESC")
//    fun buscaTodosOrdenadorPorNomeDesc(): List<Produto>
//
//    @Query("SELECT * FROM Produto ORDER BY descricao ASC")
//    fun buscaTodosOrdenadorPorDescricaoAsc(): List<Produto>
//
//    @Query("SELECT * FROM Produto ORDER BY descricao DESC")
//    fun buscaTodosOrdenadorPorDescricaoDesc(): List<Produto>
//
//    @Query("SELECT * FROM Produto ORDER BY valor ASC")
//    fun buscaTodosOrdenadosPorValorAsc(): List<Produto>
//
//    @Query("SELECT * FROM Produto ORDER BY valor DESC")
//    fun buscaTodosOrdenadosPorValorDesc(): List<Produto>

}