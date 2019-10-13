package juanocampo.test.cache.database.dao

import androidx.room.*
import io.reactivex.Observable
import juanocampo.test.cache.database.entity.PostEntity

@Dao
interface PostDao {

    @Query("UPDATE PostEntity SET isRead = 'false' WHERE postId <= 20")
    fun updateUnReadMsn(): Int

    @Query("SELECT COUNT(PostEntity.postId) FROM PostEntity")
    fun countItems(): Int

    @Query("SELECT * FROM PostEntity ORDER BY postId")
    fun getAll(): Observable<List<PostEntity>>

    @Query("SELECT * FROM PostEntity WHERE isFavorite = '1' ORDER BY postId")
    fun getAllFavoriteObservable(): Observable<List<PostEntity>>

    @Query("SELECT * FROM PostEntity")
    fun getAllSync(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(teams: List<PostEntity>)

    @Update
    fun update(entityToInsert: PostEntity): Int

    @Query("DELETE FROM PostEntity")
    fun deleteAll(): Int

    @Query("DELETE FROM PostEntity where postId = :id")
    fun delete(id: String): Int


    @Query("SELECT * FROM PostEntity where postId = :id")
    fun getPostById(id: String): PostEntity

    @Delete
    fun delete(entitiesToDelete: List<PostEntity>): Int
}