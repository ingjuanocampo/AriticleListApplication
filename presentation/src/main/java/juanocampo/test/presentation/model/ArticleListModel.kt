package juanocampo.test.presentation.model

import io.reactivex.Completable
import io.reactivex.Observable
import juanocampo.test.domain.entity.Post

interface ArticleListModel {

    fun observePostList(isFavorite: Boolean): Observable<List<Post>>

    fun deleteById(id: String): Completable

    fun setAsFavoriteById(id: String, favorite: Boolean): Completable

}