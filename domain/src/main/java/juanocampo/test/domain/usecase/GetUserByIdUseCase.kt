package juanocampo.test.domain.usecase

import juanocampo.test.domain.repository.ArticlesRepository
import juanocampo.test.domain.status.UserError
import juanocampo.test.domain.status.UserGetStatus
import juanocampo.test.domain.status.UserSuccess

class GetUserByIdUseCase(private val articlesRepository: ArticlesRepository) {

    operator fun invoke(id: String): UserGetStatus {
        val user = articlesRepository.getUserById(id)
        return if(user != null) {
            UserSuccess(user)
        } else {
            UserError
        }
    }
}