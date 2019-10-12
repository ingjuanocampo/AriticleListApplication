package juanocampo.test.articleapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import juanocampo.test.articleapp.App
import juanocampo.test.articleapp.ui.di.ActivityModule
import juanocampo.test.cache.di.CacheModule
import juanocampo.test.data.di.DataModule
import juanocampo.test.domain.di.DomainModule
import juanocampo.test.remote.di.RemoteModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityModule::class,
    DomainModule::class,
    DataModule::class,
    RemoteModule::class,
    CacheModule::class])
interface AppComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}