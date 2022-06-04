package com.example.preconoposto.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.preconoposto.R
import com.example.preconoposto.data.Rating
import com.example.preconoposto.database.AppDatabase
import com.example.preconoposto.databinding.FragmentEvaluateGasStationBinding
import com.example.preconoposto.databinding.FragmentLoginBinding
import com.example.preconoposto.domain.GasStationRatingImpl
import com.example.preconoposto.domain.UserAccessImpl
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.sql.Date
import java.sql.Timestamp
import kotlin.math.roundToLong

class EvaluateGasStationFragment : Fragment() {

    companion object {
        fun newInstance() = EvaluateGasStationFragment()
    }

    var gasStationId: Long = 1
    var userId: Long = 1

    private lateinit var generalScore: EditText
    private lateinit var attendanceScore: EditText
    private lateinit var qualityScore: EditText
    private lateinit var waitingTimeScore: EditText
    private lateinit var serviceScore: EditText
    private lateinit var safetyScore: EditText
    private lateinit var commentary: TextInputEditText
    private lateinit var evaluateButton: MaterialButton

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
        setupViews(view)
        setupObservers()
        setupListeners()
    }

    private fun setupViews(view: View) {
        generalScore = view.findViewById(R.id.loginEnterButton)
        attendanceScore = view.findViewById(R.id.evaluateGasStationAttendanceScoreEt)
        qualityScore = view.findViewById(R.id.evaluateGasStationQualityScoreEt)
        waitingTimeScore = view.findViewById(R.id.evaluateGasStationWaitingTimeScoreEt)
        serviceScore = view.findViewById(R.id.evaluateGasStationServiceScoreEt)
        safetyScore = view.findViewById(R.id.evaluateGasStationSafetyScoreEt)
        commentary = view.findViewById(R.id.evaluateGasStationCommentTiet)
        evaluateButton = view.findViewById(R.id.evaluateGasStationEvaluateMb)
    }

    private fun setupObservers(){
        viewModel.scoresAreValid.observe(viewLifecycleOwner){
            if(it == true){
                Toast.makeText(
                    this.requireContext(),
                    "Comentário feito com sucesso",
                    Toast.LENGTH_SHORT
                ).show()
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
        evaluateButton.setOnClickListener {
            viewModel.saveRating(Rating(
                idGasStation = gasStationId,
                idUser = userId,
                generalScore = generalScore.text.toString().toDouble(),
                attendanceScore = attendanceScore.text.toString().toDouble(),
                qualityScore = qualityScore.text.toString().toDouble(),
                waitingTimeScore = waitingTimeScore.text.toString().toDouble(),
                serviceScore = serviceScore.text.toString().toDouble(),
                safetyScore = safetyScore.text.toString().toDouble(),
                commentary = commentary.text.toString(),
                date = Date(System.currentTimeMillis())
            ))
        }
    }

}