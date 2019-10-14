package juanocampo.test.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import juanocampo.test.presentation.mapper.Mapper
import juanocampo.test.presentation.model.ArticleListModel
import juanocampo.test.presentation.viewmodel.ArticleListViewModel

class ArticleListViewModelFactory(private val model: ArticleListModel, private val mapper: Mapper): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (ArticleListViewModel::class.java.isAssignableFrom(modelClass)) return ArticleListViewModel(model, mapper) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}