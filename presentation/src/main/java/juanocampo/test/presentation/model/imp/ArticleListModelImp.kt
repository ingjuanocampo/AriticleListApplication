package juanocampo.test.presentation.model.imp

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import juanocampo.test.domain.entity.Post
import juanocampo.test.domain.usecase.GetListPostUseCase
import juanocampo.test.presentation.model.ArticleListModel

class ArticleListModelImp(private val getListPostUseCase: GetListPostUseCase): ArticleListModel {

    override fun observePostList(isFavorite: Boolean): Observable<List<Post>> {
        return getListPostUseCase(isFavorite).subscribeOn(Schedulers.io())
    }

    override fun deleteById(id: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAsFavoriteById(id: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}