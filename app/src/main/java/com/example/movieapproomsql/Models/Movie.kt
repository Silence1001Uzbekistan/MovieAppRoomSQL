package com.example.movieapproomsql.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Movie : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var movieName: String? = null
    var movieAuthors: String? = null
    var movieAbout: String? = null
    var movieDate: String? = null

}