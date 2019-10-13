package juanocampo.test.cache

import io.reactivex.Observable
import juanocampo.test.cache.database.RoomDB
import juanocampo.test.cache.mapper.Mapper
import juanocampo.test.data.entity.PostRepo
import juanocampo.test.data.source.LocalDataSource

class LocalDataSourceImp(private val db: RoomDB, private val mapper: Mapper) : LocalDataSource {

    override fun isSync(): Boolean {
        return db.postDao().countItems() > 0
    }

    override fun insertItems(items: List<PostRepo>) {
        db.postDao().insertAll(items.map { mapper.map(it) })
    }

    override fun deleteAll(): Boolean {
        return db.postDao().deleteAll() > 1
    }

    override fun deleteById(id: String): Boolean {
        return db.postDao().delete(id) > 1
    }

    override fun setAsReadById(id: String): Boolean {
        val post = db.postDao().getPostById(id)
        post.isRead = true
        return db.postDao().update(post) > 1
    }

    override fun getAllFavorites(): Observable<List<PostRepo>> =
        db.postDao().getAllFavoriteObservable().map { list -> list.map { mapper.map(it) } }

    override fun getPostAll(): Observable<List<PostRepo>> =
        db.postDao().getAll().map { list -> list.map { mapper.map(it) } }

    override fun markAsReadInitialData(): Boolean {
        return db.postDao().updateUnReadMsn() > 1
    }
}