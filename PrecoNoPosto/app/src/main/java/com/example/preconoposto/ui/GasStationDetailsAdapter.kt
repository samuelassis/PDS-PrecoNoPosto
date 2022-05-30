package com.example.preconoposto.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.preconoposto.R
import com.google.android.material.imageview.ShapeableImageView

class GasStationDetailsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // private val comments: List<GasStationWithRatingsAndUser>

    private var comments: List<String> = ArrayList()

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

    fun setComments(comments: List<String>){
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
        private val resUserCommentWaitingTimeScoreTextTv = userCommentView.findViewById<TextView>(R.id.resUserCommentWaitingTimeScoreTextTv)
        private val resUserCommentServiceScoreTv = userCommentView.findViewById<TextView>(R.id.resUserCommentServiceScoreTv)
        private val resUserCommentSafetyScoreTv = userCommentView.findViewById<TextView>(R.id.resUserCommentSafetyScoreTv)

        fun bind(userComment: String){
            resUserCommentDateTv.text = userComment
            Glide.with(this.itemView).load("https://midias.jornalcruzeiro.com.br/wp-content/uploads/2019/06/980-20.jpg").into(resUserCommentAvatarSip)
            resUserCommentUserNameTv.text = userComment
            resUserCommentUserCommentaryTv.text = "Lorem ipsum dolor sit amet. Quo aspernatur incidunt ut dignissimos galisum et culpa galisum At dolorem quasi est consequuntur quidem. Ad ullam sint ea quasi quia nam sapiente nesciunt et laudantium iusto. Aut aliquid fuga qui velit ipsum qui nemo nisi sit minus voluptatem cum maiores exercitationem et earum labore qui magnam itaque."
            resUserCommentAttendanceScoreTv.text = "1.5/1.5"
            resUserCommentQualityScoreTv.text = "2.0/2.0"
            resUserCommentWaitingTimeScoreTextTv.text = "2.5/2.5"
            resUserCommentServiceScoreTv.text = "3.0/3.0"
            resUserCommentSafetyScoreTv.text = "4.0/4.0"
        }

    }

}