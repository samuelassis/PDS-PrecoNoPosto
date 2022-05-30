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
import com.google.android.material.imageview.ShapeableImageView

class GasStationDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = GasStationDetailsFragment()
    }

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
    private lateinit var userCommentsRecycleView: RecyclerView
    private lateinit var gasStationDetailsAdapter: GasStationDetailsAdapter

    private lateinit var viewModel: GasStationDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gas_station_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GasStationDetailsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val price = "0,000L"
        val score = "4.6/5.0"
        val date = "31/02/22"
        val name = "Posto Ipiranga"
        val comments = ArrayList<String>()
        comments.add("Comentario A")
        comments.add("Comentario B")
        comments.add("Comentario C")
        comments.add("Comentario D")
        comments.add("Comentario E")
        comments.add("Comentario F")

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

        gasStationName.text = name
        attendanceScore.text = score
        qualityScore.text = score
        waitingTimeScore.text = score
        serviceScore.text = score
        safetyScore.text = score
        gasPrice.text = price
        gasPriceUpdateDate.text = date
        alcoholPrice.text = price
        alcoholPriceUpdateDate.text = date
        dieselPrice.text = price
        dieselPriceUpdateDate.text = date
        generalScore.text = score
        gasStationDetailsAdapter = GasStationDetailsAdapter()
        gasStationDetailsAdapter.setComments(comments)

        userCommentsRecycleView.layoutManager = LinearLayoutManager(view.context)
        userCommentsRecycleView.adapter = gasStationDetailsAdapter

    }
}