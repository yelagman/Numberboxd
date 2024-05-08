package com.example.weather.ui.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.R
import com.example.weather.data.cityItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityScreen(
    cityViewModel: CityViewModel = viewModel(),
    onNavigateToMain: (String) -> Unit
){

    var city by rememberSaveable {
        mutableStateOf("")
    }
    var cityErrorState by rememberSaveable { mutableStateOf(false) }

    var showAddDialog by rememberSaveable {
        mutableStateOf(false)
    }
    fun validate() {
        cityErrorState = city.isEmpty()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "NumberBoxd")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE784BA)
                ),
                actions = {
                    IconButton(onClick = {
                        cityViewModel.clearAllItems()
                    }) {
                        Icon(Icons.Filled.Delete, null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Filled.Add, "Add Sjow")
            }
        },
        content = {

            Column(
                modifier = Modifier.padding(it)
            ) {
                LazyRow(modifier = Modifier.fillMaxSize()) {
                    items(cityViewModel.getAllitems()) {
                        CityCard(it,
                            onRemoveItem = { cityViewModel.removeItem(it) },
                            onNavigateToMain
                        )
                    }
                }
            }
            if (showAddDialog) {
                AddCityDialog(cityViewModel,
                    onDismiss = {
                        showAddDialog = false
                    })
            }


        }
    )
}

@Composable
fun AddCityDialog(
    cityViewModel: CityViewModel,
    onDismiss: () -> Unit
){
    var city by rememberSaveable {
        mutableStateOf("")
    }
    var showRating by rememberSaveable {
        mutableStateOf("")
    }
    var cityErrorState by rememberSaveable { mutableStateOf(false) }

    fun validate() {
        cityErrorState = city.isEmpty()
    }


    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
        ){
            Column(
                Modifier.padding(16.dp)
            ) {

        OutlinedTextField(
            value = city,
            onValueChange = { city = it
                validate()},
            label = { Text(text = stringResource(R.string.enter_city_name)) },
            isError = cityErrorState)

                OutlinedTextField(
                    value = showRating,
                    onValueChange = { showRating = it },
                    label = { Text(text = "How many stars?") }
                )

        val inputErrorState = cityErrorState

        if (inputErrorState) {
            Text(
                text = when {
                    cityErrorState -> stringResource(R.string.city_cannot_be_empty)
                    else -> ""
                },
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
// FOR CHECKING IF FAVORITE OR NOT
//                Checkbox(
//                    checked = cityItem.isFavorite,
//                    onCheckedChange = { oncityCheckChange(it) }
//                )


                //FOR ADDING SHOWS TO LIST

//                SpinnerSample(
//                    list = showCategory.values().toList(),
//                    preselected = selectedCategory,
//                    onSelectionChanged = { selectedCategory = it },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 10.dp)
//                )

        Row {

            Button(
                enabled = !inputErrorState,
                onClick = {
                    if (city.isNotEmpty()) {
                        cityViewModel.addToCityList(
                            cityItem(
                                city = city,
                            )
                        )
                    }
                onDismiss()}) {
                Text(text = stringResource(R.string.save))
            }

            TextButton(onClick = { onDismiss() }) {
                Text(text = "cancel")
            }
        }

    }
    }
    }
}




@Composable
fun CityCard(
    cityItem: cityItem,
    onRemoveItem: () -> Unit = {},
    onNavigateToMain: (String) -> Unit,
    cityViewModel: CityViewModel = viewModel(),

) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier.padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = {
                onNavigateToMain(
                    cityViewModel.getCity(cityItem)
                )
            }) {
                Text(text = cityItem.city)
            }
            Spacer(modifier = Modifier.fillMaxSize(0.55f))
            Spacer(modifier = Modifier.width(10.dp))
            //Add Show Picture Here
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",
                modifier = Modifier.clickable {
                    onRemoveItem()
                },
                tint = Color.Red
            )
        }
    }
}