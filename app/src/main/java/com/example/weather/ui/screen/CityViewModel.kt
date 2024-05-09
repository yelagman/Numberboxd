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

    fun editItem(editedCity: cityItem) {
        val index = _cityList.indexOfFirst { it == editedCity } // Find the index of the edited city item in the list
        if (index != -1) { // Check if city item is found in the list
            _cityList[index] = editedCity // Replace the old city item with the edited one in the list
        }
    }

    fun getCity(cityItem: cityItem): String{
        return cityItem.city
        }
}