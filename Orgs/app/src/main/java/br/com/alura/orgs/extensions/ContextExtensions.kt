package br.com.alura.orgs.extensions

import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.vaiPara(clazz: Class<*>, configure: Intent.() -> Unit = {}) {
    Intent(this, clazz).apply {
        configure() // Aplica as configurações do lambda
        startActivity(this)
    }
}

fun Context.toast(mensagem: String) {
    Toast.makeText(
        this,
        mensagem,
        Toast.LENGTH_SHORT
    ).show()
}