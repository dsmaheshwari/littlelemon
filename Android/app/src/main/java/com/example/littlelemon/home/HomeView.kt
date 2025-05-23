package com.example.littlelemon.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.common.composables.InputBox
import com.example.database.MenuItem
import com.example.database.MenuItemDatabase
import com.example.littlelemon.ui.theme.*
import com.example.navigations.NavigationManager
import com.example.network.NetworkCall
import io.ktor.client.call.body
import kotlinx.coroutines.*

suspend fun fetchMenuList(): MenuItemList {
    val menuItems = NetworkCall.fetch(
        urlString = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    ).body<MenuItemList>()

    return menuItems
}

@Composable
private fun MenuFilterItem(
    categories: List<String>,
    selectedCategories: Set<String>,
    onSelectionChanged: (Set<String>) -> Unit
) {

    LazyRow {
        items(
            count = categories.size,
            itemContent = {
                var isSelected = selectedCategories.contains(categories[it])

                FilterChip(
                    selected = isSelected,
                    onClick = {
                        val newSelection = selectedCategories.toMutableSet()
                        if(isSelected) {
                            newSelection.remove(categories[it])
                        } else {
                            newSelection.add(categories[it])
                        }
                        onSelectionChanged(newSelection)

                        isSelected = !isSelected
                    },
                    label = {
                        Text(
                            text = categories[it],
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Button_Primary_Background,
                        selectedLabelColor = Button_Primary_Text_Color,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        labelColor = Label_Text
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.padding(start = if(it == 0) 0.dp else 8.dp)
                )
            }
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItemCard(item: MenuItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(0.66f)
                    .padding(end = 12.dp)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$${item.price}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Column(
                modifier = Modifier
                    .weight(0.33f)
                    .fillMaxHeight()
                    .padding(start = 12.dp)
            ) {
                GlideImage(
                    model = item.image,
                    contentDescription = item.description
                )
            }
        }
    }
}

@Composable
private fun MenuItemList(items: List<MenuItem>) {
    LazyColumn(

    ) {
        items(
            count = items.size,
            itemContent = {
                MenuItemCard(items[it])
            }
        )
    }
}

@Composable
fun HomeView(navigationManager: NavigationManager) {
    val context = LocalContext.current

    val dao = remember { MenuItemDatabase.getDatabase(context).menuItemDao() }
    val menuItems by dao.getAll().collectAsState(initial = emptyList())

    var selectedCategories by remember { mutableStateOf(setOf<String>()) }

    val categories = menuItems.map { it.category }.distinct()
    var searchPhrase by remember { mutableStateOf("") }
    val filteredItems = menuItems.filter { item ->
        val matchesCategory = selectedCategories.isEmpty() || item.category in selectedCategories
        val matchesSearch = searchPhrase.isNullOrBlank() || item.title.contains(searchPhrase, ignoreCase = true)
        matchesCategory && matchesSearch
    }

    LaunchedEffect(Unit) {
        val isEmpty = withContext(Dispatchers.IO) { dao.isEmpty() }

        if (isEmpty) {
            try {
                val networkMenu = fetchMenuList()
                val roomItems = networkMenu.menu.map { it.toRoom() }

                withContext(Dispatchers.IO) {
                    dao.insertAll(*roomItems.toTypedArray())
                }

            } catch (e: Exception) {
                Log.e("HomeView", "Failed to fetch from API", e)
            }
        }
    }

    Column {
        Text(
            text = "Order For Delivery!",
            style = MaterialTheme.typography.displayLarge,
        )
        MenuFilterItem(
            categories = categories,
            selectedCategories = selectedCategories,
            onSelectionChanged = { selectedCategories = it
            }
        )

        InputBox(
            label = "",
            value = searchPhrase,
            onValueChange = {
                searchPhrase = it
            },
            placeholder="Enter Search Phrase",
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        )

        MenuItemList(filteredItems)
    }
}