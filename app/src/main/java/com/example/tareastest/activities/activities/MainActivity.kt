package com.example.tareastest.activities.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tareastest.R
import com.example.tareastest.activities.adapters.TareasAdapter
import com.example.tareastest.activities.data.DaoTask
import com.example.tareastest.activities.data.Tarea
import com.example.tareastest.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var dataSet: List<Tarea>
    lateinit var adapt:TareasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataSet = DaoTask(this).queryAll()

        // Definir el adapter para el reciclerview
        adapt=TareasAdapter(dataSet, { onDelClickListener(it)})

        binding.reciclerV.adapter = adapt
        binding.reciclerV.layoutManager = GridLayoutManager(this, 1)

        //Boton flotante para añadir
        val fab = binding.floatingAddButton
        fab.setOnClickListener { view ->
            val context:Context=this
            val intent=Intent(context, DetailActivity::class.java)
            intent.putExtra("MODE","-1" )
            context.startActivity(intent)
        }

    }

    private fun onDelClickListener(pos: Int){

        DaoTask(this).delete(dataSet[pos].id)
        dataSet = DaoTask(this).queryAll()
        adapt.updateItems(dataSet)

    }
}