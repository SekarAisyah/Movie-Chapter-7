package com.example.m.view.ui

import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class HomeFragmentTest {
    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var homeFragment: HomeFragment

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        homeFragment = HomeFragment()
        homeFragment.sharedPreferences = sharedPreferences

        whenever(sharedPreferences.getString("user", "")).thenReturn("John")
    }

    @Test
    fun testSetWelcomeMessage() {
        homeFragment.onViewCreated(mock(), mock())

        assertEquals("Welcome, John", homeFragment.binding.textJudul.text)
    }
}
