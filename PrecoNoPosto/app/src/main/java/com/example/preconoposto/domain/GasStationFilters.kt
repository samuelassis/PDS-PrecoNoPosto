package com.example.preconoposto.domain

import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.data.relations.UserWithFavoritesAndGasStation

interface GasStationFilters {

    suspend fun getAllGasStationsAndAddressAndPriceAndService(
    ): List<GasStationAndAddressAndPriceAndService?>

    suspend fun getGasStationsAndAddressAndPriceAndService(
        id: Long
    ): GasStationAndAddressAndPriceAndService?

    fun getAllGasStationsThatHaveConvenienceStore(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    fun getAllGasStationsThatHaveCarWash(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    fun getAllGasStationsThatHaveCalibrator(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    fun getAllGasStationsThatHaveOilChange(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    fun getAllGasStationsThatHaveTireShop(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    fun getAllGasStationsThatHaveRestaurant(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    fun getAllGasStationsThatHaveMechanical(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    fun getAllGasStationsOrderedByGasPrice(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    fun getAllGasStationsOrderedByAlcoholPrice(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    fun getAllGasStationsOrderedByDieselPrice(
        gasStations: List<GasStationAndAddressAndPriceAndService?>
    ): List<GasStationAndAddressAndPriceAndService?>

    suspend fun getAllUserFavorites(
        userId: Long
    ): UserWithFavoritesAndGasStation?

}