package juanocampo.test.presentation.model.imp

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import juanocampo.test.domain.status.PostGetStatus
import juanocampo.test.domain.status.ProcessError
import juanocampo.test.domain.status.UserGetStatus
import juanocampo.test.domain.usecase.FavoriteUseCase
import juanocampo.test.domain.usecase.GetPostByIdUseCase
import juanocampo.test.domain.usecase.GetUserByIdUseCase
import juanocampo.test.presentation.model.ArticleDetailModel
import java.lang.IllegalStateException

class ArticleDetailModelImp(private val getPostByIdUseCase: GetPostByIdUseCase,
                            private val getUserByIdUseCase: GetUserByIdUseCase,
                            private val favoriteUseCase: FavoriteUseCase): ArticleDetailModel {


    override fun getUserById(id: String): Single<UserGetStatus> {
        return Single.fromCallable { return@fromCallable getUserByIdUseCase(id) }.subscribeOn(Schedulers.io())
    }

    override fun getPostById(id: String): Single<PostGetStatus> {
        return Single.fromCallable {
            return@fromCallable getPostByIdUseCase(id) }.subscribeOn(Schedulers.io())
    }

    override fun setAsFavoriteById(id: String, favorite: Boolean): Completable {

        return Completable.fromCallable {
            val processStatus = favoriteUseCase(id, favorite)
            if  (processStatus is ProcessError) {
                throw IllegalStateException("Something when wrong")
            }
        }.subscribeOn(Schedulers.io())
    }
}