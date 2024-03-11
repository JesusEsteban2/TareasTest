package com.example.tareastest.activities.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ContentInfo
import android.view.DragAndDropPermissions
import android.view.DragEvent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tareastest.R
import com.example.tareastest.activities.adapters.TareasAdapter
import com.example.tareastest.activities.data.DaoTask
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
        adapt=TareasAdapter(dataSet, { onDelClickListener(it)})

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
        fab.onDragEvent(event: DragEvent -> {
            onDragEvent(event: DragEvent)})

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

            R.id.editar -> {
                    exitProcess(0)
                    return true
            }
            R.id.nuevo -> {
                    newTask()
                    return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun onDragEvent(event: DragEvent): Boolean {
        if (mListenerInfo == null || mListenerInfo.mOnReceiveContentListener == null) {
            return false
        }
        // Accept drag events by default if there's an OnReceiveContentListener set.
        if (event.action == DragEvent.ACTION_DRAG_STARTED) {
            return true
        }
        if (event.action == DragEvent.ACTION_DROP) {
            val permissions: DragAndDropPermissions = DragAndDropPermissions.obtain(event)
            if (permissions != null) {
                permissions.takeTransient()
            }
            val payload: ContentInfo =
                ContentInfo.Builder(event.clipData, ContentInfo.SOURCE_DRAG_AND_DROP)
                    .setDragAndDropPermissions(permissions)
                    .build()
            val remainingPayload: ContentInfo = performReceiveContent(payload)
            // Return true unless none of the payload was consumed.
            return remainingPayload != payload
        }
        return false
    }

}

