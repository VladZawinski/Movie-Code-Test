package non.shahad.data.mappers

import non.shahad.data.cache.entities.MovieEntity
import non.shahad.data.models.MovieDataModel
import non.shahad.domain.model.Movie
import java.time.Duration
import java.util.*

/**
 * Cache lifetime is 30 minutes
 */
suspend fun MovieDataModel.toEntity(query: String) =
    MovieEntity(
        id = id,
        voteAverage = vote_average,
        title = title,
        posterPath = poster_path,
        isFavorite = false,
        query = query,
        lastUpdateTimestamp = Date().time + Duration.ofMinutes(30).toMillis()
    )

suspend fun Movie.toEntity() =
    MovieEntity(
        id = id,
        voteAverage = voteAverage,
        title = title,
        posterPath = posterPath,
        isFavorite = isFavorite,
        query = query,
        lastUpdateTimestamp = Date().time + Duration.ofMinutes(30).toMillis()
    )

suspend fun Movie.toEntity(q: String) =
    MovieEntity(
        id = id,
        voteAverage = voteAverage,
        title = title,
        posterPath = posterPath,
        isFavorite = isFavorite,
        query = q,
        lastUpdateTimestamp = Date().time + Duration.ofMinutes(30).toMillis()
    )

suspend fun MovieDataModel.toDomainModel(query: String) =
    Movie(
        id = id,
        voteAverage = vote_average,
        title = title,
        posterPath = poster_path,
        isFavorite = false,
        query = query,
        lastUpdateTimestamp = Date().time + Duration.ofMinutes(30).toMillis()
    )

suspend fun MovieEntity.toDomainModel() =
    Movie(
        id = id,
        voteAverage = voteAverage,
        title = title,
        posterPath = posterPath,
        isFavorite = isFavorite,
        query = query,
        lastUpdateTimestamp = Date().time + Duration.ofMinutes(30).toMillis()
    )