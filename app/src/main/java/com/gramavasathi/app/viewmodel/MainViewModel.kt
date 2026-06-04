package com.gramavasathi.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gramavasathi.app.data.FarmRepository
import com.gramavasathi.app.model.FarmStay
import com.gramavasathi.app.model.HostChecklistItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // Farm Stays
    var farmStays by mutableStateOf(FarmRepository.farmStays)
        private set

    var searchQuery by mutableStateOf("")
        private set

    var selectedFilters = mutableStateListOf<String>()
        private set

    fun updateSearch(query: String) {
        searchQuery = query
        applyFilters()
    }

    fun toggleFilter(filter: String) {
        if (selectedFilters.contains(filter)) {
            selectedFilters.remove(filter)
        } else {
            selectedFilters.add(filter)
        }
        applyFilters()
    }

    private fun applyFilters() {
        var filteredList = FarmRepository.farmStays

        if (searchQuery.isNotEmpty()) {
            filteredList = filteredList.filter {
                it.name.contains(searchQuery, ignoreCase = true) ||
                it.location.contains(searchQuery, ignoreCase = true) ||
                it.activities.any { activity -> activity.contains(searchQuery, ignoreCase = true) }
            }
        }

        if (selectedFilters.isNotEmpty()) {
            filteredList = filteredList.filter { farm ->
                selectedFilters.all { filter -> farm.activities.contains(filter) }
            }
        }

        farmStays = filteredList
    }

    // Host Checklist
    val checklistItems = mutableStateListOf(
        HostChecklistItem(1, "Clean Sheets"),
        HostChecklistItem(2, "Safe Water"),
        HostChecklistItem(3, "Western Toilet"),
        HostChecklistItem(4, "Clean Bathroom"),
        HostChecklistItem(5, "First Aid Kit")
    )

    fun toggleChecklistItem(id: Int) {
        val index = checklistItems.indexOfFirst { it.id == id }
        if (index != -1) {
            val item = checklistItems[index]
            checklistItems[index] = item.copy(isChecked = !item.isChecked)
        }
    }

    val readinessScore: Int
        get() {
            val checkedCount = checklistItems.count { it.isChecked }
            return if (checklistItems.isEmpty()) 0 else (checkedCount.toFloat() / checklistItems.size * 100).toInt()
        }

    // Simulated GenAI: Cultural Guide
    var culturalQuery by mutableStateOf("")
    var culturalResponse by mutableStateOf("")
    var isAskingAi by mutableStateOf(false)

    fun askAiCulturalGuide() {
        if (culturalQuery.isBlank()) return
        viewModelScope.launch {
            isAskingAi = true
            culturalResponse = ""
            delay(1500) // Simulate processing
            culturalResponse = simulateCulturalGuide(culturalQuery)
            isAskingAi = false
        }
    }

    private fun simulateCulturalGuide(query: String): String {
        val lower = query.lowercase()
        return when {
            lower.contains("shoes") -> "In rural homes, it is customary to remove shoes before entering. This keeps the living space clean and shows respect for the household."
            lower.contains("food") || lower.contains("eat") -> "Always accept a small offering of food or tea. Using your right hand for eating and passing items is considered polite."
            lower.contains("dress") || lower.contains("wear") -> "Modest clothing is appreciated in village settings. For women, long skirts or loose trousers are ideal. For men, casual shirts and pants are standard."
            lower.contains("hello") || lower.contains("greet") -> "A warm 'Namaste' with folded hands is the traditional way to greet elders and hosts."
            else -> "That's a great question about our local culture! Generally, villagers are very welcoming. Being polite, asking permission before taking photos, and showing interest in their daily activities like farming will build a great bond."
        }
    }

    // Simulated GenAI: Listing Optimizer
    var rawDescription by mutableStateOf("")
    var optimizedDescription by mutableStateOf("")
    var isOptimizing by mutableStateOf(false)

    fun optimizeListing() {
        if (rawDescription.isBlank()) return
        viewModelScope.launch {
            isOptimizing = true
            optimizedDescription = ""
            delay(2000) // Simulate complex processing
            optimizedDescription = simulateListingOptimizer(rawDescription)
            isOptimizing = false
        }
    }

    private fun simulateListingOptimizer(raw: String): String {
        return "✨ **Welcome to Your Tranquil Escape** ✨\n\n" +
               "Nestled in the heart of nature, this property offers more than just a stay—it's an experience. " +
               "${raw.trim().replaceFirstChar { it.uppercase() }}. " +
               "Wake up to the chirping of birds, breathe in the fresh village air, and enjoy home-cooked organic meals. " +
               "Our home is your home, where every corner tells a story of tradition and warmth. " +
               "Come, rediscover peace and connect with the earth at our humble abode."
    }

    // Booking Simulation
    var bookingStatus by mutableStateOf("")
        private set

    fun simulateBooking(farmName: String) {
        bookingStatus = "Booking confirmed for $farmName! Enjoy your stay."
    }

    fun clearBookingStatus() {
        bookingStatus = ""
    }
}
