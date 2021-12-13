package com.arjinmc.jetpackplayground.architecture.viewmodel

/**
 * Created by Eminem Lo on 12/13/21
 * email: arjinmc@hotmail.com
 */
interface IViewModel<T> {

    fun setData(data: T?)

    fun getData(): T?

    fun loadData()
}