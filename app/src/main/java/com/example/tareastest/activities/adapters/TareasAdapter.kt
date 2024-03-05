package com.example.tareastest.activities.adapters

import android.content.DialogInterface.OnClickListener
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.tareastest.R
import com.example.tareastest.activities.data.Tarea
import com.example.tareastest.databinding.ItemLayoutBinding
import android.view.LayoutInflater
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.get
import java.util.Locale.Category

class TareasAdapter(var dataSet: List<Tarea>,
                    val onDelClickListener: (position:Int) -> Unit ) :
    RecyclerView.Adapter<TareasAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(dataSet[position])

        //Listener para el icono borrar
        holder.binding.delImage.setOnClickListener({onDelClickListener(position)})

        // Listener para toda la línea.
        //holder.itemView.setOnClickListener { onDelClickListener(position) }
    }

    override fun getItemCount(): Int = dataSet.size

    fun updateItems(results: List<Tarea>) {
        dataSet = results
        notifyDataSetChanged()


    }

    class ViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun render(tarea: Tarea) {
            var context=binding.root.context
            var icon: Drawable? = context.getDrawable(R.drawable.uncheck)
            if (tarea.doit) {
                icon = context.getDrawable(R.drawable.checkmark)
            }
            binding.taskChip.chipIcon = icon
            binding.taskText.text = tarea.task
            binding.catImage.setImageDrawable(context.getDrawable(R.drawable.shoping_36))
        }

        fun update (dataSet: List<Tarea>,adapter: TareasAdapter){
            adapter.updateItems(dataSet)
        }
    }
}