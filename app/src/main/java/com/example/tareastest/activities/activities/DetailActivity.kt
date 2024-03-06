package com.example.tareastest.activities.activities

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.tareastest.R
import com.example.tareastest.activities.adapters.CategoryAdapter
import com.example.tareastest.activities.data.Category
import com.example.tareastest.activities.data.DaoTask
import com.example.tareastest.activities.data.Tarea
import com.example.tareastest.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    lateinit var lisCa: List<Category>
    lateinit var ta: Tarea

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // init category list
        lisCa = Category().iniciate()

        //Get binding
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get extra -1 new record mode , for mode >-1 edit mode
        val mode = intent.getIntExtra("MODE", -1)

        if (mode > -1) {
            var dataSet = DaoTask(this).queryAll()
            ta = dataSet[mode]
            displayAll()
        } else {
            ta = Tarea(-1, "", false, 0)
        }

        //Visualiza y carga el Onclick del chip
        displayChip()
        binding.Doit.setOnClickListener({ onClickChip() })

        //Asigna el onclick del Boton.
        binding.saveButton.setOnClickListener({
            ta.task=binding.detailTask.text.toString()
            if (mode>-1) {DaoTask(this).update(ta)} else {DaoTask(this).insert(ta)}
            finish()
        })

        //Prepare list of categories pass to the adapter
        var catSelect = binding.catSel
        catSelect.adapter = CategoryAdapter(this, lisCa,ta)

        catSelect.setOnItemClickListener { parent, view, position, id ->
            ta.cat = position
            (catSelect.adapter as CategoryAdapter).notifyDataSetChanged()
        }
    }

    fun onClickChip() {

        if (ta.doit) {
            ta.doit = false
        } else {
            ta.doit = true
        }
        displayChip()
    }

    fun displayChip() {
        if (ta.doit) {
            binding.Doit.text = this.getString(R.string.Done)
            binding.Doit.chipIcon = this.getDrawable(R.drawable.checkmark)
        } else {
            binding.Doit.text = this.getString(R.string.Pending)
            binding.Doit.chipIcon = this.getDrawable(R.drawable.uncheck)
        }
    }

    fun displayAll() {
        binding.detailTask.setText(ta.task)
        displayChip()
        // binding.catSel.
    }

}