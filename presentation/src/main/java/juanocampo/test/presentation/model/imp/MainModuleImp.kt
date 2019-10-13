package juanocampo.test.presentation.model.imp

import io.reactivex.Completable
import juanocampo.test.presentation.model.MainModel

class MainModuleImp: MainModel {

    override fun syncServerInfo(): Completable {
        return Completable.complete()
    }

    override fun deleteAll(): Completable {
        return Completable.complete()
    }
}