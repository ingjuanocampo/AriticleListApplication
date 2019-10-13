package juanocampo.test.data

import io.reactivex.Observable
import juanocampo.test.data.mapper.Mapper
import juanocampo.test.data.source.LocalDataSource
import juanocampo.test.data.source.RemoteDataSource
import juanocampo.test.domain.entity.Post
import juanocampo.test.domain.entity.User
import juanocampo.test.domain.repository.ArticlesRepository

class ArticleRepositoryImp(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val mapper: Mapper
) : ArticlesRepository {

    override fun sync(): Boolean {
        return if (!localDataSource.isSync()) {
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

    override fun deleteAll() = localDataSource.deleteAll()

    override fun deleteById(id: String) = localDataSource.deleteById(id)

    override fun setAsReadById(id: String) = localDataSource.setAsReadById(id)

    override fun getPostAll(): Observable<List<Post>> =
        localDataSource.getPostAll()
            .map { list ->
                return@map list.map { post -> mapper.map(post) }
            }

    override fun getUserById(userId: String): Observable<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavorites(): Observable<List<Post>> =
        localDataSource.getAllFavorites()
            .map { list ->
                return@map list.map { post -> mapper.map(post) }
            }

    override fun markAsReadInitialData() = localDataSource.markAsReadInitialData()
}