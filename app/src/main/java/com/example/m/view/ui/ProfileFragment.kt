@file:Suppress("RedundantNullableReturnType")

package com.example.m.view.ui

import android.annotation.SuppressLint
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
import com.example.m.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@Suppress("RedundantNullableReturnType", "NAME_SHADOWING", "unused")
@AndroidEntryPoint
class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ApplySharedPref")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        val getUser = sharedPreferences.getString("user", "")
        binding.edtUserNameProfile.setText(getUser)

        val getNama = sharedPreferences.getString("nama", "")
        binding.edtNameProfile.setText(getNama)

        val getTgl = sharedPreferences.getString("tgl", "")
        binding.edtTanggalLahirProfile.setText(getTgl)

        val getAlamat = sharedPreferences.getString("alamat", "")
        binding.edtAlamat.setText(getAlamat)

        binding.btnUpdate.setOnClickListener {
            val getUsername = binding.edtUserNameProfile.text.toString()
            val getNamaLengkap = binding.edtNameProfile.text.toString()
            val getTglLahir = binding.edtTanggalLahirProfile.text.toString()
            val getAlamat = binding.edtAlamat.text.toString()
            val addUser = sharedPreferences.edit()
            addUser.putString("user", getUsername)
            addUser.putString("nama", getNamaLengkap)
            addUser.putString("tgl", getTglLahir)
            addUser.putString("alamat", getAlamat)
            addUser.apply()

            Toast.makeText(context, "Update Berhasil", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
        }

        binding.btnLogout.setOnClickListener {
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signOut()
            val addUser = sharedPreferences.edit()
            addUser.remove("nama")
            addUser.remove("tgl")
            addUser.remove("alamat")
            addUser.commit()
            Toast.makeText(context, "Keluar Berhasil", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment2)
        }
    }

    fun updateButtonClicked() {
        val newUser = binding.edtUserNameProfile.text.toString()
        val newNama = binding.edtNameProfile.text.toString()
        val newTgl = binding.edtTanggalLahirProfile.text.toString()
        val newAlamat = binding.edtAlamat.text.toString()

        val editor = sharedPreferences.edit()
        editor.putString("user", newUser)
        editor.putString("nama", newNama)
        editor.putString("tgl", newTgl)
        editor.putString("alamat", newAlamat)
        editor.apply()
    }


    private fun signout() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
    }
}