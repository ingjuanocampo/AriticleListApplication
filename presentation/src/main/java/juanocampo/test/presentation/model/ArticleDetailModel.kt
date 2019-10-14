package juanocampo.test.presentation.model

import io.reactivex.Single
import juanocampo.test.domain.status.PostGetStatus
import juanocampo.test.domain.status.UserGetStatus

interface ArticleDetailModel {

    fun getUserById(id: String): Single<UserGetStatus>

    fun getPostById(id: String): Single<PostGetStatus>

}