package com.example.tareastest.activities.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tareastest.R
import com.example.tareastest.activities.adapters.CategoryAdapter
import com.example.tareastest.activities.data.Category
import com.example.tareastest.databinding.ActivityDetailBinding
import com.example.tareastest.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var catSelect = binding.catSelect
        var lisCat = Category().iniciate()

        catSelect.adapter = CategoryAdapter(this, lisCat)
    }
}