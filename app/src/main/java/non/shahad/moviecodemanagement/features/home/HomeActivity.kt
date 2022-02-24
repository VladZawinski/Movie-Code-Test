package non.shahad.moviecodemanagement.features.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import non.shahad.domain.model.Movie
import non.shahad.moviecodemanagement.R
import non.shahad.moviecodemanagement.base.MVIActivity
import non.shahad.moviecodemanagement.databinding.ActivityHomeBinding
import non.shahad.moviecodemanagement.databinding.ViewholderMovieBinding
import non.shahad.moviecodemanagement.features.adapters.movieItem
import non.shahad.moviecodemanagement.features.detail.DetailActivity
import non.shahad.moviecodemanagement.features.detail.MOVIE_ID_KEY
import timber.log.Timber


class HomeActivity(
    override val layoutRes: Int = R.layout.activity_home
) : MVIActivity<ActivityHomeBinding,HomeViewModel,HomeState, HomeSideEffect>() {

    private val upcomingDelegate = AsyncListDifferDelegationAdapter(
        MovieDiffItemCallback,
        movieItem(
            onClick = ::onItemClick,
            onFavorite = {
                viewModel.actionFavorite(it)
            }
        )
    )

    private val popularDelegate = AsyncListDifferDelegationAdapter(
        MovieDiffItemCallback,
        movieItem(
            onClick = ::onItemClick,
            onFavorite = {
                viewModel.actionFavorite(it)
            }
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.popularRv.apply {
            adapter = popularDelegate
            setHasFixedSize(true)
        }
        viewBinding.upComingRv.apply {
            adapter = upcomingDelegate
            setHasFixedSize(true)
        }

        viewBinding.swiper.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override val viewModel: HomeViewModel
        by viewModels()

    override fun handleSideEffect(sideEffect: HomeSideEffect) {
        when(sideEffect){
            is HomeSideEffect.ShowSnackBar -> {

            }
        }
    }

    override fun render(state: HomeState) {
        viewBinding.swiper.isRefreshing = state.isLoading

        popularDelegate.items = state.popularMovies
        upcomingDelegate.items = state.upcomingMovies
    }

    private fun onItemClick(movie: Movie){
        val i = Intent(this,DetailActivity::class.java).apply {
            putExtra(MOVIE_ID_KEY,movie.id)
        }
        startActivity(i)
    }
}

object MovieDiffItemCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.isFavorite == newItem.isFavorite && oldItem.title == newItem.title
    }

}

