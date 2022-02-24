package non.shahad.moviecodemanagement.features.adapters

import androidx.databinding.DataBindingUtil
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import non.shahad.domain.model.Movie
import non.shahad.moviecodemanagement.R
import non.shahad.moviecodemanagement.databinding.ViewholderMovieBinding

fun movieItem(
    onClick: (Movie) -> Unit,
    onFavorite: (Movie) -> Unit
) = adapterDelegateViewBinding<Movie, Movie, ViewholderMovieBinding>(
    { inflater,root -> DataBindingUtil.inflate(inflater, R.layout.viewholder_movie,root,false) }
){
    bind {
        binding.movie = item
        binding.root.setOnClickListener {
            onClick(item)
        }
        binding.favoriteBtn.setOnClickListener {
            onFavorite(item)
        }
    }
}