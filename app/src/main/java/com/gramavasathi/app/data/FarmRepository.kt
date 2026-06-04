package com.gramavasathi.app.data

import com.gramavasathi.app.model.FarmStay

object FarmRepository {
    val farmStays = listOf(
        FarmStay(
            id = "1",
            name = "Green Acres Farm",
            location = "Tumakuru, Karnataka",
            description = "Experience authentic village life with organic farming and traditional food. Perfect for families looking to reconnect with nature.",
            price = "₹1500/night",
            imageUrl = "https://images.unsplash.com/photo-1500382017468-9049fed747ef?q=80&w=1000&auto=format&fit=crop",
            activities = listOf("Cow Milking", "Local Cooking", "Field Plowing"),
            rating = 4.5f
        ),
        FarmStay(
            id = "2",
            name = "River Side Retreat",
            location = "Shivamogga, Karnataka",
            description = "A peaceful stay near the Tunga river. Perfect for birdwatching and fishing. Enjoy the sound of water and fresh air.",
            price = "₹2000/night",
            imageUrl = "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?q=80&w=1000&auto=format&fit=crop",
            activities = listOf("Birdwatching", "Fishing"),
            rating = 4.8f
        ),
        FarmStay(
            id = "3",
            name = "Heritage Malnad Home",
            location = "Chikmagalur, Karnataka",
            description = "A 100-year-old traditional house surrounded by coffee plantations. Taste the best home-grown coffee.",
            price = "₹2500/night",
            imageUrl = "https://images.unsplash.com/photo-1500076656116-558758c991c1?q=80&w=1000&auto=format&fit=crop",
            activities = listOf("Local Cooking", "Birdwatching"),
            rating = 4.7f
        ),
        FarmStay(
            id = "4",
            name = "Vedic Village Stay",
            location = "Udupi, Karnataka",
            description = "Experience the serenity of coastal village life. Learn traditional crafts and enjoy local festivals.",
            price = "₹1800/night",
            imageUrl = "https://images.unsplash.com/photo-1518495973542-4542c06a5843?q=80&w=1000&auto=format&fit=crop",
            activities = listOf("Cow Milking", "Field Plowing"),
            rating = 4.6f
        )
    )
}
