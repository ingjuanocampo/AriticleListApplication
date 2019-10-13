package juanocampo.test.data.source

import io.reactivex.Observable
import juanocampo.test.data.entity.PostRepo

interface LocalDataSource {

    fun isSync(): Boolean

    fun insertItems(items: List<PostRepo>)

    fun deleteAll()

    fun delete(post: PostRepo)

    fun setAsReadById(id: String)

    fun getAllFavorites(): Observable<List<PostRepo>>

    fun getAll(): Observable<List<PostRepo>>

    fun markAsReadInitialData()
}