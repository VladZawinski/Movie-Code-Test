package non.shahad.data.service

import non.shahad.data.service.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    companion object {
        private const val MOVIE = "movie"
        private const val UPCOMING = "${MOVIE}/upcoming"
        private const val POPULAR = "${MOVIE}/popular"
        private const val API_KEY = "4a1705a7c4fea68b615eb02e4f191208"
    }

    @GET(UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("api_key")
        query: String = API_KEY
    ): MovieResponse

    @GET(POPULAR)
    suspend fun getPopularMovies(
        @Query("api_key")
        query: String = API_KEY
    ): MovieResponse
}