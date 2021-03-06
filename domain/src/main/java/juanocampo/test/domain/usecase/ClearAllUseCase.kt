package juanocampo.test.domain.usecase

import juanocampo.test.domain.repository.ArticlesRepository
import juanocampo.test.domain.status.ProcessError
import juanocampo.test.domain.status.ProcessStatus
import juanocampo.test.domain.status.ProcessSuccess

class ClearAllUseCase(private val repository: ArticlesRepository) {

    
    operator fun invoke(): ProcessStatus {
        return try {
            val delete = repository.deleteAll()
            if (delete) ProcessSuccess else ProcessError
        } catch (e: Exception) {
            ProcessError
        }
    }
}