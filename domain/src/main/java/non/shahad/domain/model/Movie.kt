package non.shahad.domain.model

data class Movie(
    val id: Int,
    val voteAverage: Double?,
    val title: String?,
    val posterPath: String?,
    val isFavorite: Boolean,
    val query: String,
    val lastUpdateTimestamp: Long
)