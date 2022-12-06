package com.example.movieapproomsql

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapproomsql.Adapter.MovieAdapter
import com.example.movieapproomsql.Database.AppDatabase
import com.example.movieapproomsql.Models.Movie
import com.example.movieapproomsql.databinding.FragmentHomeBinding
import com.example.movieapproomsql.databinding.MyDialogBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var movieAdapter: MovieAdapter
    lateinit var list: ArrayList<Movie>
    lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        appDatabase = AppDatabase.getInstance(requireContext())

        list = appDatabase.movieDao().getAllMovies() as ArrayList<Movie>
        movieAdapter = MovieAdapter(list, object : MovieAdapter.OnMyItemClickListener {
            override fun itemClick(movie: Movie) {

                val bundle = Bundle()
                bundle.putInt("id", movie.id!!)

                findNavController().navigate(R.id.showFragment, bundle)

            }

            override fun itemCLickChange(movie: Movie, position: Int) {

                val bundle = Bundle()
                bundle.putInt("id", movie.id!!)
                bundle.putInt("a", position)


                val dialog = AlertDialog.Builder(context)
                val myDialogBinding = MyDialogBinding.inflate(layoutInflater, null, false)

                myDialogBinding.nameEt.setText(movie.movieName)
                myDialogBinding.authorsEt.setText(movie.movieAuthors)
                myDialogBinding.aboutEt.setText(movie.movieAbout)
                myDialogBinding.dateEt.setText(movie.movieDate)

                dialog.setView(myDialogBinding.root)

                dialog.setPositiveButton("Edit", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                        movie.movieName = myDialogBinding.nameEt.text.toString()
                        movie.movieAuthors = myDialogBinding.authorsEt.text.toString()
                        movie.movieAbout = myDialogBinding.aboutEt.text.toString()
                        movie.movieDate = myDialogBinding.dateEt.text.toString()

                        appDatabase.movieDao().updateMovie(movie)
                        list[position] = movie
                        movieAdapter.notifyItemChanged(position)

                    }

                })

                dialog.show()

            }

            override fun itemClickDelete(movie: Movie, position: Int) {

                appDatabase.movieDao().deleteMovie(movie)
                list.remove(movie)
                movieAdapter.notifyItemRemoved(list.size)
                movieAdapter.notifyItemRangeRemoved(position, list.size)

            }

        })

        movieAdapter.notifyItemInserted(list.size)
        movieAdapter.notifyItemRemoved(list.size)

        binding.rv.adapter = movieAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.my_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        when (id) {
            R.id.menuId -> {

                findNavController().navigate(R.id.addFragment)

            }
        }

        return super.onOptionsItemSelected(item)

    }

}