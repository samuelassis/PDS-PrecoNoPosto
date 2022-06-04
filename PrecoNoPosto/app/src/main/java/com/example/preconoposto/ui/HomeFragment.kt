package com.example.preconoposto.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.database.AppDatabase
import com.example.preconoposto.databinding.FragmentHomeBinding
import com.example.preconoposto.domain.GasStationFiltersImpl
import com.google.android.material.button.MaterialButton

class HomeFragment : Fragment() {

    private val userId: Long = 1

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    private lateinit var hasConvenienceStore: ToggleButton
    private lateinit var hasCarWash: ToggleButton
    private lateinit var hasCalibrator: ToggleButton
    private lateinit var hasOilChange: ToggleButton
    private lateinit var hasTireShop: ToggleButton
    private lateinit var hasRestaurant: ToggleButton
    private lateinit var hasMechanical: ToggleButton

    private lateinit var favorites: MaterialButton
    private lateinit var orderByGasPrice: MaterialButton
    private lateinit var orderByAlcoholPrice: MaterialButton
    private lateinit var orderByDieselPrice: MaterialButton

    private val gasStationDao by lazy {
        AppDatabase.getInstance(this.requireContext()).gasStationDao
    }
    private val userDao by lazy {
        AppDatabase.getInstance(this.requireContext()).userDao
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.gasStationFilter = GasStationFiltersImpl(userDao, gasStationDao)
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
        viewModel.getAllGasStationsAndAddressAndPriceAndService()
    }

    private fun setupObservers(){
        viewModel.gasStationsCompleteList.observe(viewLifecycleOwner){
            updateMap(it)
        }
        viewModel.gasStationsFilteredSet.observe(viewLifecycleOwner){
            updateMap(it.toList())
        }
    }

    private fun setupListeners(){
        hasConvenienceStore.setOnClickListener {
            checkTogglesAndUpdateGasStationFilteredList()
        }
        hasCarWash.setOnClickListener {
            checkTogglesAndUpdateGasStationFilteredList()
        }
        hasCalibrator.setOnClickListener {
            checkTogglesAndUpdateGasStationFilteredList()
        }
        hasOilChange.setOnClickListener {
            checkTogglesAndUpdateGasStationFilteredList()
        }
        hasTireShop.setOnClickListener {
            checkTogglesAndUpdateGasStationFilteredList()
        }
        hasRestaurant.setOnClickListener {
            checkTogglesAndUpdateGasStationFilteredList()
        }
        hasMechanical.setOnClickListener {
            checkTogglesAndUpdateGasStationFilteredList()
        }

        favorites.setOnClickListener {
            viewModel.getAllUserFavorites(userId)
        }
        orderByGasPrice.setOnClickListener {
            viewModel.getAllGasStationsOrderedByGasPrice()
        }
        orderByAlcoholPrice.setOnClickListener {
            viewModel.getAllGasStationsOrderedByAlcoholPrice()
        }
        orderByDieselPrice.setOnClickListener {
            viewModel.getAllGasStationsOrderedByDieselPrice()
        }
    }

    private fun checkTogglesAndUpdateGasStationFilteredList(){
        var noneIsChecked = true
        if(hasConvenienceStore.isChecked){
            viewModel.getAllGasStationsThatHaveConvenienceStore()
            noneIsChecked = false
        }
        if(hasCarWash.isChecked) {
            viewModel.getAllGasStationsThatHaveCarWash()
            noneIsChecked = false
        }
        if(hasCalibrator.isChecked) {
            viewModel.getAllGasStationsThatHaveCalibrator()
            noneIsChecked = false
        }
        if(hasOilChange.isChecked) {
            viewModel.getAllGasStationsThatHaveOilChange()
            noneIsChecked = false
        }
        if(hasTireShop.isChecked) {
            viewModel.getAllGasStationsThatHaveTireShop()
            noneIsChecked = false
        }
        if(hasRestaurant.isChecked) {
            viewModel.getAllGasStationsThatHaveRestaurant()
            noneIsChecked = false
        }
        if(hasMechanical.isChecked) {
            viewModel.getAllGasStationsThatHaveMechanical()
            noneIsChecked = false
        }

        if(noneIsChecked)
            updateMap(viewModel.gasStationsCompleteList.value ?: listOf())
    }

    private fun updateMap(gasStationsList: List<GasStationAndAddressAndPriceAndService?>){
        gasStationsList.forEach { _ ->
            // updateMapTaps
        }
    }

}