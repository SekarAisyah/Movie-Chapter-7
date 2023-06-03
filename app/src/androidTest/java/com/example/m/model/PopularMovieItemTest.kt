package com.example.m.model

import org.junit.Assert.assertEquals
import org.junit.Test

class PopularMovieItemTest {

    @Test
    fun `testPopularMovieItem`() {
        val item = PopularMovieItem(
            true,
            "backdrop_path",
            listOf(1, 2, 3),
            123,
            "original_language",
            "original_title",
            "overview",
            9.8,
            "poster_path",
            "2023-06-02",
            "title",
            true,
            7.5,
            100
        )

        assertEquals(true, item.adult)
        assertEquals("backdrop_path", item.backdropPath)
        assertEquals(listOf(1, 2, 3), item.genreIds)
        assertEquals(123, item.id)
        assertEquals("original_language", item.originalLanguage)
        assertEquals("original_title", item.originalTitle)
        assertEquals("overview", item.overview)
        assertEquals(9.8, item.popularity, 0.0)
        assertEquals("poster_path", item.posterPath)
        assertEquals("2023-06-02", item.releaseDate)
        assertEquals("title", item.title)
        assertEquals(true, item.video)
        assertEquals(7.5, item.voteAverage, 0.0)
        assertEquals(100, item.voteCount)
    }
}
