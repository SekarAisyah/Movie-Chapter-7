package com.example.m.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.m.databinding.FragmentProfileBinding
import com.example.m.view.ui.ProfileFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class ProfileFragmentTest {

    @Mock
    private lateinit var mockSharedPreferences: SharedPreferences

    @Mock
    private lateinit var mockEditor: SharedPreferences.Editor

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        `when`(mockSharedPreferences.edit()).thenReturn(mockEditor)
    }

    @Test
    fun testUpdateButtonClicked() {
        // Set up the mock SharedPreferences
        val fragment = ProfileFragment()
        fragment.sharedPreferences = mockSharedPreferences
        `when`(mockSharedPreferences.getString(eq("user"), anyString())).thenReturn("oldUser")
        `when`(mockSharedPreferences.getString(eq("nama"), anyString())).thenReturn("oldNama")
        `when`(mockSharedPreferences.getString(eq("tgl"), anyString())).thenReturn("oldTgl")
        `when`(mockSharedPreferences.getString(eq("alamat"), anyString())).thenReturn("oldAlamat")

        // Set up the EditText inputs
        val context = ApplicationProvider.getApplicationContext<Context>()
        val inflater = LayoutInflater.from(context)
        fragment.binding = FragmentProfileBinding.inflate(inflater)
        fragment.binding.edtUserNameProfile.setText("newUser")
        fragment.binding.edtNameProfile.setText("newNama")
        fragment.binding.edtTanggalLahirProfile.setText("newTgl")
        fragment.binding.edtAlamat.setText("newAlamat")

        // Call the updateButtonClicked method directly
        fragment.updateButtonClicked()

        // Verify the SharedPreferences are updated correctly
        verify(mockEditor).putString("user", "newUser")
        verify(mockEditor).putString("nama", "newNama")
        verify(mockEditor).putString("tgl", "newTgl")
        verify(mockEditor).putString("alamat", "newAlamat")
        verify(mockEditor).apply()
    }
}
