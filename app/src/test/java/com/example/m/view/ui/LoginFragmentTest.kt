package com.example.m.view.ui

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LoginFragmentTest {

    private lateinit var loginFragment: LoginFragment

    @Before
    fun setup() {
        loginFragment = LoginFragment()
    }

    //memeriksa validasi kredensial dengan email dan kata sandi yang valid.
    @Test
    fun validateCredentials_validEmailAndPassword_returnsTrue() {
        val email = "kai@gmail.com"
        val password = "12345678"

        val result = loginFragment.validateCredentials(email, password)

        //membandingkan hasilnya dengan true menggunakan assertEquals()
        assertEquals(true, result)
    }

    //memeriksa validasi kredensial dengan email yang tidak valid.
    @Test
    fun validateCredentials_invalidEmail_returnsFalse() {
        val email = "invalid_email"
        val password = "password123"

        val result = loginFragment.validateCredentials(email, password)

        //membandingkan hasilnya dengan false menggunakan assertEquals()
        assertEquals(false, result)
    }

    //memeriksa validasi kredensial dengan email kosong
    @Test
    fun validateCredentials_emptyEmail_returnsFalse() {
        val email = ""
        val password = "password123"

        val result = loginFragment.validateCredentials(email, password)

        //membandingkan hasilnya dengan false menggunakan assertEquals()
        assertEquals(false, result)
    }

    //memeriksa validasi kredensial dengan kata sandi kosong.
    @Test
    fun validateCredentials_emptyPassword_returnsFalse() {
        val email = "test@example.com"
        val password = ""

        val result = loginFragment.validateCredentials(email, password)

        //membandingkan hasilnya dengan false menggunakan assertEquals()
        assertEquals(false, result)
    }
}
