package com.example.littlelemon.home

import com.example.database.MenuItem
import kotlinx.serialization.Serializable

@Serializable
data class MenuItemList(
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    val id: String,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
) {
    fun toRoom(): MenuItem {
        return MenuItem(
            id = id.toInt(),
            title = title,
            description = description,
            price = price.toDouble(),
            image = image,
            category = category
        )
    }
}