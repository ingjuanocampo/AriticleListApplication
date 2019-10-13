package juanocampo.test.data

import io.reactivex.Observable
import juanocampo.test.data.source.LocalDataSource
import juanocampo.test.data.source.RemoteDataSource
import juanocampo.test.domain.entity.Post
import juanocampo.test.domain.entity.User
import juanocampo.test.domain.repository.ArticlesRepository

class ArticleRepositoryImp(private val localDataSource: LocalDataSource,
                           private val remoteDataSource: RemoteDataSource): ArticlesRepository {


    override fun sync(): Boolean {
        return if(!localDataSource.isSync()) {
            val responseList = remoteDataSource.fetchPost()
            if (!responseList.isNullOrEmpty()) {
                localDataSource.insertItems(responseList)
                true
            } else {
                false
            }
        } else {
            true
        }

    }

    override fun deleteAll(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteById(id: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAsReadById(id: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPostAll(): Observable<List<Post>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserById(userId: String): Observable<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavorites(): Observable<List<Post>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun markAsReadInitialData(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}