package com.example.weather.ui.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.cityItem
import kotlinx.coroutines.launch

class CityViewModel: ViewModel() {
    private var _cityList =
        mutableStateListOf<cityItem>()

    fun addToCityList(cityItem: cityItem) {
        _cityList.add(cityItem)
    }


    fun removeItem(cityItem: cityItem) {
        _cityList.remove(cityItem)
    }

    fun clearAllItems() {
        _cityList.clear()
    }

    fun getAllitems(): List<cityItem> {
        return _cityList
    }

    fun editItem(originalCity: cityItem, editedCity: cityItem) {
        val index = _cityList.indexOf (originalCity) // Find the index of the original city item in the list
        _cityList[index] = editedCity // Replace the old city item with the edited one in the list
        }

    fun getCity(cityItem: cityItem): String{
        return cityItem.city
        }
}