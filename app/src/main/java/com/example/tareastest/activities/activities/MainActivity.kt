package com.example.tareastest.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tareastest.R
import com.example.tareastest.activities.data.DatabaseHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var db= DatabaseHelper(this)
        db.writableDatabase.insert()
    }
}