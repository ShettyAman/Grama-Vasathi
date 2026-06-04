package com.gramavasathi.app.model

data class FarmStay(
    val id: String = "",
    val name: String = "",
    val location: String = "",
    val description: String = "",
    val price: String = "",
    val imageUrl: String = "",
    val activities: List<String> = emptyList(),
    val rating: Float = 0f
)

data class HostChecklistItem(
    val id: Int,
    val title: String,
    val isChecked: Boolean = false
)

data class CulturalTip(
    val title: String,
    val description: String,
    val icon: String = ""
)