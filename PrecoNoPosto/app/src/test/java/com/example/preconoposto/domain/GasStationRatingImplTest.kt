package com.example.preconoposto.domain

import com.example.preconoposto.data.Rating
import com.example.preconoposto.repository.RatingRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.sql.Date

class GasStationRatingImplTest {
    @RelaxedMockK
    val ratingRepository = mockk<RatingRepository>()

    @Test
    fun `when save is called it should save the data in DB`() = runBlocking {
        val validRating = Rating(
            idRating = 1,
            idGasStation = 1,
            idUser = 1,
            generalScore = 4.0,
            attendanceScore = 4.0,
            qualityScore = 4.0,
            waitingTimeScore = 4.0,
            serviceScore = 4.0,
            safetyScore = 4.0,
            commentary = "Commentary",
            date = Date(20211010)
        )

        coEvery {
            ratingRepository.save(validRating)
        } returns validRating.idRating

        val gasStationRatingImpl = GasStationRatingImpl(ratingRepository)
        gasStationRatingImpl.save(rating = validRating)

        coVerify(exactly = 1){
            ratingRepository.save(validRating)
        }
    }
}