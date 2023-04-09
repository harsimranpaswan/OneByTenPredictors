package com.workshop.onebytenpredictors.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.workshop.onebytenpredictors.R
import com.workshop.onebytenpredictors.databinding.ActivityForgotBinding


class ForgotActivity : AppCompatActivity() {
    var mailV: Boolean = false

    private lateinit var binding: ActivityForgotBinding
    private lateinit var firebaseAuth: FirebaseAuth
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        emailFocusListener()

        binding.buttonRecover.setOnClickListener {
            if (mailV) {
                firebaseAuth.sendPasswordResetEmail(binding.etMail.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Please check all fields", Toast.LENGTH_SHORT).show()
            }
        }
        binding.tvBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun emailFocusListener() {
        binding.etMail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.mailLayout.helperText = validEmail()
                mailV = validEmail() == null
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }
    private fun validEmail(): String? {
        val emailText = binding.etMail.text.toString()
        if (emailText == null) {
            return "Email cannot be empty"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email Address"
        }
        return null
    }
}