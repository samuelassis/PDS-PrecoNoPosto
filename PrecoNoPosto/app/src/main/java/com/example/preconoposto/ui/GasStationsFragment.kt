package com.example.preconoposto.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preconoposto.R
import com.example.preconoposto.database.AppDatabase
import com.example.preconoposto.domain.GasStationDetailsImpl
import com.example.preconoposto.domain.GasStationFiltersImpl

class GasStationsFragment : Fragment(R.layout.fragment_gas_stations) {

    companion object {
        fun newInstance() = GasStationsFragment()
    }

    var generalScoreTexts = mutableListOf<String>()

    private lateinit var orderByGasPrice: ToggleButton
    private lateinit var orderByAlcoholPrice: ToggleButton
    private lateinit var orderByDieselPrice: ToggleButton

    private lateinit var viewModel: GasStationsViewModel
    private lateinit var gasStationsRecycleView: RecyclerView
    private lateinit var gasStationsAdapter: GasStationAdapter

    private val gasStationDao by lazy {
        AppDatabase.getInstance(this.requireContext()).gasStationDao
    }
    private val favoriteDao by lazy{
        AppDatabase.getInstance(this.requireContext()).favoriteDao
    }
    private val userDao by lazy{
        AppDatabase.getInstance(this.requireContext()).userDao
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gas_stations, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[GasStationsViewModel::class.java]
        viewModel.gasStationDetailsImpl = GasStationDetailsImpl(favoriteDao, gasStationDao)
        viewModel.gasStationFilter = GasStationFiltersImpl(userDao, favoriteDao, gasStationDao)
        viewModel.getAllGasStationsAndPrice()

        setupView(view)
        setupListeners()
        setupObservers(view)
    }

    private fun setupView(view: View){
        orderByGasPrice = view.findViewById(R.id.gasStationsOrderByGasPriceTb)
        orderByAlcoholPrice = view.findViewById(R.id.gasStationsOrderByAlcoholPriceTb)
        orderByDieselPrice = view.findViewById(R.id.gasStationsOrderByDieselTb)
    }

    private fun setupListeners(){
        orderByGasPrice.setOnClickListener {
            orderByAlcoholPrice.isChecked = false
            orderByDieselPrice.isChecked = false
            checkTogglesAndUpdateGasStationFilteredList()
        }
        orderByAlcoholPrice.setOnClickListener {
            orderByGasPrice.isChecked = false
            orderByDieselPrice.isChecked = false
            checkTogglesAndUpdateGasStationFilteredList()
        }
        orderByDieselPrice.setOnClickListener {
            orderByGasPrice.isChecked = false
            orderByAlcoholPrice.isChecked = false
            checkTogglesAndUpdateGasStationFilteredList()
        }
    }

    private fun setupObservers(view: View) {
        viewModel.gasStationsCompleteList.observe(viewLifecycleOwner) { gasStationsAndPricesList ->
                val gasStationsIds = gasStationsAndPricesList.map {
                    it?.gasStation?.idGasStation ?: 1L
                }
                viewModel.getAllGeneralScoresTexts(gasStationsIds)
                    .observe(viewLifecycleOwner) { generalScoresTexts ->
                        val textList = generalScoresTexts.map {
                            it["generalScore"] ?: "--/5.0"
                        }
                        gasStationsAndPricesList?.let { gasStationsAndPrices ->
                            gasStationsAdapter = GasStationAdapter()
                            gasStationsAdapter.setgasStations(gasStationsAndPrices.filterNotNull(), textList)
                            gasStationsRecycleView = view.findViewById(R.id.gasStationsListRv)
                            gasStationsRecycleView.layoutManager = LinearLayoutManager(view.context)
                            gasStationsRecycleView.adapter = gasStationsAdapter
                        }
                    }
            }
    }

    private fun checkTogglesAndUpdateGasStationFilteredList(){

        var noneIsChecked = true

        if(orderByGasPrice.isChecked){
            orderByGasPrice.setTextColor(resources.getColor(R.color.white))
            orderByGasPrice.setBackgroundColor(resources.getColor(R.color.orange_dark))
            viewModel.getAllGasStationsOrderedByGasPrice()
            noneIsChecked = false
        }
        else {
            orderByGasPrice.setTextColor(resources.getColor(R.color.black))
            orderByGasPrice.setBackgroundColor(resources.getColor(R.color.white))
        }

        if(orderByAlcoholPrice.isChecked) {
            orderByAlcoholPrice.setTextColor(resources.getColor(R.color.white))
            orderByAlcoholPrice.setBackgroundColor(resources.getColor(R.color.orange_dark))
            viewModel.getAllGasStationsOrderedByAlcoholPrice()
            noneIsChecked = false
        }
        else {
            orderByAlcoholPrice.setTextColor(resources.getColor(R.color.black))
            orderByAlcoholPrice.setBackgroundColor(resources.getColor(R.color.white))
        }

        if(orderByDieselPrice.isChecked) {
            orderByDieselPrice.setTextColor(resources.getColor(R.color.white))
            orderByDieselPrice.setBackgroundColor(resources.getColor(R.color.orange_dark))
            viewModel.getAllGasStationsOrderedByDieselPrice()
            noneIsChecked = false
        }
        else {
            orderByDieselPrice.setTextColor(resources.getColor(R.color.black))
            orderByDieselPrice.setBackgroundColor(resources.getColor(R.color.white))
        }

        if(noneIsChecked){
            viewModel.getAllGasStationsAndPrice()
        }
        /*else {
            viewModel.setFilteredList()
        }*/
    }

}