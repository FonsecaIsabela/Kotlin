package br.com.alura.orgs.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import kotlinx.parcelize.Parcelize

@Entity
// implementação do Parcelable com o plugin parcelizeAdd commentMore actions
@Parcelize
data class Produto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val imagem: String? = null

): Parcelable

