package juanocampo.test.domain.usecase

import juanocampo.test.domain.repository.ArticlesRepository
import juanocampo.test.domain.status.ProcessError
import juanocampo.test.domain.status.ProcessStatus
import juanocampo.test.domain.status.ProcessSuccess

class FavoriteUseCase(private val articlesRepository: ArticlesRepository) {

    operator fun invoke(id: String, favorite: Boolean): ProcessStatus {
        return try {
            val favorite = articlesRepository.setAsFavoriteById(id, favorite)
            if (favorite) ProcessSuccess else ProcessError
        } catch (e: Exception) {
            ProcessError
        }
    }

}