package com.example.preconoposto.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.location.Geocoder
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.Gravity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.preconoposto.R
import com.example.preconoposto.data.relations.GasStationAndAddressAndPriceAndService
import com.example.preconoposto.database.AppDatabase
import com.example.preconoposto.database.dataStore
import com.example.preconoposto.database.loggedUserIdPreference
import com.example.preconoposto.databinding.FragmentHomeBinding
import com.example.preconoposto.domain.GasStationFiltersImpl
import com.example.preconoposto.ui.viewmodels.HomeViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var userId = 100L

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var supportMapFragment: SupportMapFragment

    private lateinit var hasConvenienceStore: ToggleButton
    private lateinit var hasCarWash: ToggleButton
    private lateinit var hasCalibrator: ToggleButton
    private lateinit var hasOilChange: ToggleButton
    private lateinit var hasTireShop: ToggleButton
    private lateinit var hasRestaurant: ToggleButton
    private lateinit var hasMechanical: ToggleButton
    private lateinit var searchLocation: ImageButton
    private lateinit var searchAddress: TextInputEditText
    private lateinit var teste: TextInputEditText

    private val gasStationDao by lazy {
        AppDatabase.getInstance(this.requireContext()).gasStationDao
    }
    private val userDao by lazy {
        AppDatabase.getInstance(this.requireContext()).userDao
    }
    private val favoriteDao by lazy {
        AppDatabase.getInstance(this.requireContext()).favoriteDao
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
        viewModel.gasStationFilter = GasStationFiltersImpl(userDao, favoriteDao, gasStationDao)
        binding = FragmentHomeBinding.inflate(inflater)
//        binding.homeProgressBarIcon.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            requireContext().dataStore.data.collect {
                userId = it[loggedUserIdPreference]?.toLong() ?: 0L
            }
        }
        setupViews(view)
        setupListeners()
        viewModel.getAllGasStationsAndAddressAndPriceAndService()
    }



    private fun setupViews(view: View){
        supportMapFragment =
            childFragmentManager.findFragmentById(R.id.homeGasStationMap) as SupportMapFragment

        hasConvenienceStore = view.findViewById(R.id.homeHasConvenienceStoreTb)
        hasCarWash = view.findViewById(R.id.homeHasCarWashTb)
        hasCalibrator = view.findViewById(R.id.homeHasCalibratorTb)
        hasOilChange = view.findViewById(R.id.homeHasOilChangeTb)
        hasTireShop = view.findViewById(R.id.homeHasTireShopTb)
        hasRestaurant = view.findViewById(R.id.homeHasRestaurantTb)
        hasMechanical = view.findViewById(R.id.homeHasMechanicalTb)
        searchLocation = view.findViewById(R.id.homeChoseLocaleImageButton)
        searchAddress = view.findViewById(R.id.homeSearchLocationTiet)

        checkNetworkConnection()

    }

    private fun setupMap(){
        supportMapFragment.getMapAsync { googleMap ->
            val icex = LatLng(-19.8688170307, -43.96438268)

            googleMap.setInfoWindowAdapter(object: GoogleMap.InfoWindowAdapter {
                override fun getInfoContents(p0: Marker): View {
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

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(icex, 13.5F)
            )

            viewModel.gasStationsCompleteList.observe(viewLifecycleOwner){
                googleMap.clear()
                updateMapMarkers(it, googleMap)
            }
            viewModel.gasStationsFilteredSet.observe(viewLifecycleOwner){
                googleMap.clear()
                updateMapMarkers(it.toList(), googleMap)
            }
            searchLocation.setOnClickListener {
                val search = searchAddress.text.toString()
                val address = geocoder.getFromLocationName(search, 1)
                val latlng = address?.let {
                    LatLng(it[0].latitude, it[0].longitude)
                }
                latlng?.let {
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 13F))
                } ?: googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(icex, 13F))
            }

            googleMap.setOnInfoWindowClickListener {
                val gasStationId = it.tag.toString().toLong()
                val action = HomeFragmentDirections.fromHomeFragmentToGasStationDetailsFragment(gasStationId)
                view?.findNavController()?.navigate(action)
                Log.i("Marker", it.tag.toString())
            }

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

        binding.homeNetworkTryAgainTv.setOnClickListener {
            checkNetworkConnection()
        }

        /*favorites.setOnClickListener {
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
        Log.i("preference", userId.toString())
        viewModel.aux = viewModel.gasStationsCompleteList.value ?: listOf()

        var noneIsChecked = true

        if(hasConvenienceStore.isChecked){
            hasConvenienceStore.setTextColor(resources.getColor(R.color.white))
            hasConvenienceStore.setBackgroundColor(resources.getColor(R.color.orange_dark))
            viewModel.getAllGasStationsThatHaveConvenienceStore()
            noneIsChecked = false
        }
        else {
            hasConvenienceStore.setTextColor(resources.getColor(R.color.black))
            hasConvenienceStore.setBackgroundColor(resources.getColor(R.color.white))
        }

        if(hasCarWash.isChecked) {
            hasCarWash.setTextColor(resources.getColor(R.color.white))
            hasCarWash.setBackgroundColor(resources.getColor(R.color.orange_dark))
            viewModel.getAllGasStationsThatHaveCarWash()
            noneIsChecked = false
        }
        else {
            hasCarWash.setTextColor(resources.getColor(R.color.black))
            hasCarWash.setBackgroundColor(resources.getColor(R.color.white))
        }

        if(hasCalibrator.isChecked) {
            hasCalibrator.setTextColor(resources.getColor(R.color.white))
            hasCalibrator.setBackgroundColor(resources.getColor(R.color.orange_dark))
            viewModel.getAllGasStationsThatHaveCalibrator()
            noneIsChecked = false
        }
        else {
            hasCalibrator.setTextColor(resources.getColor(R.color.black))
            hasCalibrator.setBackgroundColor(resources.getColor(R.color.white))
        }

        if(hasOilChange.isChecked) {
            hasOilChange.setTextColor(resources.getColor(R.color.white))
            hasOilChange.setBackgroundColor(resources.getColor(R.color.orange_dark))
            viewModel.getAllGasStationsThatHaveOilChange()
            noneIsChecked = false
        }
        else {
            hasOilChange.setTextColor(resources.getColor(R.color.black))
            hasOilChange.setBackgroundColor(resources.getColor(R.color.white))
        }

        if(hasTireShop.isChecked) {
            hasTireShop.setTextColor(resources.getColor(R.color.white))
            hasTireShop.setBackgroundColor(resources.getColor(R.color.orange_dark))
            viewModel.getAllGasStationsThatHaveTireShop()
            noneIsChecked = false
        }
        else {
            hasTireShop.setTextColor(resources.getColor(R.color.black))
            hasTireShop.setBackgroundColor(resources.getColor(R.color.white))
        }

        if(hasRestaurant.isChecked) {
            hasRestaurant.setTextColor(resources.getColor(R.color.white))
            hasRestaurant.setBackgroundColor(resources.getColor(R.color.orange_dark))
            viewModel.getAllGasStationsThatHaveRestaurant()
            noneIsChecked = false
        }
        else {
            hasRestaurant.setTextColor(resources.getColor(R.color.black))
            hasRestaurant.setBackgroundColor(resources.getColor(R.color.white))
        }

        if(hasMechanical.isChecked) {
            hasMechanical.setTextColor(resources.getColor(R.color.white))
            hasMechanical.setBackgroundColor(resources.getColor(R.color.orange_dark))
            viewModel.getAllGasStationsThatHaveMechanical()
            noneIsChecked = false
        }
        else {
            hasMechanical.setTextColor(resources.getColor(R.color.black))
            hasMechanical.setBackgroundColor(resources.getColor(R.color.white))
        }

        if(noneIsChecked){
            viewModel.clearGasStationFilter()
            viewModel.getAllGasStationsAndAddressAndPriceAndService()
        }
        else {
            viewModel.setFilteredList()
            viewModel.clearGasStationFilter()
        }
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
                        ).icon(BitmapDescriptorFactory.defaultMarker(30F))
                        .position(itemLatLng)
                )
                marker?.tag = item.gasStation.idGasStation
            }

        }
    }

    @SuppressLint("NewApi")
    fun isOnline(context: Context?): Boolean {
        context?.let {
            val connectivityManager =
                it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
            return false
        } ?: return false

    }

    private fun checkNetworkConnection() {
        if (isOnline(context)) {
            binding.homeGasStationMap.visibility = View.VISIBLE
            binding.homeNetworkErrorTv.visibility = View.GONE
            binding.homeNetworkTryAgainTv.visibility = View.GONE
            setupMap()
        } else {
            binding.homeGasStationMap.visibility = View.GONE
            binding.homeNetworkErrorTv.visibility = View.VISIBLE
            binding.homeNetworkTryAgainTv.visibility = View.VISIBLE
        }
    }

}