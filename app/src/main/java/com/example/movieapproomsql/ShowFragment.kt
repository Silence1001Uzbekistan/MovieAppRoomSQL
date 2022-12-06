package com.example.movieapproomsql

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieapproomsql.Database.AppDatabase
import com.example.movieapproomsql.databinding.FragmentShowBinding

class ShowFragment : Fragment() {

    lateinit var binding: FragmentShowBinding

    lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentShowBinding.inflate(inflater, container, false)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appDatabase = AppDatabase.getInstance(requireContext())

        val id = arguments?.getInt("id")

        val movieById = appDatabase.movieDao().getMovieById(id!!)

        binding.movieNameId.text = movieById.movieName
        binding.movieAuthorsId.text = movieById.movieAuthors
        binding.movieAboutId.text = movieById.movieAbout
        binding.movieDateId.text = movieById.movieDate

    }

}