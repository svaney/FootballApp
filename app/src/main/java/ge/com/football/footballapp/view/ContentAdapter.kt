package ge.com.football.footballapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ge.com.football.footballapp.R
import ge.com.football.footballapp.model.*
import kotlinx.android.synthetic.main.list_item_half_header.view.*
import kotlinx.android.synthetic.main.list_item_match_header.view.*
import android.opengl.ETC1.getWidth
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.RelativeLayout


class ContentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_MATCH_HEADER = 1
        private const val VIEW_TYPE_HALF_HEADER = 2
        private const val VIEW_TYPE_ACTION = 3
    }

    private lateinit var modelMatch: ModelMatch


    fun setModelMatch(modelMatch: ModelMatch) {
        this.modelMatch = modelMatch
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> VIEW_TYPE_MATCH_HEADER
            isHalfHeaderPosition(position) -> VIEW_TYPE_HALF_HEADER
            else -> VIEW_TYPE_ACTION
        }
    }

    private fun isHalfHeaderPosition(adapterPosition: Int): Boolean {
        return adapterPosition == 1 || adapterPosition - 2 == modelMatch.getFirstHalfSummery().size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MATCH_HEADER -> createMatchHeaderViewHolder(parent)
            VIEW_TYPE_ACTION -> createActionViewHolder(parent)
            else -> createHalfHeaderViewHolder(parent)
        }
    }

    private fun createMatchHeaderViewHolder(parent: ViewGroup): MatchHeaderViewHolder {
        return MatchHeaderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_match_header,
                parent,
                false
            )
        )
    }

    private fun createHalfHeaderViewHolder(parent: ViewGroup): HalfHeaderViewHolder {
        return HalfHeaderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_half_header,
                parent,
                false
            )
        )
    }

    private fun createActionViewHolder(parent: ViewGroup): ActionViewHolder {
        return ActionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_action,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        // match header + first Half header + second half header
        var size = 3

        size += modelMatch.getFirstHalfSummery().size

        size += modelMatch.getSecondHalfSummery().size

        return size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_MATCH_HEADER -> {
                bindHeaderItem(
                    matchTime = modelMatch.getDisplayedTime(),
                    matchDate = modelMatch.getDisplayedDate(),
                    stadiumAdress = modelMatch.stadiumAdress,
                    team1 = modelMatch.team1,
                    team2 = modelMatch.team2,
                    matchScore = modelMatch.getMatchScore(),
                    viewHolder = holder as MatchHeaderViewHolder
                )
            }

            VIEW_TYPE_HALF_HEADER -> {
                bindHalfStats(
                    if (position == 1) 1 else 2,
                    modelMatch.getDisplayedScoreByHalf(if (position == 1) 1 else 2),
                    holder as HalfHeaderViewHolder
                )
            }

            VIEW_TYPE_ACTION -> {
                bindActionItem(
                    getSummeryByAdapterPosition(position),
                    holder as ActionViewHolder
                )
            }
        }
    }

    private fun getSummeryByAdapterPosition(position: Int): ModelSummary {
        if (position - 2 < modelMatch.getFirstHalfSummery().size) {
            return modelMatch.getFirstHalfSummery()[position - 2]
        }
        return modelMatch.getSecondHalfSummery()[position - 3 - modelMatch.getFirstHalfSummery().size]
    }

    private fun bindHeaderItem(
        matchTime: String,
        matchDate: String,
        stadiumAdress: String,
        matchScore: String,
        team1: ModelTeam,
        team2: ModelTeam,
        viewHolder: MatchHeaderViewHolder
    ) {
        viewHolder.dateTv.text = matchDate
        viewHolder.mathTimeTv.text = matchTime
        viewHolder.stadiumTv.text = stadiumAdress
        viewHolder.scoreTv.text = matchScore

        viewHolder.teamOneNameTv.text = team1.teamName
        viewHolder.teamOneBallTv.text = team1.getBallPosDisplayedText()

        viewHolder.teamTwoNameTv.text = team2.teamName
        viewHolder.teamTwoBallTv.text = team2.getBallPosDisplayedText()


        Glide.with(viewHolder.itemView.context)
            .load(team1.teamImage)
            .apply(RequestOptions.circleCropTransform())
            .into(viewHolder.teamOneIv)

        Glide.with(viewHolder.itemView.context)
            .load(team2.teamImage)
            .apply(RequestOptions.circleCropTransform())
            .into(viewHolder.teamTwoIv)
    }

    private fun bindHalfStats(half: Int, halfScore: String , viewHolder: HalfHeaderViewHolder) {
        viewHolder.halfTv.text = "$half Half"
        viewHolder.halfScore.text = halfScore
    }

    private fun bindActionItem(summary: ModelSummary, viewHolder: ActionViewHolder) {

    }

    internal inner class MatchHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTv = itemView.dateTextView
        val stadiumTv = itemView.stadiumNameTextView
        val scoreTv = itemView.scoreTextView
        val mathTimeTv = itemView.timeTextView
        val teamOneIv = itemView.teamOneImage
        val teamTwoIv = itemView.teamTwoImage
        val teamOneBallTv = itemView.teamOneBallPos
        val teamTwoBallTv = itemView.teamTwoBallPos
        val teamOneNameTv = itemView.teamOneName
        val teamTwoNameTv = itemView.teamTwoName
    }

    internal inner class HalfHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val halfTv = itemView.halfTextView
        val halfScore = itemView.halfScoreTextView
    }

    internal inner class ActionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //    val team1Logo = itemView.
    }


}
