package com.example.weather.data

data class cityItem(
    val city: String,
    val showRating:String,
    var showcategory:showCategory,
    var isFavorite: Boolean
)

enum class showCategory {
    CurrentlyWatching, Watchlist;

//    fun getIcon(): Int {
//        return if (this == FRUIT) R.drawable.fruit
//        else if (this==MEAT) R.drawable.meat
//        else R.drawable.bread
//    }
}
