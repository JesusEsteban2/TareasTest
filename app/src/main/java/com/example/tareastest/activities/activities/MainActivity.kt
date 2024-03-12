package com.example.tareastest.activities.activities

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tareastest.R
import com.example.tareastest.activities.adapters.TareasAdapter
import com.example.tareastest.activities.data.DaoTask
import com.example.tareastest.activities.data.Emergente
import com.example.tareastest.activities.data.Tarea
import com.example.tareastest.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    // Pre declaración para poder usuarlos en metodos fuera de OnCrate.
    private lateinit var binding: ActivityMainBinding
    lateinit var dataSet: List<Tarea>
    lateinit var adapt:TareasAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Preparación de binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inicialización de dataSet de la BBDD
        dataSet = DaoTask(this).queryAll()

        // Definir el adapter para el reciclerview
        adapt = TareasAdapter(dataSet, { onDelClickListener(it) })

        //Muestra la opcion atras en el menú
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Define el adapter para el reciclerview
        binding.reciclerV.adapter = adapt
        binding.reciclerV.layoutManager = GridLayoutManager(this, 1)

        //Boton flotante para añadir
        val fab = binding.floatingAddButton
        fab.setOnClickListener { view ->
            newTask()
        }

        // Boton flotante cambiar de sitio con Longclick
        fab.setOnLongClickListener { view ->
            // Mover botón fab a la derecha o izquierda de la Pantalla.
            var l: Int = fab.left
            var r: Int = fab.right
            var t: Int = fab.top
            var b: Int = fab.bottom
            var wi: Int = r - l
            var hi: Int = b - t
            if (l !=25){
                Log.i("POS","Pos l= $l")
                Log.i("POS","Pos r= $r")
                Log.i("POS","Pos t= $t")
                Log.i("POS","Pos b= $b")
                l=25
            } else {
                l=900
            }
            r = l + wi
            fab.layout(l, t, r, b)
           true
        }

    }

    //Funciób para ir a añadir nueva tarea.
    fun newTask() {

            val context:Context=this
            val intent=Intent(context, DetailActivity::class.java)
            intent.putExtra("MODE",-1 )
            context.startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        dataSet = DaoTask(this).queryAll()
        adapt.updateItems(dataSet)

    }

    private fun onDelClickListener(pos: Int){

        DaoTask(this).delete(dataSet[pos].id)
        dataSet = DaoTask(this).queryAll()
        adapt.updateItems(dataSet)

    }

    // Inflado de menú.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.tarea_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.nuevo -> {
                newTask()
                return true
            }

            R.id.salir -> {
                    exitProcess(0)
                    return true
            }

            R.id.acercade -> {
                val dialogo= Emergente("rollo de  texto","rollo de título",binding.root.context)
                dialogo.build()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


}

