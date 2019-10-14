package juanocampo.test.domain.usecase

import org.junit.Test
import org.mockito.Mockito.verify

class GetListPostUseCaseTest: DomainTestBase() {

    lateinit var getListPostUseCase: GetListPostUseCase

    override fun setUp() {
        super.setUp()
        getListPostUseCase = GetListPostUseCase(articlesRepository)
    }

    @Test
    fun getFavoriteListTest() {
        getListPostUseCase(true)
        verify(articlesRepository).getFavorites()
    }

    @Test
    fun getAllListTest() {
        getListPostUseCase(false)
        verify(articlesRepository).getPostAll()
    }

}