package com.example.m.view.ui

import com.example.m.view.ui.ResgisterFragment
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ResgisterFragmentTest {

    //memeriksa validasi input dengan input yang valid
    @Test
    fun testValidateInput_WithValidInput() {

        val registerFragment = ResgisterFragment()

        // Memanggil fungsi validateInput dengan input yang valid
        val result = registerFragment.validateInput("username", "test@example.com", "password", "password")

        // Memastikan hasil pengujian adalah true
        assertTrue(result)
    }

   //memeriksa validasi input dengan input yang kosong
    @Test
    fun testValidateInput_WithEmptyInput() {

        val registerFragment = ResgisterFragment()

        // Memanggil fungsi validateInput dengan input yang kosong
        val result = registerFragment.validateInput("", "", "", "")

        // Memastikan hasil pengujian adalah false
        assertFalse(result)
    }

    //memeriksa validasi input dengan password yang tidak cocok.
    @Test
    fun testValidateInput_WithMismatchedPasswords() {

        val registerFragment = ResgisterFragment()

        // Memanggil fungsi validateInput dengan password yang tidak cocok
        val result = registerFragment.validateInput("username", "test@example.com", "password1", "password2")

        // Memastikan hasil pengujian adalah false
        assertFalse(result)
    }


}
