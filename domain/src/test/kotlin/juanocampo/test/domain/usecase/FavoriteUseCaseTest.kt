package juanocampo.test.domain.usecase

import juanocampo.test.domain.status.ProcessError
import juanocampo.test.domain.status.ProcessSuccess
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import java.lang.IllegalStateException
import org.mockito.Mockito.`when` as whenever


class FavoriteUseCaseTest: DomainTestBase() {

    lateinit var favoriteUseCase: FavoriteUseCase

    @Before
    override fun setUp() {
        super.setUp()
        favoriteUseCase = FavoriteUseCase(articlesRepository)
    }


    @Test
    fun favoriteByIdFailTest() {
        whenever(articlesRepository.setAsFavoriteById("1", true)).thenReturn(false)
        val result = favoriteUseCase("1", true)
        verify(articlesRepository).setAsFavoriteById("1", true)
        assert(result is ProcessError)
    }

    @Test
    fun whenThrownAnExceptionFavoriteFails() {
        whenever(articlesRepository.setAsFavoriteById("1", true)).thenThrow(IllegalStateException::class.java)
        val result = favoriteUseCase("1", true)

        assert(result is ProcessError)
    }

    @Test
    fun favoriteByIdSuccessTest() {
        whenever(articlesRepository.setAsFavoriteById("1", true)).thenReturn(true)
        val result = favoriteUseCase("1", true)
        verify(articlesRepository).setAsFavoriteById("1", true)

        assert(result is ProcessSuccess)
    }
}