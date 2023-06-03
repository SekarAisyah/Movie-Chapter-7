package com.example.m.view.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.m.R
import com.example.m.databinding.FragmentResgisterBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@Suppress("RedundantNullableReturnType")
@AndroidEntryPoint
class ResgisterFragment : Fragment() {

    lateinit var binding: FragmentResgisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sharedRegis: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResgisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedRegis = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            val getUsername = binding.edtUsernameRegister.text.toString()
            val getEmail = binding.edtEmailRegister.text.toString()
            val getPassword = binding.edtPasswordRegister.text.toString()
            val getUlangPass = binding.edtUlangiPassword.text.toString()

            val addUser = sharedRegis.edit()
            addUser.putString("user", getUsername)

            if (validateInput(getUsername, getEmail, getPassword, getUlangPass)) {
                addUser.apply()
                firebaseAuth.createUserWithEmailAndPassword(getEmail, getPassword).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(context, "Register Berhasil", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_resgisterFragment_to_loginFragment2)
                    } else {
                        Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Data Belum Lengkap", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun validateInput(username: String, email: String, password: String, confirmPassword: String): Boolean {
        // Logika validasi input di sini
        // Misalnya, memeriksa apakah semua input tidak kosong dan password sesuai
        return username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword
    }
}
