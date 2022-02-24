package non.shahad.moviecodemanagement.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import non.shahad.moviecodemanagement.R

@BindingAdapter("bindImageView")
fun simpleLoadImage(imageView: ImageView, posterUrl: String?) {
    posterUrl?.let {
        Glide.with(imageView)
            .load("https://image.tmdb.org/t/p/w154/${posterUrl}")
            .placeholder(R.drawable.ic_baseline_favorite_border_24)
            .into(imageView)
    }
}

@BindingAdapter("bindFavoriteActions")
fun bindFavoriteActions(imageView: ImageView, isFavorite: Boolean?) {
    isFavorite?.let {
        if (it){
            Glide.with(imageView)
                .load(R.drawable.ic_baseline_favorite_24)
                .into(imageView)
        } else {
            Glide.with(imageView)
                .load(R.drawable.ic_baseline_favorite_border_24)
                .into(imageView)
        }
    }
}