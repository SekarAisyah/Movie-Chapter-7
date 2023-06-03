package com.example.m.view.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.m.R
import com.example.m.databinding.FragmentDetailBinding
import com.example.m.model.DetailMovieItem
import com.example.m.room.FavoriteMovie
import com.example.m.viewmodel.DetailViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailFragmentTest {

    @Mock
    private lateinit var viewModel: DetailViewModel

    @Mock
    private lateinit var binding: FragmentDetailBinding

    @Mock
    private lateinit var inflater: LayoutInflater

    @Mock
    private lateinit var container: ViewGroup

    @Mock
    private lateinit var view: View

    @Mock
    private lateinit var favoriteMovie: FavoriteMovie

    private lateinit var detailFragment: DetailFragment

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        detailFragment = DetailFragment()
        detailFragment.viewModel = viewModel
        detailFragment.binding = binding

        `when`(inflater.inflate(R.layout.fragment_detail, container, false)).thenReturn(view)
        `when`(binding.root).thenReturn(view as RelativeLayout?)
    }

    @Test
    fun testObserveDetailMovie() {
        val movie = MutableLiveData<DetailMovieItem?>()
        movie.value = DetailMovieItem(
            title = "Movie Title",
            releaseDate = "2023-01-01",
            popularity = 9.8,
            backdropPath = "/backdrop.jpg",
            overview = "Movie overview"
        )

        `when`(viewModel.movie).thenReturn(movie)

        detailFragment.observeDetailMovie()

        assertEquals("Movie Title", binding.tvNamafilmdetail.text.toString())
        assertEquals("Release: 2023-01-01", binding.tvReleasefilmdetail.text.toString())
        assertEquals("Popularity: 9.8", binding.tvPopularitydetail.text.toString())
        // Assert the other expected behaviors here
    }

    // Other test methods...

}
