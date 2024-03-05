package com.example.tareastest.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tareastest.R
import com.example.tareastest.activities.adapters.TareasAdapter
import com.example.tareastest.activities.data.DaoTask
import com.example.tareastest.activities.data.Tarea
import com.example.tareastest.databinding.ActivityMainBinding

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

        adapt=TareasAdapter(dataSet, { onDelClickListener(it)})

        binding.reciclerV.adapter = adapt

        binding.reciclerV.layoutManager = GridLayoutManager(this, 1)

    }

    private fun onDelClickListener(pos: Int){

        DaoTask(this).delete(dataSet[pos].id)
        dataSet = DaoTask(this).queryAll()
        adapt.updateItems(dataSet)

    }
}