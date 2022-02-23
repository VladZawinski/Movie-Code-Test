package non.shahad.data.cache.daos

import androidx.room.Dao
import androidx.room.Query
import non.shahad.data.cache.entities.MovieEntity

@Dao
abstract class MovieDao: BaseDao<MovieEntity>() {
    @Query("DELETE FROM movie WHERE `query` = :query")
    abstract fun deleteAllByQuery(query: String)

    @Query("SELECT * FROM movie WHERE `query` = :by")
    abstract fun queryAll(by: String): List<MovieEntity>?

    @Query("SELECT * FROM movie WHERE `id` = :id")
    abstract fun findById(id: Int): MovieEntity?
}