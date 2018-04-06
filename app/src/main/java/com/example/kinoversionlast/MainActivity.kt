package com.example.kinoversionlast

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val films = Film.List()

        val recyclerView : RecyclerView = findViewById(R.id.list_films)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = FilmAdapter(films,this,
                onItemClick = { (name,genre,description,country,year,picture) ->
            val intent = Intent(this@MainActivity, plotFilm::class.java)
            startActivity(intent)
        })

        launch(UI)
        {
            val cacheFilms = loadFilmsFromDatabase(application as App).await()

            if (cacheFilms.isNotEmpty())
            {
                films.addAll(cacheFilms)
                recyclerView.adapter = FilmAdapter(films,this@MainActivity,
                        onItemClick = { (name,genre,description,country,year,picture) ->
                    val intent = Intent(this@MainActivity, plotFilm::class.java)
                            intent.putExtra("name", name)
                            intent.putExtra("description", description)
                            intent.putExtra("picture", picture)
                    startActivity(intent)})
                recyclerView.adapter.notifyDataSetChanged()
            }
            else
            {
                val internetFilmsJob = loadFilmsFromInternet()
                internetFilmsJob.start()

                val internetFilms = internetFilmsJob.await()
                saveFilms(application as App, internetFilms)

                films.addAll(internetFilms)
                recyclerView.adapter = FilmAdapter(films,this@MainActivity,
                        onItemClick = { (name,genre,description,country,year,picture) ->
                            val intent = Intent(this@MainActivity, plotFilm::class.java)
                            intent.putExtra("name", name)
                            intent.putExtra("description", description)
                            intent.putExtra("picture", picture)
                            startActivity(intent)})

                recyclerView.adapter.notifyDataSetChanged()
            }
        }
    }
}






