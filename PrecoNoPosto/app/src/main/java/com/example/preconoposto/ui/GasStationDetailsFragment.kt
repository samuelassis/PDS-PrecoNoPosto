package com.example.preconoposto.ui

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.preconoposto.R
import com.example.preconoposto.database.AppDatabase
import com.example.preconoposto.domain.GasStationDetailsImpl
import com.example.preconoposto.ui.viewmodels.GasStationDetailsViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView

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
    private lateinit var gasStationDetailsAvatarSiv: ShapeableImageView

    private lateinit var gasStationDetailsGasolineUpdateMb: MaterialButton
    private lateinit var gasStationDetailsAlcoholUpdateMb: MaterialButton
    private lateinit var gasStationDetailsDieselUpdateMb: MaterialButton

    private lateinit var gasStationDetailsEvaluateMb: MaterialButton

    private lateinit var userCommentsRecycleView: RecyclerView
    private lateinit var gasStationDetailsAdapter: GasStationDetailsAdapter

    private lateinit var viewModel: GasStationDetailsViewModel
    private val gasStationDao by lazy {
        AppDatabase.getInstance(this.requireContext()).gasStationDao
    }
    private val favoriteDao by lazy {
        AppDatabase.getInstance(this.requireContext()).favoriteDao
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
        viewModel.gasStationDetailsImpl = GasStationDetailsImpl(favoriteDao, gasStationDao)

        gasStationToolBar = view.findViewById(R.id.gasStationsDetailsToolbar)

        gasStationDetailsAvatarSiv = view.findViewById(R.id.gasStationDetailsAvatarSiv)
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

        gasStationDetailsEvaluateMb = view.findViewById(R.id.gasStationDetailsEvaluateMb)

        userCommentsRecycleView = view.findViewById(R.id.gasStationDetailsCommentsRv)

        Glide.with(this)
            .load("https://site.zuldigital.com.br/blog/wp-content/uploads/2020/09/shutterstock_339529217_Easy-Resize.com_.jpg")
            .into(gasStationDetailsAvatarSiv)
        setupListeners(view)
        setupObservers(view)
    }

    private fun setupListeners(view: View) {
        gasStationDetailsGasolineUpdateMb.setOnClickListener {
            showdialog("Gasolina")
        }
        gasStationDetailsAlcoholUpdateMb.setOnClickListener {
            showdialog("Alcool")
        }
        gasStationDetailsDieselUpdateMb.setOnClickListener {
            showdialog("Diesel")
        }
        gasStationDetailsEvaluateMb.setOnClickListener {
            val action =
                GasStationDetailsFragmentDirections.fromGasStationDetailsFragmentToEvaluateGasStationFragment(
                    gasStationId)
            view.findNavController().navigate(action)
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

        viewModel.priceGasoline.observe(viewLifecycleOwner) {
            gasPrice.text = it
            gasPriceUpdateDate.text = viewModel.getTodayDateTime()
        }
        viewModel.priceAlcohol.observe(viewLifecycleOwner) {
            alcoholPrice.text = it
            alcoholPriceUpdateDate.text = viewModel.getTodayDateTime()
        }
        viewModel.priceDiesel.observe(viewLifecycleOwner) {
            dieselPrice.text = it
            dieselPriceUpdateDate.text = viewModel.getTodayDateTime()
        }
    }

    fun showdialog(item: String) {
        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(requireContext())
        builder.setTitle(item)

    // Set up the input
        val input = EditText(requireContext())
    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setHint("Insira o valor (R$/Litro)")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

    // Set up the buttons
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            viewModel.setNewPrice(item, input.text.toString())
        })
        builder.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }
}