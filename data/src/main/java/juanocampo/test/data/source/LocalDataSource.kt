package juanocampo.test.data.source

import io.reactivex.Observable
import juanocampo.test.data.entity.PostRepo
import juanocampo.test.domain.entity.Post

interface LocalDataSource {

    fun isSync(): Boolean

    fun insertItems(items: List<PostRepo>)

    fun deleteAll(): Boolean

    fun deleteById(string: String): Boolean

    fun setAsReadById(id: String): Boolean

    fun getAllFavorites(): Observable<List<PostRepo>>

    fun getPostAll(): Observable<List<PostRepo>>

    fun markAsReadInitialData(): Boolean

    fun setAsFavoriteById(id: String, favorite: Boolean): Boolean

    fun getPostById(id: String): PostRepo?
}