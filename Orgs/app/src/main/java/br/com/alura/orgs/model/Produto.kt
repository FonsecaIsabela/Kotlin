package br.com.alura.orgs.model

import android.os.Parcelable
import java.math.BigDecimal
import kotlinx.parcelize.Parcelize

// implementação do Parcelable com o plugin parcelizeAdd commentMore actions
@Parcelize
data class Produto(
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val imagem: String? = null

): Parcelable

