package com.example.tareastest.activities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.SimpleAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.tareastest.R
import com.example.tareastest.activities.data.Category
import com.example.tareastest.activities.data.Tarea
import com.google.android.material.snackbar.Snackbar


class CategoryAdapter (private val context: Context, val lisCat: List<Category>,var ta:Tarea):BaseAdapter() {

    override fun getCount(): Int {
        return lisCat.size
    }

    override fun getItem(position:Int): Category {
        return lisCat[position]
    }

    override fun getItemId(position: Int): Long {
        //val t: Toast = Toast.makeText(this.context, "Has presionado la categoría $position", Toast.LENGTH_LONG)
        //t.show()
        //ta.cat=position
        return position.toLong()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        var vie=view
        if (vie == null) {
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            vie = inflater.inflate(R.layout.category_layout, viewGroup, false)
        }
        val imagen = vie!!.findViewById<View>(R.id.catImage) as ImageView
        val texto = vie!!.findViewById<View>(R.id.cattext) as TextView

        imagen.setImageResource(lisCat[position].image)
        if (ta.cat==position) {
            imagen.setBackgroundColor(imagen.context.getColor(R.color.mark))
        } else {
            imagen.setBackgroundColor(imagen.context.getColor(R.color.white))
        }
        texto.setText(vie!!.context.getString(lisCat[position].categ).toString())
        return vie
    }

    fun updateView (position: Int) {

    }

}