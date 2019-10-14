package juanocampo.test.presentation.model.imp

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import juanocampo.test.domain.entity.Post
import juanocampo.test.domain.status.ProcessError
import juanocampo.test.domain.status.ProcessStatus
import juanocampo.test.domain.usecase.FavoriteUseCase
import juanocampo.test.domain.usecase.GetListPostUseCase
import juanocampo.test.presentation.model.ArticleListModel
import java.lang.IllegalStateException

class ArticleListModelImp(private val getListPostUseCase: GetListPostUseCase, private val favoriteUseCase: FavoriteUseCase): ArticleListModel {

    override fun observePostList(isFavorite: Boolean): Observable<List<Post>> {
        return getListPostUseCase(isFavorite).subscribeOn(Schedulers.io())
    }

    override fun deleteById(id: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAsFavoriteById(id: String, favorite: Boolean): Completable {
        return subscribeCompletableIO{
            favoriteUseCase(id, favorite)
        }
    }

    private fun subscribeCompletableIO(function: () -> ProcessStatus): Completable {

        return Completable.fromCallable {
            val processStatus = function()
            if  (processStatus is ProcessError) {
                throw IllegalStateException("Something when wrong")
            }
        }.subscribeOn(Schedulers.io())
    }

}