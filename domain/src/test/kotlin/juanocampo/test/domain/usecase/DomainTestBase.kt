package juanocampo.test.domain.usecase

import juanocampo.test.domain.repository.ArticlesRepository
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

open class DomainTestBase {

    @Mock
    lateinit var articlesRepository: ArticlesRepository


    @Before
    open fun setUp() {
        MockitoAnnotations.initMocks(this)

    }
}