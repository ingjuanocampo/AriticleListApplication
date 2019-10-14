package juanocampo.test.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import juanocampo.test.presentation.model.MainModel

class MainViewModel(private val model: MainModel) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val sycStatus = MutableLiveData<Boolean>()
    val deleteStatus = MutableLiveData<Boolean>()

    fun syncServerInformation() {
        compositeDisposable.add(
            model.syncServerInfo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    sycStatus.value = true
                }, { sycStatus.value = false })
        )
    }

    fun refreshServerInformation() {
        compositeDisposable.add(
            model.refresh()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    sycStatus.value = true
                }, { sycStatus.value = false })
        )
    }

    fun deleteAll() {
        val deleteDisposable = model.deleteAll()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                deleteStatus.value = true
            }, { deleteStatus.value = false })
        compositeDisposable.add(deleteDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}