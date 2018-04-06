package com.example.kinoversionlast

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.os.Bundle
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_plot_film.*

class plotFilm : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plot_film)


        val actionBar = supportActionBar
        actionBar!!.setHomeButtonEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        val description = intent.getStringExtra("description")
        val name = intent.getStringExtra("name")
        val picture = intent.getStringExtra("picture")

        filmName.setText(name)
        filmDescription.setText(description)

        Picasso.with(this).load(picture).placeholder(R.drawable.image_1).networkPolicy(NetworkPolicy.OFFLINE).into(filmImage)
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return true
    }
}
