package non.shahad.data.service.response

import non.shahad.data.models.MovieDataModel

data class MovieResponse(
    override val page: Int,
    override val totalPages: Int,
    override val totalResults: Int,
    override val results: List<MovieDataModel>
): TMDBResponse<MovieDataModel>