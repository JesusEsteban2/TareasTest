package com.example.tareastest.activities.data

import android.app.AlertDialog
import android.content.Context


/**
 * Clase para crear un dialogo Emergente. se le puede añadir un boton positivo y uno negativo.
 * El último método a llamar es build construye y muestra el dialogo.
 * @constructor Construye el builder y pre inicializa el dialogo
 * @param me Texto a mostrar en el dialogo.
 * @param ti Titulo a mostrar en el dialogo.
 */
class Emergente (var me:String,var ti:String,context:Context){

    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
    lateinit var dialog: AlertDialog

    init {

        create()

    }

    private fun create(){

        builder
            .setMessage(me)
            .setTitle(ti)
        setNegBotDefault("Cancel")


    }

    fun build() {

        dialog = builder.create()
        dialog.show()
    }


    fun setNegBotDefault(te:String){
        builder.setNegativeButton(te) { dialog, which ->
            dialog.cancel()
        }
    }

    fun cancel(){
        dialog.cancel()
    }
}

