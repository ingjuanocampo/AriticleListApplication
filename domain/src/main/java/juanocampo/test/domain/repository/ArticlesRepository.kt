package juanocampo.test.domain.repository

import io.reactivex.Observable
import juanocampo.test.domain.entity.Post
import juanocampo.test.domain.entity.User


interface ArticlesRepository {

    fun sync(): Boolean

    fun deleteAll(): Boolean

    fun deleteById(id: String): Boolean

    fun setAsReadById(id: String): Boolean

    fun getPostAll(): Observable<List<Post>>

    fun getUserById(userId: String): Observable<User>

    fun getFavorites(): Observable<List<Post>>

    fun markAsReadInitialData(): Boolean
}