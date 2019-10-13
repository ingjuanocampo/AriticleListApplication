package juanocampo.test.domain.usecase

import juanocampo.test.domain.repository.ArticlesRepository
import juanocampo.test.domain.status.ProcessError
import juanocampo.test.domain.status.ProcessStatus
import juanocampo.test.domain.status.ProcessSuccess

class SynServerInfoUseCase(private val repository: ArticlesRepository) {

    operator fun invoke(): ProcessStatus {
        val sync = repository.sync()
        val updateProcess = repository.markAsReadInitialData()
        return if (sync && updateProcess) ProcessSuccess else ProcessError
    }
}