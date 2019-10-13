package juanocampo.test.presentation.model

import io.reactivex.Completable

interface MainModel {
    fun syncServerInfo(): Completable
    fun deleteAll(): Completable
    fun refresh(): Completable
}