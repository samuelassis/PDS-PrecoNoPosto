package com.example.preconoposto.domain

import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.data.relations.UserWithFavoritesAndGasStation
import com.example.preconoposto.repository.GasStationRepository
import com.example.preconoposto.repository.UserRepository

class GasStationFiltersImpl(
    private val userRepository: UserRepository,
    private val gasStationRepository: GasStationRepository
): GasStationFilters {

    override suspend fun getAllGasStationsAndAddressAndPriceAndService(
    ): List<GasStationAndAddressAndPriceAndService> {
        return gasStationRepository.getAllGasStationsAndAddressAndPriceAndService()
    }

    override suspend fun getGasStationsAndAddressAndPriceAndService(
        id: Long
    ): GasStationAndAddressAndPriceAndService? {
        return gasStationRepository.getGasStationsAndAddressAndPriceAndService(id)
    }

    override suspend fun getAllGasStationsThatHaveConvenienceStore(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?> {
        val filteredGasStations = gasStations.filterNotNull()
        return filteredGasStations.filter {
            it.service.hasConvenienceStore
        }
    }

    override suspend fun getAllGasStationsThatHaveCarWash(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?> {
        val filteredGasStations = gasStations.filterNotNull()
        return filteredGasStations.filter {
            it.service.hasCarWash
        }
    }

    override suspend fun getAllGasStationsThatHaveCalibrator(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?> {
        val filteredGasStations = gasStations.filterNotNull()
        return filteredGasStations.filter {
            it.service.hasCalibrator
        }
    }

    override suspend fun getAllGasStationsThatHaveOilChange(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?> {
        val filteredGasStations = gasStations.filterNotNull()
        return filteredGasStations.filter {
            it.service.hasOilChange
        }
    }

    override suspend fun getAllGasStationsThatHaveTireShop(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?> {
        val filteredGasStations = gasStations.filterNotNull()
        return filteredGasStations.filter {
            it.service.hasTireShop
        }
    }

    override suspend fun getAllGasStationsThatHaveRestaurant(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?> {
        val filteredGasStations = gasStations.filterNotNull()
        return filteredGasStations.filter {
            it.service.hasRestaurant
        }
    }

    override suspend fun getAllGasStationsThatHaveMechanical(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?> {
        val filteredGasStations = gasStations.filterNotNull()
        return filteredGasStations.filter {
            it.service.hasMechanical
        }
    }

    override suspend fun getAllGasStationsOrderedByGasPrice(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?> {
        val filteredGasStations = gasStations.filterNotNull()
        return filteredGasStations.sortedBy {
            it.price.gasolinePrice
        }
    }

    override suspend fun getAllGasStationsOrderedByAlcoholPrice(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?> {
        val filteredGasStations = gasStations.filterNotNull()
        return filteredGasStations.sortedBy {
            it.price.alcoholPrice
        }
    }

    override suspend fun getAllGasStationsOrderedByDieselPrice(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?> {
        val filteredGasStations = gasStations.filterNotNull()
        return filteredGasStations.sortedBy {
            it.price.dieselPrice
        }
    }

    override suspend fun getAllUserFavorites(
        userId: Long
    ): UserWithFavoritesAndGasStation? {
        return userRepository.getFavorites(userId)
    }

}