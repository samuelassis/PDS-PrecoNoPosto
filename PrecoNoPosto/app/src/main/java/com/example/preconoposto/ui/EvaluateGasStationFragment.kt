package com.example.preconoposto.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.preconoposto.data.Rating
import com.example.preconoposto.database.AppDatabase
import com.example.preconoposto.databinding.FragmentEvaluateGasStationBinding
import com.example.preconoposto.domain.GasStationRatingImpl
import java.sql.Date

class EvaluateGasStationFragment : Fragment() {

    var userId: Long = 1

    private val args: EvaluateGasStationFragmentArgs by navArgs()

    lateinit var binding: FragmentEvaluateGasStationBinding

    private val ratingDao by lazy {
        AppDatabase.getInstance(this.requireContext()).ratingDao
    }

    private lateinit var viewModel: EvaluateGasStationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[EvaluateGasStationViewModel::class.java]
        viewModel.gasStationRatingImpl = GasStationRatingImpl(ratingDao)

        binding = FragmentEvaluateGasStationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
    }

    private fun setupObservers(){
        viewModel.scoresAreValid.observe(viewLifecycleOwner){
            if(it == true){
                Toast.makeText(
                    this.requireContext(),
                    "Comentário feito com sucesso",
                    Toast.LENGTH_SHORT
                ).show()
                requireActivity().onBackPressed()
            }
            else{
                Toast.makeText(
                    this.requireContext(),
                    "Erro: Nota inválida",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setupListeners(){
        binding.evaluateGasStationEvaluateMb.setOnClickListener {
            viewModel.saveRating(Rating(
                idGasStation = args.gasStationId,
                idUser = userId,
                generalScore = setTextToDouble(binding.evaluateGasStationGeneralScoreEt.text.toString()),
                attendanceScore = setTextToDouble(binding.evaluateGasStationAttendanceScoreEt.text.toString()),
                qualityScore = setTextToDouble(binding.evaluateGasStationQualityScoreEt.text.toString()),
                waitingTimeScore = setTextToDouble(binding.evaluateGasStationWaitingTimeScoreEt.text.toString()),
                serviceScore = setTextToDouble(binding.evaluateGasStationServiceScoreEt.text.toString()),
                safetyScore = setTextToDouble(binding.evaluateGasStationSafetyScoreEt.text.toString()),
                commentary = binding.evaluateGasStationCommentTiet.text.toString(),
                date = Date(System.currentTimeMillis())
            ))
        }
    }

    private fun setTextToDouble(text: String): Double {
        return if (text.isNullOrEmpty()) {
            0.0
        } else {
            text.toDouble()
        }
    }

}