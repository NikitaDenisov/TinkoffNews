package com.denisov.tinkoffnews.presentation

import androidx.annotation.CallSuper
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Provider

abstract class BaseViewModel : ViewModel() {
    private val disposables = CompositeDisposable()

    protected inline fun subscribe(crossinline function: () -> Disposable) {
        subscribe(function())
    }

    protected fun subscribe(disposable: Disposable) {
        disposables.add(disposable)
    }

    protected fun cancelSubscribes() = disposables.clear()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        cancelSubscribes()
    }
}

class ViewModelFactorySingle(val provider: Provider<out ViewModel>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return provider.get() as T
    }
}

fun <T : BaseViewModel> FragmentActivity.getViewModel(
    java: Class<T>,
    viewModelFactory: ViewModelProvider.Factory
): T = ViewModelProvider(this, viewModelFactory).get(java)

inline fun <reified T : BaseViewModel> FragmentActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory): T {
    return getViewModel(T::class.java, viewModelFactory)
}