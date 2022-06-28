package com.example.preconoposto.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.preconoposto.data.Rating
import com.example.preconoposto.domain.GasStationRatingImpl
import com.example.preconoposto.ui.viewmodels.EvaluateGasStationViewModel
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import java.sql.Date
import java.util.concurrent.TimeUnit

class EvaluateGasStationViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = Timeout(1, TimeUnit.MINUTES)

    @RelaxedMockK
    private lateinit var scoresAreValid: Observer<Boolean>

    private val gasStationRatingImpl = mockk<GasStationRatingImpl>()

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when all scores are under 5 the scoresAreValid variable should be true`(){
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
            commentary = "Lorem ipsum dolor sit amet. Quo aspernatur incidunt ut dignissimos galisum et culpa galisum At dolorem quasi est consequuntur quidem. Ad ullam sint ea quasi quia nam sapiente nesciunt et laudantium iusto. Aut aliquid fuga qui velit ipsum qui nemo nisi sit minus voluptatem cum maiores exercitationem et earum labore qui magnam itaque.",
            date = Date(20211010)
        )

        coEvery {
            gasStationRatingImpl.save(rating = validRating)
        } returns Unit

        val evaluateGasStationViewModel = EvaluateGasStationViewModel()
        evaluateGasStationViewModel.gasStationRatingImpl = gasStationRatingImpl

        evaluateGasStationViewModel.scoresAreValid.observeForever(
            scoresAreValid
        )
        evaluateGasStationViewModel.saveRating(validRating)

        verify(exactly = 1){
            scoresAreValid.onChanged(true)
        }
    }

    @Test
    fun `when any score is above 5 the scoresAreValid variable should be false`(){
        val invalidRating = Rating(
            idRating = 1,
            idGasStation = 1,
            idUser = 1,
            generalScore = 5.1,
            attendanceScore = 4.0,
            qualityScore = 4.0,
            waitingTimeScore = 4.0,
            serviceScore = 4.0,
            safetyScore = 4.0,
            commentary = "Lorem ipsum dolor sit amet. Quo aspernatur incidunt ut dignissimos galisum et culpa galisum At dolorem quasi est consequuntur quidem. Ad ullam sint ea quasi quia nam sapiente nesciunt et laudantium iusto. Aut aliquid fuga qui velit ipsum qui nemo nisi sit minus voluptatem cum maiores exercitationem et earum labore qui magnam itaque.",
            date = Date(20211010)
        )

        coEvery {
            gasStationRatingImpl.save(rating = invalidRating)
        } returns Unit

        val evaluateGasStationViewModel = EvaluateGasStationViewModel()
        evaluateGasStationViewModel.gasStationRatingImpl = gasStationRatingImpl

        evaluateGasStationViewModel.scoresAreValid.observeForever(
            scoresAreValid
        )
        evaluateGasStationViewModel.saveRating(invalidRating)

        verify(exactly = 1){
            scoresAreValid.onChanged(false)
        }
    }
}