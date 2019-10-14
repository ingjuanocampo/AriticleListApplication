package juanocampo.test.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import juanocampo.test.presentation.model.ArticleDetailModel
import juanocampo.test.presentation.viewmodel.ArticleDetailViewModel

class ArticleDetailViewModelFactory(private val model: ArticleDetailModel): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (ArticleDetailViewModel::class.java.isAssignableFrom(modelClass)) return ArticleDetailViewModel(model) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}