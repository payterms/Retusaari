package ru.payts.retusaari.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import ru.payts.retusaari.R
import ru.payts.retusaari.viewmodel.MainViewModel
import androidx.activity.viewModels


class MainActivity : AppCompatActivity() {
    //Use the 'by viewModels()' Kotlin property delegate or
    // ViewModelProvider.ViewModelProvider(ViewModelStoreOwner), passing in the activity.
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // deprecated
        // viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.viewState().observe(this, Observer { value ->
            tv_hello.text = value
            Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
        })

    }

    fun buttonTouched(view: View) {
        Toast.makeText(this, "So gently!", Toast.LENGTH_SHORT).show()
    }
}
