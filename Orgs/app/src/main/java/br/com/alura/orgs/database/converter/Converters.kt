package br.com.alura.orgs.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {

    @TypeConverter
    fun deString(valor: String?): BigDecimal {
        return valor?.let { BigDecimal(it) } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun bigDecimalParaString(valor: BigDecimal?): String? {
        return valor?.toPlainString()
    }
}