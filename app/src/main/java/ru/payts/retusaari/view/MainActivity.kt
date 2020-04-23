package ru.payts.retusaari.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import ru.payts.retusaari.R
import ru.payts.retusaari.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    //Use the 'by viewModels()' Kotlin property delegate or
    // ViewModelProvider.ViewModelProvider(ViewModelStoreOwner), passing in the activity.
    //private val viewModel: MainViewModel by viewModels()

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // deprecated
        // viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.viewState().observe(this, Observer { value ->
            tv_hello.text = value
            Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
        })

        val button: Button = findViewById(R.id.magic_button)
        button.setOnClickListener {
            // Code here executes on main thread after user presses button
            Toast.makeText(this, "So gently!", Toast.LENGTH_SHORT).show()
        }

    }
}
