package com.example.preconoposto.ui

import android.graphics.Color
import android.graphics.Typeface
import android.location.Geocoder
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.Gravity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import com.example.preconoposto.R
import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.database.AppDatabase
import com.example.preconoposto.databinding.FragmentHomeBinding
import com.example.preconoposto.domain.GasStationFiltersImpl
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class HomeFragment : Fragment() {

    private val userId: Long = 1

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var supportMapFragment: SupportMapFragment

    /*private lateinit var hasConvenienceStore: ToggleButton
    private lateinit var hasCarWash: ToggleButton
    private lateinit var hasCalibrator: ToggleButton
    private lateinit var hasOilChange: ToggleButton
    private lateinit var hasTireShop: ToggleButton
    private lateinit var hasRestaurant: ToggleButton
    private lateinit var hasMechanical: ToggleButton

    private lateinit var favorites: MaterialButton
    private lateinit var orderByGasPrice: MaterialButton
    private lateinit var orderByAlcoholPrice: MaterialButton
    private lateinit var orderByDieselPrice: MaterialButton*/

    private val gasStationDao by lazy {
        AppDatabase.getInstance(this.requireContext()).gasStationDao
    }
    private val userDao by lazy {
        AppDatabase.getInstance(this.requireContext()).userDao
    }
    private val geocoder by lazy {
        Geocoder(this.requireContext())
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
        setupViews(view)
        setupMap()
        setupListeners()
        viewModel.getAllGasStationsAndAddressAndPriceAndService()
    }

    private fun setupViews(view: View){
        supportMapFragment =
            childFragmentManager.findFragmentById(R.id.homeGasStationMap) as SupportMapFragment
    }

    private fun setupMap(){
        supportMapFragment.getMapAsync { googleMap ->

            googleMap.setInfoWindowAdapter(object: GoogleMap.InfoWindowAdapter {
                override fun getInfoContents(p0: Marker): View? {
                    val info = LinearLayout(context)
                    info.orientation = LinearLayout.VERTICAL

                    val title = TextView(context)
                    title.setTextColor(Color.BLACK)
                    title.gravity = Gravity.CENTER
                    title.setTypeface(null, Typeface.BOLD)
                    title.text = p0.title

                    val snippet = TextView(context)
                    snippet.setTextColor(Color.GRAY)
                    snippet.text = p0.snippet

                    info.addView(title)
                    info.addView(snippet)

                    return info
                }

                override fun getInfoWindow(p0: Marker): View? {
                    return null
                }
            })

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                LatLng(-19.8688170307, -43.96438268), 14F)
            )

            viewModel.gasStationsCompleteList.observe(viewLifecycleOwner){
                updateMapMarkers(it, googleMap)
            }
            viewModel.gasStationsFilteredSet.observe(viewLifecycleOwner){
                updateMapMarkers(it.toList(), googleMap)
            }

            googleMap.setOnInfoWindowClickListener {
                Log.i("Marker", it.tag.toString())
            }

        }
    }

    private fun setupListeners(){
        /*hasConvenienceStore.setOnClickListener {
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
        }*/
    }

    private fun checkTogglesAndUpdateGasStationFilteredList(){
        var noneIsChecked = true

        /*if(hasConvenienceStore.isChecked){
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

        if(noneIsChecked){
            viewModel.clearGasStationFilter()
            viewModel.getAllGasStationsAndAddressAndPriceAndService()
        }*/
    }

    private fun updateMapMarkers(
        gasStationsList: List<GasStationAndAddressAndPriceAndService?>,
        googleMap: GoogleMap
    ){
        gasStationsList.forEach { item ->
            val address = item?.address?.let { address ->
                geocoder.getFromLocationName(
                    "${address.street} ${address.number} ${address.city}",
                    1
                )
            }

            val latlng = address?.let {
                LatLng(it[0].latitude, it[0].longitude)
            }

            latlng?.let { itemLatLng ->
                val marker = googleMap.addMarker(
                    MarkerOptions()
                        .title(item.gasStation.name)
                        .snippet(String.format(
                            "Gasolina: R$%.2f\nÁlcool: R$%.2f\nDiesel: R$%.2f ",
                            item.price.gasolinePrice,
                            item.price.alcoholPrice,
                            item.price.dieselPrice)
                        )
                        .position(itemLatLng)
                )
                marker?.tag = item.gasStation.idGasStation
            }

        }
    }

}