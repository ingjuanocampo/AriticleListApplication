package juanocampo.test.domain.usecase

import juanocampo.test.domain.repository.ArticlesRepository

class GetListPostUseCase(private val articlesRepository: ArticlesRepository) {

    operator fun invoke(isFavorite: Boolean) =
        if (isFavorite) articlesRepository.getFavorites() else articlesRepository.getPostAll()

}