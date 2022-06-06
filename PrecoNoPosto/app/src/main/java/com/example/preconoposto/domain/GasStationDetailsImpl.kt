package com.example.preconoposto.domain

import com.example.preconoposto.data.Favorite
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.Price
import com.example.preconoposto.data.relations.GasStationAndPrice
import com.example.preconoposto.data.relations.GasStationWithRatingsAndUser
import com.example.preconoposto.data.relations.RatingAndUser
import com.example.preconoposto.repository.FavoriteRepository
import com.example.preconoposto.repository.GasStationRepository

class GasStationDetailsImpl(
    private val favoriteRepository: FavoriteRepository,
    private val gasStationRepository: GasStationRepository
) : GasStationDetails {

    override suspend fun getGasStationWithRatingsAndUser(id: Long): GasStationWithRatingsAndUser {
        return gasStationRepository.getGasStationWithRatingsAndUser(id)
    }

    override suspend fun getGasStationAndPrice(id: Long): GasStationAndPrice {
        return gasStationRepository.getGasStationAndPrice(id)
    }

    override suspend fun getScoreAverageTexts(id: Long): Map<String, String> {
        val gasStationWithRatingsAndUser = getGasStationWithRatingsAndUser(id)

        val scoreAveragesStrings = gasStationWithRatingsAndUser.ratings?.let {
            getScoreAverageStrings(it)
        } ?: listOf(
            "--/5.0",
            "--/5.0",
            "--/5.0",
            "--/5.0",
            "--/5.0",
            "--/5.0"
        )
        return mapOf(
            "generalScore" to scoreAveragesStrings[0],
            "attendanceScore" to scoreAveragesStrings[1],
            "qualityScore" to scoreAveragesStrings[2],
            "waitingTimeScore" to scoreAveragesStrings[3],
            "serviceScore" to scoreAveragesStrings[4],
            "safetyScore" to scoreAveragesStrings[5],
        )
    }

    private fun getScoreAverageStrings(ratingAndUser: List<RatingAndUser>): List<String> {
        val generalScore = ratingAndUser.map {
            it.rating.generalScore
        }
        val attendanceScore = ratingAndUser.map {
            it.rating.attendanceScore
        }
        val qualityScore = ratingAndUser.map {
            it.rating.qualityScore
        }
        val waitingTimeScore = ratingAndUser.map {
            it.rating.waitingTimeScore
        }
        val serviceScore = ratingAndUser.map {
            it.rating.serviceScore
        }
        val safetyScore = ratingAndUser.map {
            it.rating.safetyScore
        }

        return listOf(
            String.format("%.1f", generalScore.average())+"/5.0",
            String.format("%.1f", attendanceScore.average())+"/5.0",
            String.format("%.1f", qualityScore.average())+"/5.0",
            String.format("%.1f", waitingTimeScore.average())+"/5.0",
            String.format("%.1f", serviceScore.average())+"/5.0",
            String.format("%.1f", safetyScore.average())+"/5.0"
        )
    }

    override suspend fun getPriceTexts(id: Long): Map<String, String> {
        val gasStationAndPrice = getGasStationAndPrice(id)
        val priceTexts = gasStationAndPrice.price?.let {
            getScorePriceAndDateStrings(it)
        } ?: listOf(
            "0,000L",
            "0,000L",
            "0,000L"
        )
        return mapOf(
            "gasPrice" to priceTexts[0],
            "alcoholPrice" to priceTexts[1],
            "dieselPrice" to priceTexts[2],
            "date" to priceTexts[3]
        )
    }

    private fun getScorePriceAndDateStrings(price: Price): List<String> {
        return listOf(
            String.format("%.3f", price.gasolinePrice)+"/L",
            String.format("%.3f", price.alcoholPrice)+"/L",
            String.format("%.3f", price.dieselPrice)+"/L",
            price.lastUpdateDate.toString()
        )
    }

    override suspend fun saveFavorite(favorite: Favorite) {
        favoriteRepository.save(favorite)
    }

    override suspend fun deleteFavorite(userId: Long, gasStationId: Long) {
        val favorite = favoriteRepository.getFavoriteByUserIdAndGasStationId(userId, gasStationId)
        favoriteRepository.delete(favorite)
    }

    override suspend fun getAllGasStationAndPrice(): List<GasStationAndPrice> {
        return gasStationRepository.getAllGasStationAndPrice()
    }

}