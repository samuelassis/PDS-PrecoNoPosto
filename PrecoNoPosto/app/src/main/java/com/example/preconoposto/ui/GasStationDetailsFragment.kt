package com.example.preconoposto.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preconoposto.R
import com.example.preconoposto.database.AppDatabase
import com.example.preconoposto.domain.GasStationDetailsImpl
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton

class GasStationDetailsFragment : Fragment() {

    private val args: GasStationDetailsFragmentArgs by navArgs()

    private var gasStationId: Long = 0L

    private lateinit var gasStationToolBar: MaterialToolbar
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gasStationId = args.gasStationId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_gas_station_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[GasStationDetailsViewModel::class.java]
        viewModel.gasStationDetailsImpl = GasStationDetailsImpl(gasStationDao)

        gasStationToolBar = view.findViewById(R.id.gasStationsDetailsToolbar)

        generalScore = view.findViewById(R.id.gasStationDetailsGeneralScoreTv)
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

        gasStationDetailsGasolineUpdateMb =
            view.findViewById(R.id.gasStationDetailsGasolineUpdateMb)
        gasStationDetailsAlcoholUpdateMb = view.findViewById(R.id.gasStationDetailsAlcoholUpdateMb)
        gasStationDetailsDieselUpdateMb = view.findViewById(R.id.gasStationDetailsDieselUpdateMb)

        userCommentsRecycleView = view.findViewById(R.id.gasStationDetailsCommentsRv)

        setupListeners(view)
        setupObservers(view)
    }

    private fun setupListeners(view: View) {
        gasStationDetailsGasolineUpdateMb.setOnClickListener {

        }
        gasStationDetailsGasolineUpdateMb.setOnClickListener {

        }
        gasStationDetailsGasolineUpdateMb.setOnClickListener {

        }
        gasStationToolBar.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupObservers(view: View) {
        viewModel.getPriceTexts(gasStationId).observe(viewLifecycleOwner) { map ->
            val date = map.getValue("date")

            gasPrice.text = map.getValue("gasPrice")
            gasPriceUpdateDate.text = date
            alcoholPrice.text = map.getValue("alcoholPrice")
            alcoholPriceUpdateDate.text = date
            dieselPrice.text = map.getValue("dieselPrice")
            dieselPriceUpdateDate.text = date
        }

        viewModel.getScoreAverageTexts(gasStationId).observe(viewLifecycleOwner) { map ->
            generalScore.text = map.getValue("generalScore")
            attendanceScore.text = map.getValue("attendanceScore")
            qualityScore.text = map.getValue("qualityScore")
            waitingTimeScore.text = map.getValue("waitingTimeScore")
            serviceScore.text = map.getValue("serviceScore")
            safetyScore.text = map.getValue("safetyScore")
        }

        viewModel.getGasStationWithRatingsAndUser(gasStationId)
            .observe(viewLifecycleOwner) { gasStationWithRatingsAndUser ->
                gasStationToolBar.title = gasStationWithRatingsAndUser.gasStation.name
                gasStationWithRatingsAndUser.ratings?.let { ratingAndUserList ->
                    gasStationDetailsAdapter = GasStationDetailsAdapter()
                    gasStationDetailsAdapter.setComments(ratingAndUserList)
                    userCommentsRecycleView.layoutManager = LinearLayoutManager(view.context)
                    userCommentsRecycleView.adapter = gasStationDetailsAdapter
                }
            }
    }
}