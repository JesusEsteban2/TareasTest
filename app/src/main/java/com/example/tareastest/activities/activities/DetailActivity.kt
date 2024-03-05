package com.example.tareastest.activities.activities

import android.os.Bundle
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
    lateinit var lisCa:List<Category>
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
        } else {
            ta = Tarea(-1, "", false, 0)
        }


        binding.Doit.setOnClickListener({onClickChip()})

        if (ta.doit) {
            binding.Doit.text = this.getString(R.string.Done)
            binding.Doit.chipIcon = this.getDrawable(R.drawable.checkmark)
        } else {
            binding.Doit.text = this.getString(R.string.Pending)
            binding.Doit.chipIcon = this.getDrawable(R.drawable.uncheck)
            binding

            // Definir el adapter para el reciclerview

            //Prepare list of categories pass to the adapter
            var catSelect = binding.catSel
            catSelect.adapter = CategoryAdapter(this, lisCa)

        }


    }

    fun onClickChip () {

        if (ta.doit) {
            ta.doit = false
            binding.Doit.text = this.getString(R.string.Pending)
            binding.Doit.chipIcon = this.getDrawable(R.drawable.uncheck)
        } else {
            ta.doit = true
            binding.Doit.text = this.getString(R.string.Done)
            binding.Doit.chipIcon = this.getDrawable(R.drawable.checkmark)
        }

    }
}