package com.example.movieapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.adapter.RvAdapter
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.models.Movie
import com.example.movieapp.models.Save
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private lateinit var list: ArrayList<Movie>
    private lateinit var rvAdapter: RvAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = ArrayList()

        list = ArrayList()
        list.add(Movie("Test", "twg", "trgwb", "wtbb"))
        val gson = Gson()
        val sharedPreferences = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var data = sharedPreferences.getString("movie", "exeample")
        if (data != "exeample") {
            val type = object : TypeToken<ArrayList<Movie>>() {}.type
            val gsonToContact = gson.fromJson<ArrayList<Movie>>(data, type)
            if (gsonToContact.isNotEmpty()) {
                list.addAll(gsonToContact)
            }

        }

        rvAdapter = RvAdapter(this, list, object : RvAdapter.RvClick {
            override fun onClick(movie: Movie) {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                Save.movie = Movie(movie.name, movie.author, movie.about, movie.date)
                startActivity(intent)
            }

        })
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }




    }

    override fun onResume() {
        super.onResume()

    }
}