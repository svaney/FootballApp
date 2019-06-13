package ge.com.football.footballapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import ge.com.football.footballapp.R
import ge.com.football.footballapp.base.MatchViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MatchViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var retryButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)

        findViews()

        observeViewModel()

    }

    private fun findViews() {
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        retryButton = findViewById(R.id.retryButton)

        retryButton.setOnClickListener(viewModel.retryClickListener)
    }

    private fun observeViewModel() {
        viewModel.matchLoading.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
            recyclerView.visibility = if (it) View.GONE else View.VISIBLE
        })

        viewModel.matchErrorTextId.observe(this, Observer {
            if (it != null){
                retryButton.visibility =  View.VISIBLE
                Toast.makeText(this, getString(it), Toast.LENGTH_SHORT).show()
            } else {
                retryButton.visibility =  View.GONE
            }
        })

        viewModel.matchInfo.observe(this, Observer {
            if (it != null){

            }
        })
    }
}
