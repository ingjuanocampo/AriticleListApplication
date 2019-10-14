package juanocampo.test.domain.usecase

import juanocampo.test.domain.repository.ArticlesRepository
import juanocampo.test.domain.status.PostError
import juanocampo.test.domain.status.PostGetStatus
import juanocampo.test.domain.status.PostSuccess

class GetPostByIdUseCase(private val articlesRepository: ArticlesRepository) {

    operator fun invoke(id: String): PostGetStatus {
        articlesRepository.setAsReadById(id)
        val post = articlesRepository.getPostById(id)
        return if (post != null) {
            PostSuccess(post)
        } else {
            PostError
        }
    }

}