package com.example.movieapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieapp.databinding.ActivityAddBinding
import com.example.movieapp.models.Movie
import com.google.gson.Gson

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var list: ArrayList<Movie>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = ArrayList()
        val gson = Gson()

        val sharedPreferences=getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)


        binding.btnSave.setOnClickListener {
            var name=binding.edtName.text?.toString()?.trim()
            var author=binding.edtAuthors.text?.toString()?.trim()
            var about=binding.edtAbout.text?.toString()?.trim()
            var date=binding.edtDate.text?.toString()?.trim()

            if (name!!.isNotEmpty()&& about!!.isNotEmpty()&&about!!.isNotEmpty()&&date!!.isNotEmpty()){
                val movie=Movie(name, author, about, date)
                list.add(movie)
                val jsonlist=gson.toJson(list)
                val editor=sharedPreferences.edit()
                editor.putString("movie",jsonlist)

                editor.apply()
                Toast.makeText(this, "Saqlandi", Toast.LENGTH_SHORT).show()
                binding.edtName.text!!.clear()
                binding.edtAbout.text!!.clear()
                binding.edtDate.text!!.clear()
                binding.edtAuthors.text!!.clear()

            }else if (name.isNotEmpty()){
                binding.edtName.error="To'ldirilmagan"
                binding.edtName.isFocusable=true
            }else if (author!!.isNotEmpty()){
                binding.edtAuthors.error="To'ldirilmagan"
                binding.edtAbout.isFocusable=true
            }else if (about!!.isNotEmpty()){
                binding.edtAbout.error="To'ldirilmagan"
                binding.edtAbout.isFocusable=true
            }else if (date!!.isNotEmpty()){
                binding.edtDate.error="To'ldirilmagan"
                binding.edtDate.isFocusable=true
            }else{
                Toast.makeText(this, "Ma'lumotlarda xatolik", Toast.LENGTH_SHORT).show()
            }
        }
    }
}