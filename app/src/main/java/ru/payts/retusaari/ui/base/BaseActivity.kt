package ru.payts.retusaari.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

abstract class BaseActivity<T, S : BaseViewState<T>> : AppCompatActivity() {
    abstract val viewModel: BaseViewModel<T, S>
    abstract val layoutRes: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        viewModel.getViewState().observe(this, Observer<S> { viewState ->
            if (viewState == null) return@Observer
            if (viewState.error != null) {
                renderError(viewState.error)
                return@Observer
            }
            renderData(viewState.data)
        })
    }

    protected fun renderError(error: Throwable?) {
        error?.let {
            it.message?.let { showError(it) }
        }
    }

    fun showError(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    abstract fun renderData(data: T)
}