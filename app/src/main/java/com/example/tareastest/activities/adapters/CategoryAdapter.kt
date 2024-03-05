package com.example.tareastest.activities.adapters

import com.example.tareastest.R
import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import com.example.tareastest.activities.data.Category


class CategoryAdapter (private val context: Context, val lisCat: List<Category>):BaseAdapter() {

    override fun getCount(): Int {
        return lisCat.size
    }

    override fun getItem(position:Int): Category {
        return lisCat[position]
    }

    override fun getItemId(position: Int): Long {
        return position as Long
    }

    override fun getView(position: Int, view: View, viewGroup: ViewGroup): View {
            var view = view
            if (view == null) {
                val inflater = context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflater.inflate(R.layout.category_layout, viewGroup, false)
            }
            val imagen = view.findViewById<View>(R.id.catImage) as ImageView
            val texto = view.findViewById<View>(R.id.cattext) as TextView

            imagen.setImageResource(lisCat[position].image)
            texto.setText(view.context.getString(lisCat[position].categ).toString())
            return view
        }

}