package non.shahad.moviecodemanagement.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("bindImageView")
fun simpleLoadImage(imageView: ImageView, posterUrl: String?) {
    posterUrl?.let {
        imageView.load("https://image.tmdb.org/t/p/w500/${posterUrl}")
    }
}