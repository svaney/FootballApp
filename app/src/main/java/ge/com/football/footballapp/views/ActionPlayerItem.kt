package ge.com.football.footballapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ge.com.football.footballapp.R
import ge.com.football.footballapp.model.ModeTeamAction
import org.w3c.dom.Text

class ActionPlayerItem : LinearLayout {

    private lateinit var playerImage: ImageView
    private lateinit var playerName: TextView
    private lateinit var actionTime: TextView
    private lateinit var actionImage: ImageView

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        View.inflate(getContext(), R.layout.action_player_item, this)

        playerImage = findViewById(R.id.playerImage)
        playerName = findViewById(R.id.playerName)
        actionTime = findViewById(R.id.actionTime)
        actionImage = findViewById(R.id.actionImage)
    }

    fun setUpItem(isLeftGravity: Boolean, playerNameText: String, timeText: String, imageurl: String, actionImageRes: Int) {
        gravity = if (isLeftGravity) Gravity.LEFT else Gravity.RIGHT

        actionTime.text = timeText
        playerName.text = playerNameText
        actionImage.setImageResource(actionImageRes)

        Glide.with(this)
            .load(imageurl)
            .apply(RequestOptions.circleCropTransform())
            .into(playerImage)

    }

}
