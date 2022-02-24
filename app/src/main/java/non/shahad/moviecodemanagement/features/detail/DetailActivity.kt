package non.shahad.moviecodemanagement.features.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import non.shahad.moviecodemanagement.R
import non.shahad.moviecodemanagement.base.MVIActivity
import non.shahad.moviecodemanagement.databinding.ActivityDetailBinding
import timber.log.Timber

const val MOVIE_ID_KEY = "movie-id"

class DetailActivity(
    override val layoutRes: Int = R.layout.activity_detail
) : MVIActivity<ActivityDetailBinding,DetailViewModel, DetailState, DetailSideEffect>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.lifecycleOwner = this
        intent.extras?.getInt(MOVIE_ID_KEY)?.also {
            viewModel.fetch(it)
        }

        viewBinding.heartBtn.setOnClickListener {
            viewModel.actionFavorite()
        }
    }

    override val viewModel: DetailViewModel
        by viewModels()

    override fun handleSideEffect(sideEffect: DetailSideEffect) {
        when(sideEffect){
            is DetailSideEffect.ShowSnackBar -> {

            }
        }
    }

    override fun render(state: DetailState) {
        if (state.isLoading){
            // Show some loading spinner
        } else {
            // Hide that
        }

        viewBinding.movie = state.movie
    }
}