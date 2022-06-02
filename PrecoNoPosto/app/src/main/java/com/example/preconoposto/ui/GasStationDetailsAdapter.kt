package com.example.preconoposto.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.preconoposto.R
import com.example.preconoposto.data.relations.GasStationWithRatingsAndUser
import com.example.preconoposto.data.relations.RatingAndUser
import com.google.android.material.imageview.ShapeableImageView

class GasStationDetailsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var comments: List<RatingAndUser> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserCommentsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.res_user_comment, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is UserCommentsViewHolder -> {
                holder.bind(comments[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    fun setComments(comments: List<RatingAndUser>){
        this.comments = comments
    }

    class UserCommentsViewHolder(
        userCommentView: View
    ) : RecyclerView.ViewHolder(userCommentView) {

        private val resUserCommentDateTv = userCommentView.findViewById<TextView>(R.id.resUserCommentDateTv)
        private val resUserCommentAvatarSip = userCommentView.findViewById<ShapeableImageView>(R.id.resUserCommentAvatarSip)
        private val resUserCommentUserNameTv = userCommentView.findViewById<TextView>(R.id.resUserCommentUserNameTv)
        private val resUserCommentUserCommentaryTv = userCommentView.findViewById<TextView>(R.id.resUserCommentUserCommentaryTv)
        private val resUserCommentAttendanceScoreTv = userCommentView.findViewById<TextView>(R.id.resUserCommentAttendanceScoreTv)
        private val resUserCommentQualityScoreTv = userCommentView.findViewById<TextView>(R.id.resUserCommentQualityScoreTv)
        private val resUserCommentWaitingTimeScoreTextTv = userCommentView.findViewById<TextView>(R.id.resUserCommentWaitingTimeScoreTv)
        private val resUserCommentServiceScoreTv = userCommentView.findViewById<TextView>(R.id.resUserCommentServiceScoreTv)
        private val resUserCommentSafetyScoreTv = userCommentView.findViewById<TextView>(R.id.resUserCommentSafetyScoreTv)

        @SuppressLint("SetTextI18n")
        fun bind(userComment: RatingAndUser){
            resUserCommentDateTv.text = userComment.rating.date.toString()
            Glide.with(this.itemView).load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHUvOd8Q-VihyupbJCdgjIR2FxnjGtAgMu3g&usqp=CAU").into(resUserCommentAvatarSip)
            resUserCommentUserNameTv.text = userComment.user.name
            resUserCommentUserCommentaryTv.text = userComment.rating.commentary
            resUserCommentAttendanceScoreTv.text = "%.1f".format(userComment.rating.attendanceScore)+"/5.0"
            resUserCommentQualityScoreTv.text = "%.1f".format(userComment.rating.qualityScore)+"/5.0"
            resUserCommentWaitingTimeScoreTextTv.text = "%.1f".format(userComment.rating.waitingTimeScore)+"/5.0"
            resUserCommentServiceScoreTv.text = "%.1f".format(userComment.rating.serviceScore)+"/5.0"
            resUserCommentSafetyScoreTv.text = "%.1f".format(userComment.rating.safetyScore)+"/5.0"
        }

    }

}