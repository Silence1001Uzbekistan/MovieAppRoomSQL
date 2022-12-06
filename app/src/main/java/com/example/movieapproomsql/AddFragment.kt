package com.example.movieapproomsql

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.movieapproomsql.Database.AppDatabase
import com.example.movieapproomsql.Models.Movie
import com.example.movieapproomsql.databinding.FragmentAddBinding
import com.google.android.material.snackbar.Snackbar

class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        return binding.root

    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = Movie()
        appDatabase = AppDatabase.getInstance(requireContext())

        binding.dateAddId.setOnClickListener {

            val datePickerDialog = DatePickerDialog(requireContext())
            datePickerDialog.setOnDateSetListener { datePicker, i, i2, i3 ->


                binding.dateAddId.text = "$i3.${i2 + 1}.$i"
                movie.movieDate = binding.dateAddId.text.trim().toString()

            }

            datePickerDialog.show()


        }

        binding.saveAdd.setOnClickListener {

            movie.movieName = binding.nameAddId.text.trim().toString()
            movie.movieAuthors = binding.authorsAddId.text.trim().toString()
            movie.movieAbout = binding.aboutAddId.text.trim().toString()

            //movie.movieDate = binding.dateAddId.text.trim().toString()


            appDatabase.movieDao().addMovies(movie)

            Snackbar.make(it, "Save", Snackbar.LENGTH_LONG).show()

            findNavController().popBackStack()


        }


    }

}