package com.mehedi.weather.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mehedi.weather.R
import com.mehedi.weather.R.layout

/**
 * Created by mhasan2 on 2/20/21.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        setupFragment()
    }

    private fun setupFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(
            R.id.fragment_container,
            LookupFragment.newInstance()
        ).commit()
    }

}
