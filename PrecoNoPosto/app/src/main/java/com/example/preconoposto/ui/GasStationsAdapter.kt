package com.example.preconoposto.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.preconoposto.R
import com.example.preconoposto.data.relations.GasStationAndPrice
import com.example.preconoposto.data.relations.GasStationWithRatings
import com.example.preconoposto.data.relations.GasStationWithRatingsAndUser
import com.example.preconoposto.data.relations.RatingAndUser
import com.google.android.material.imageview.ShapeableImageView

class GasStationAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var gasStations: List<GasStationAndPrice> = ArrayList()
    private var generalScores: List<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GasStationsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.res_gas_stations_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is GasStationsViewHolder -> {
                holder.bind(gasStations[position], generalScores[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return gasStations.size
    }

    fun setgasStations(gasStations: List<GasStationAndPrice>,generalScores: List<String>){
        this.gasStations = gasStations
        this.generalScores = generalScores
    }

    class GasStationsViewHolder(
        gasStationView: View
    ) : RecyclerView.ViewHolder(gasStationView) {

        private val gasStationsListAvatarSip = gasStationView.findViewById<ShapeableImageView>(R.id.resGasStationsListAvatarSip)
        private val gasStationsListGasStationNameTv = gasStationView.findViewById<TextView>(R.id.resGasStationsNameTv)
        private val resGasStationsListGeneralScoreTv = gasStationView.findViewById<TextView>(R.id.resGasStationsListGeneralScoreTv)
        private val resGasStationsListGasolineTv = gasStationView.findViewById<TextView>(R.id.resGasStationsListGasolineTv)
        private val resGasStationsListAlcoholTv = gasStationView.findViewById<TextView>(R.id.resGasStationsListAlcoholTv)
        private val resGasStationsListDieselTv = gasStationView.findViewById<TextView>(R.id.resGasStationsListDieselTv)

        @SuppressLint("SetTextI18n")
        fun bind(gasStation: GasStationAndPrice, generalScore: String){
            Glide.with(this.itemView).load("https://site.zuldigital.com.br/blog/wp-content/uploads/2020/09/shutterstock_339529217_Easy-Resize.com_.jpg").into(gasStationsListAvatarSip)
            gasStationsListGasStationNameTv.text = gasStation.gasStation.name
            resGasStationsListGeneralScoreTv.text = generalScore
            resGasStationsListGasolineTv.text = "R$ %.3f".format(gasStation.price?.gasolinePrice ?: "0.000")+"/L"
            resGasStationsListAlcoholTv.text = "R$ %.3f".format(gasStation.price?.alcoholPrice ?: "0.000")+"/L"
            resGasStationsListDieselTv.text = "R$ %.3f".format(gasStation.price?.dieselPrice ?: "0.000")+"/L"
        }

    }

}