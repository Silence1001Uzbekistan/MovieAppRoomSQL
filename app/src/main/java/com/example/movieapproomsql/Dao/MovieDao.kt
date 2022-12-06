package com.example.movieapproomsql.Dao

import androidx.room.*
import com.example.movieapproomsql.Models.Movie

@Dao
interface MovieDao {

    @Query("select * from movie")
    fun getAllMovies(): List<Movie>

    @Insert
    fun addMovies(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Update
    fun updateMovie(movie: Movie)

    @Query("select * from movie where id=:id")
    fun getMovieById(id:Int):Movie


}