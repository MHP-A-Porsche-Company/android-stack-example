package com.mhp.superawsometaskapp.injection

import android.content.Context
import com.mhp.superawsometaskapp.AwsomeApp
import com.mhp.superawsometaskapp.service.DefaultListItemService
import com.mhp.superawsometaskapp.service.ListItemService
import com.mhp.superawsometaskapp.viewmodel.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *The module Dagger Dependency Injection [Module] of this app. This class defines some specific
 * implementation of classes that can be used as injectable dependencies. The functions inside this
 * class can be used to define [Singleton]s or special implementations of interfaces but also named
 * parameters for dependencies of the same type.
 */
@Module
class AwsomeModule {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return AwsomeApp.context
    }

    @Provides
    @Singleton
    internal fun provideListItemService(): ListItemService {
        return DefaultListItemService()
    }

    @Provides
    internal fun provideCreateItemViewModel(listItemService: ListItemService): CreateItemViewModel {
        return DefaultCreateItemViewModel(listItemService)
    }

    @Provides
    internal fun provideListViewModel(listItemService: ListItemService): ListViewModel {
        return DefaultListViewModel(listItemService)
    }

    @Provides
    internal fun provideEditItemViewModel(listItemService: ListItemService): EditItemViewModel {
        return DefaultEditItemViewModel(listItemService)
    }
}