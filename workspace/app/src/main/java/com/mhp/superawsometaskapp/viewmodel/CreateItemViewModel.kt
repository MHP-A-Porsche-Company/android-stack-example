package com.mhp.superawsometaskapp.viewmodel

import io.reactivex.subjects.BehaviorSubject

interface CreateItemViewModel {
    val title: BehaviorSubject<String>
    /**
     * Create a new instance of [ListItem] with a given name
     */
    fun createItem(title: String)
}