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
import com.example.preconoposto.data.GasStation
import com.example.preconoposto.data.Price
import com.example.preconoposto.data.Rating
import com.example.preconoposto.data.User
import com.example.preconoposto.data.relations.GasStationAndPrice
import com.example.preconoposto.data.relations.GasStationWithRatingsAndUser
import com.example.preconoposto.data.relations.RatingAndUser
import com.google.android.material.imageview.ShapeableImageView
import java.sql.Date

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[GasStationDetailsViewModel::class.java]

        val gasStation = GasStation(
            idGasStation = 0,
            idAddress = 0,
            idService = 0,
            idRating = 0,
            idPrice = 0,
            name = "Posto Ipiranga Anel 2003"
        )
        val price = Price(
            idPrice = 0,
            idGasStation = 0,
            gasolinePrice = 5.165,
            alcoholPrice = 4.354,
            dieselPrice = 4.665,
            lastUpdateDate = Date(20190731)
        )
        val rating = Rating(
            idRating = 0,
            idGasStation = 0,
            idUser = 0,
            generalScore = 4.7,
            attendanceScore = 4.3,
            qualityScore = 4.1,
            waitingTimeScore = 4.9,
            serviceScore = 3.8,
            safetyScore = 5.0,
            commentary = "Este é um exemplo de comentário feito por um usuário qualquer",
            date = Date(20190731)
        )
        val rating2 = Rating(
            idRating = 1,
            idGasStation = 0,
            idUser = 1,
            generalScore = 4.5,
            attendanceScore = 4.5,
            qualityScore = 4.3,
            waitingTimeScore = 4.7,
            serviceScore = 4.0,
            safetyScore = 4.8,
            commentary = "Exemplo de comentário muito grande: lalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalalala",
            date = Date(20190731)
        )
        val user = User(
            idUser = 0,
            name = "Fábio Brum",
            email = "email@gmail.com",
            password = "123456",
            birthday = "31/12/1999"
        )
        val user2 = User(
            idUser = 1,
            name = "Emerson Gouveia",
            email = "email@gmail.com",
            password = "123456",
            birthday = "31/12/1999"
        )
        val listOfRatingsAndUser = listOf(
            RatingAndUser(
                rating = rating,
                user = user
            ),
            RatingAndUser(
                rating = rating2,
                user = user2
            )
        )

        viewModel.setGasStationWithRatingsAndUser(GasStationWithRatingsAndUser(
            gasStation = gasStation,
            ratings = listOfRatingsAndUser
        ))

        viewModel.setGasStationAndPrice(GasStationAndPrice(
            gasStation = gasStation,
            price = price
        ))

        val gasStationWithRatingsAndUser = viewModel._gasStationWithRatingsAndUser

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
}