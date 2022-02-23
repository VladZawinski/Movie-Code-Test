package non.shahad.data.service.response

interface TMDBResponse<T> {
    val page: Int
    val totalPages: Int
    val totalResults: Int
    val results: List<T>
}