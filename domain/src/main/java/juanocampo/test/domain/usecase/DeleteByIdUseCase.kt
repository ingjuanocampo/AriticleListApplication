package juanocampo.test.domain.usecase

import juanocampo.test.domain.repository.ArticlesRepository
import juanocampo.test.domain.status.ProcessError
import juanocampo.test.domain.status.ProcessStatus
import juanocampo.test.domain.status.ProcessSuccess

class DeleteByIdUseCase(private val articlesRepository: ArticlesRepository) {

    operator fun invoke(id: String): ProcessStatus {
        return try {
            val deleted = articlesRepository.deleteById(id)
            if (deleted) ProcessSuccess else ProcessError
        } catch (e: Exception) {
            ProcessError
        }
    }

}