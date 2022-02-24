package non.shahad.data.cache.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import non.shahad.data.cache.entities.MovieEntity

@Dao
abstract class MovieDao: BaseDao<MovieEntity>() {
    @Query("DELETE FROM movie WHERE `query` = :query")
    abstract fun deleteAllByQuery(query: String)

    @Query("SELECT * FROM movie WHERE `query` = :by")
    abstract fun queryAll(by: String): List<MovieEntity>?

    @Query("SELECT * FROM movie WHERE `id` = :id")
    abstract fun findById(id: Int): MovieEntity?

    @Query("UPDATE movie SET is_favorite = :status WHERE id = :id")
    abstract fun changeStatusById(id: Int, status: Boolean)

    @Transaction
    open suspend fun deleteAndInsertByQuery(movies: List<MovieEntity>, query: String) {
        deleteAllByQuery(query)
        insertAll(movies)
    }
}