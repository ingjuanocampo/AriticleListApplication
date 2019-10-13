package juanocampo.test.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import juanocampo.test.presentation.model.MainModel
import juanocampo.test.presentation.viewmodel.MainViewModel

class MainViewModelFactory(private val model: MainModel): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (MainViewModel::class.java.isAssignableFrom(modelClass)) return MainViewModel(model) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}