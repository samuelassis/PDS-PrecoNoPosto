package com.example.preconoposto.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preconoposto.R
import com.example.preconoposto.database.AppDatabase
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.Price
import com.example.preconoposto.data.Rating
import com.example.preconoposto.data.User
import com.example.preconoposto.data.relations.GasStationAndPrice
import com.example.preconoposto.data.relations.GasStationWithRatingsAndUser
import com.example.preconoposto.data.relations.RatingAndUser
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date

class GasStationDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = GasStationDetailsFragment()
    }

    val gasStationId : Long = 1

    private lateinit var gasStationName: TextView
    private lateinit var attendanceScore: TextView
    private lateinit var qualityScore: TextView
    private lateinit var waitingTimeScore: TextView
    private lateinit var serviceScore: TextView
    private lateinit var safetyScore: TextView
    private lateinit var gasPrice: TextView
    private lateinit var gasPriceUpdateDate: TextView
    private lateinit var alcoholPrice: TextView
    private lateinit var alcoholPriceUpdateDate: TextView
    private lateinit var dieselPrice: TextView
    private lateinit var dieselPriceUpdateDate: TextView
    private lateinit var generalScore: TextView

    private lateinit var gasStationDetailsGasolineUpdateMb: MaterialButton
    private lateinit var gasStationDetailsAlcoholUpdateMb: MaterialButton
    private lateinit var gasStationDetailsDieselUpdateMb: MaterialButton

    private lateinit var userCommentsRecycleView: RecyclerView
    private lateinit var gasStationDetailsAdapter: GasStationDetailsAdapter

    private lateinit var viewModel: GasStationDetailsViewModel
    private val gasStationDao by lazy {
        AppDatabase.getInstance(this.requireContext()).gasStationDao
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gas_station_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[GasStationDetailsViewModel::class.java]

        val gasStationWithRatingsAndUser = viewModel.gasStationWithRatingsAndUser

        gasStationName = view.findViewById(R.id.gasStationDetailsGasStationNameTv)
        attendanceScore = view.findViewById(R.id.gasStationDetailsAttendanceScoreTv)
        qualityScore = view.findViewById(R.id.gasStationDetailsQualityScoreTv)
        waitingTimeScore = view.findViewById(R.id.gasStationDetailsWaitingTimeScoreTv)
        serviceScore = view.findViewById(R.id.gasStationDetailsServiceScoreTv)
        safetyScore = view.findViewById(R.id.gasStationDetailsSafetyScoreTv)
        gasPrice = view.findViewById(R.id.gasStationDetailsGasolinePriceTv)
        gasPriceUpdateDate = view.findViewById(R.id.gasStationDetailsGasolineUpdatedDateTv)
        alcoholPrice = view.findViewById(R.id.gasStationDetailsAlcoholPriceTv)
        alcoholPriceUpdateDate = view.findViewById(R.id.gasStationDetailsAlcoholUpdatedDateTv)
        dieselPrice = view.findViewById(R.id.gasStationDetailsDieselPriceTv)
        dieselPriceUpdateDate = view.findViewById(R.id.gasStationDetailsDieselUpdatedDateTv)
        generalScore = view.findViewById(R.id.gasStationDetailsGeneralScoreTv)
        userCommentsRecycleView = view.findViewById(R.id.gasStationDetailsCommentsRv)

        gasStationDetailsGasolineUpdateMb = view.findViewById(R.id.gasStationDetailsGasolineUpdateMb)
        gasStationDetailsAlcoholUpdateMb = view.findViewById(R.id.gasStationDetailsAlcoholUpdateMb)
        gasStationDetailsDieselUpdateMb = view.findViewById(R.id.gasStationDetailsDieselUpdateMb)

        CoroutineScope(Dispatchers.Default).launch {
            val gasStationAndAddressAndPriceAndService =
                gasStationDao.getGasStationWithRatingsAndUser(gasStationId)
            val gasStationAndPrice =
                gasStationDao.getGasStationAndPrice(gasStationId)

            viewModel.setGasStationWithRatingsAndUser(GasStationWithRatingsAndUser(
                gasStation = gasStationAndAddressAndPriceAndService.gasStation,
                ratings = gasStationAndAddressAndPriceAndService.ratings
            ))

            viewModel.setGasStationAndPrice(GasStationAndPrice(
                gasStation = gasStationAndPrice.gasStation,
                price = gasStationAndPrice.price
            ))
        }

        setupListeners()

        viewModel.getGasStationAndPrice().observe(viewLifecycleOwner) {
            val date =
                it?.price?.lastUpdateDate?.toString() ?: "--/--/--"

            gasPrice.text = viewModel.getGasolinePriceText()
            gasPriceUpdateDate.text = date
            alcoholPrice.text = viewModel.getAlcoholPriceText()
            alcoholPriceUpdateDate.text = date
            dieselPrice.text = viewModel.getDieselPriceText()
            dieselPriceUpdateDate.text = date
        }

        viewModel.getGasStationWithRatingsAndUser().observe(viewLifecycleOwner) {
            gasStationName.text = gasStationWithRatingsAndUser.value?.gasStation?.name ?: "Posto Selecionado"
            generalScore.text = viewModel.getGeneralScoreText()
            attendanceScore.text = viewModel.getAttendanceScoreText()
            qualityScore.text = viewModel.getQualityScoreText()
            waitingTimeScore.text = viewModel.getWaitingTimeScoreText()
            serviceScore.text = viewModel.getServiceScoreText()
            safetyScore.text = viewModel.getSafetyScoreText()

            gasStationDetailsAdapter = GasStationDetailsAdapter()
            gasStationDetailsAdapter.setComments(it.ratings)
            userCommentsRecycleView.layoutManager = LinearLayoutManager(view.context)
            userCommentsRecycleView.adapter = gasStationDetailsAdapter
        }
    }

    private fun setupListeners() {
        gasStationDetailsGasolineUpdateMb.setOnClickListener {

        }
        gasStationDetailsGasolineUpdateMb.setOnClickListener {

        }
        gasStationDetailsGasolineUpdateMb.setOnClickListener {

        }
    }


}