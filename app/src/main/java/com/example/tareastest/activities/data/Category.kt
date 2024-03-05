package com.example.tareastest.activities.data

import com.example.tareastest.R

data class Category(var categ:Int=0,var image:Int=0) {

    fun iniciate ():List<Category>{

        var lisCat:MutableList<Category> = mutableListOf<Category>()

        val work=Category(R.string.Work, R.drawable.work_36)
        lisCat.add(work)
        val home=Category(R.string.Home, R.drawable.home_36)
        lisCat.add(home)
        val shopp=Category(R.string.Shopp, R.drawable.shoping_36)
        lisCat.add(shopp)
        val travel=Category(R.string.Travel, R.drawable.travel_36)
        lisCat.add(travel)
        val urgent=Category(R.string.Urgent, R.drawable.urgent_36)
        lisCat.add(urgent)

        return lisCat
    }

}

