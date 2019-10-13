package juanocampo.test.articleapp.ui.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import juanocampo.test.articleapp.ui.main.MainActivity
import juanocampo.test.articleapp.ui.main.fragment.ListArticleFragment
import juanocampo.test.presentation.di.ActivityScope
import juanocampo.test.presentation.di.PresentationModule

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [PresentationModule::class])
    abstract fun mainActivity(): MainActivity


    @ActivityScope
    @ContributesAndroidInjector(modules = [PresentationModule::class])
    abstract fun listArticleFragment(): ListArticleFragment

}