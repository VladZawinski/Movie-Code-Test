package non.shahad.data.repository

import non.shahad.data.service.TMDBService
import non.shahad.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val service: TMDBService
): HomeRepository {

}