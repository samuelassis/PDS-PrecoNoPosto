package com.example.preconoposto.domain

import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.data.relations.UserWithFavoritesAndGasStation

interface GasStationFilters {

    suspend fun getAllGasStationsAndAddressAndPriceAndService(
    ): List<GasStationAndAddressAndPriceAndService?>

    suspend fun getGasStationsAndAddressAndPriceAndService(
        id: Long
    ): GasStationAndAddressAndPriceAndService?

    suspend fun getAllGasStationsThatHaveConvenienceStore(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    suspend fun getAllGasStationsThatHaveCarWash(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    suspend fun getAllGasStationsThatHaveCalibrator(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    suspend fun getAllGasStationsThatHaveOilChange(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    suspend fun getAllGasStationsThatHaveTireShop(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    suspend fun getAllGasStationsThatHaveRestaurant(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    suspend fun getAllGasStationsThatHaveMechanical(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    suspend fun getAllGasStationsOrderedByGasPrice(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    suspend fun getAllGasStationsOrderedByAlcoholPrice(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    suspend fun getAllGasStationsOrderedByDieselPrice(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    suspend fun getAllUserFavorites(
        userId: Long
    ): UserWithFavoritesAndGasStation?

}