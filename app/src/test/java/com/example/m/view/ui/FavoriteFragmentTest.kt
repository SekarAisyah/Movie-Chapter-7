package com.example.m.view.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.m.room.FavoriteMovie
import com.example.m.view.adapter.FavoriteMovieAdapter
import com.example.m.viewmodel.FavoriteViewModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteFragmentTest {

    @Mock
    private lateinit var viewModel: FavoriteViewModel

    @Mock
    private lateinit var observer: Observer<List<FavoriteMovie>>

    private lateinit var fragment: FavoriteFragment

    @Before
    fun setUp() {
        fragment = FavoriteFragment()
        fragment.viewModel = viewModel
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getAllFavMovies_listNotNull_setAdapter() {
        // Create a dummy list of movies
        val movieList = listOf(
            FavoriteMovie(1, "Movie 1", "2021-01-01", "poster1.jpg"),
            FavoriteMovie(2, "Movie 2", "2021-02-02", "poster2.jpg"),
            FavoriteMovie(3, "Movie 3", "2021-03-03", "poster3.jpg")
        )

        // Mock the behavior of the viewModel.listMovie LiveData
        `when`(viewModel.listMovie).thenReturn(mockLiveData(movieList))

        // Mock RecyclerView.LayoutManager
        val layoutManager: RecyclerView.LayoutManager = mock(RecyclerView.LayoutManager::class.java)
        `when`(fragment.binding.rvListFilm.layoutManager).thenReturn(layoutManager)

        // Attach the observer to the viewModel.listMovie LiveData
        fragment.getAllFavMovies()
        fragment.viewModel.listMovie.observeForever(observer)

        // Verify that the RecyclerView adapter is set with the movieList
        verify(fragment.binding.rvListFilm).setHasFixedSize(false)
        verify(fragment.binding.rvListFilm).adapter = FavoriteMovieAdapter(movieList)

        // Clean up
        fragment.viewModel.listMovie.removeObserver(observer)
    }

    @Test
    fun getAllFavMovies_listNull_noAdapterSet() {
        // Mock the behavior of the viewModel.listMovie LiveData to be null
        `when`(viewModel.listMovie).thenReturn(mockLiveData<List<FavoriteMovie>?>(null))

        // Attach the observer to the viewModel.listMovie LiveData
        fragment.getAllFavMovies()
        fragment.viewModel.listMovie.observeForever(observer)

        // Verify that the RecyclerView adapter is not set
        verify(fragment.binding.rvListFilm, never()).setHasFixedSize(false)
        verify(fragment.binding.rvListFilm, never()).adapter = any(FavoriteMovieAdapter::class.java)

        // Clean up
        fragment.viewModel.listMovie.removeObserver(observer)
    }

    private fun <T> mockLiveData(value: T): LiveData<T> {
        return MutableLiveData<T>().apply {
            postValue(value)
        }
    }

}

private fun Any.thenReturn(mockLiveData: LiveData<List<FavoriteMovie>?>) {

}


