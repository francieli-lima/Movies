package br.com.francielilima.movies.modules.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.francielilima.movies.R
import br.com.francielilima.movies.models.Movie
import br.com.francielilima.movies.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_details.*

class DetailsActivity: AppCompatActivity() {

    private var viewModel: DetailsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_details)

        viewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        viewModel?.extras = intent.extras

        setup()
    }

    //region Private

    private fun setup() {
        registerObservables()
    }

    private fun registerObservables() {
        viewModel?.movieDetailsData?.observe(this, Observer {
            it?.let {
                setupViews(it)
            }
        })
    }

    private fun setupViews(movie: Movie) {
        Picasso.with(this)
                .load("${Constants.URLs.bigImageURL}${movie.backgroundPath}")
                .into(imageViewBackground)

        Picasso.with(this)
                .load("${Constants.URLs.mediumImageURL}${movie.posterPath}")
                .into(imageViewPoster)


    }

    //endregion
}
