package com.example.m.view.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m.R
import com.example.m.databinding.FragmentHomeBinding
import com.example.m.view.adapter.MovieAdapter
import com.example.m.viewmodel.MovieViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION", "ReplaceGetOrSet")
@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        observeMovie()
        (activity as AppCompatActivity).setSupportActionBar(binding.tbHome)

        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth.currentUser == null) {
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment2)
        }

        sharedPreferences = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        val getUser = sharedPreferences.getString("user", "")
        binding.textJudul.text = "Welcome, $getUser"

        binding.ivIcprofile.setOnClickListener {
            val addUser = sharedPreferences.edit()
            addUser.putString("user", getUser)
            addUser.apply()
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        binding.ivIcfavorit.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
    }

    private fun observeMovie() {
        binding.rvListFilm.layoutManager = LinearLayoutManager(requireContext())
        binding.rvListFilm.setHasFixedSize(false)
        viewModel.setMoviesList()
        viewModel.movie.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.rvListFilm.adapter = MovieAdapter(it)
            }
        }
    }
}
