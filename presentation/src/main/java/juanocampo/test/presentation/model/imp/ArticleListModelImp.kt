package juanocampo.test.presentation.model.imp

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import juanocampo.test.domain.entity.Post
import juanocampo.test.domain.status.ProcessError
import juanocampo.test.domain.status.ProcessStatus
import juanocampo.test.domain.usecase.DeleteByIdUseCase
import juanocampo.test.domain.usecase.FavoriteUseCase
import juanocampo.test.domain.usecase.GetListPostUseCase
import juanocampo.test.presentation.entity.RecyclerViewType
import juanocampo.test.presentation.model.ArticleListModel
import juanocampo.test.presentation.usecase.SorterAndFilterUIArticleUseCase

class ArticleListModelImp(
    private val getListPostUseCase: GetListPostUseCase,
    private val favoriteUseCase: FavoriteUseCase,
    private val deleteByIdUseCase: DeleteByIdUseCase,
    private val sorterArticleUseCase: SorterAndFilterUIArticleUseCase
) : ArticleListModel {

    override fun observePostList(isFavorite: Boolean): Observable<List<Post>> {
        return getListPostUseCase(isFavorite).subscribeOn(Schedulers.io())
    }

    override fun deleteById(id: String): Completable {
        return subscribeCompletableIO {
            deleteByIdUseCase(id)
        }
    }

    override fun setAsFavoriteById(id: String, favorite: Boolean): Completable {
        return subscribeCompletableIO {
            favoriteUseCase(id, favorite)
        }
    }

    private fun subscribeCompletableIO(function: () -> ProcessStatus): Completable {
        return Completable.fromCallable {
            val processStatus = function()
            if (processStatus is ProcessError) {
                throw IllegalStateException("Something when wrong")
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun sortAndFilterItems(
        query: String,
        toggleSort: Boolean,
        list: List<RecyclerViewType>
    ): Single<List<RecyclerViewType>> {
        return sorterArticleUseCase(query, toggleSort, list)
    }

}