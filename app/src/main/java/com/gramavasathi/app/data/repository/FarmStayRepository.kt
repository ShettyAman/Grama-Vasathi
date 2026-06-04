package com.gramavasathi.app.data.repository

import com.gramavasathi.app.data.model.Booking
import com.gramavasathi.app.data.model.FarmStay
import com.gramavasathi.app.data.model.SampleData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository for Farm Stays. 
 * Firebase integration is disabled to ensure the app runs without API key dependencies.
 */
class FarmStayRepository {

    // ── Get all farm-stays (using sample data) ──
    fun getFarmStays(): Flow<List<FarmStay>> = flow {
        // Emit sample data directly
        emit(SampleData.farmStays)
    }

    // ── Search/filter farm-stays by activity ──
    fun getFarmStaysByActivity(activity: String): Flow<List<FarmStay>> = flow {
        emit(SampleData.farmStays.filter { it.activities.contains(activity) })
    }

    // ── Get single farm-stay ──
    suspend fun getFarmStayById(id: String): FarmStay? {
        return SampleData.farmStays.find { it.id == id }
    }

    // ── Save a booking (simulated) ──
    suspend fun saveBooking(booking: Booking): Result<String> {
        // Simulate success for local execution
        return Result.success("SIMULATED_${System.currentTimeMillis()}")
    }

    // ── Seed sample data (No-op in offline mode) ──
    suspend fun seedSampleData() {
        // Not used in mock mode
    }
}
