package com.workshop.onebytenpredictors.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.workshop.onebytenpredictors.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    var mailV: Boolean = false
    var passwordV: Boolean = false

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        emailFocusListener()
        passwordFocusListener()

        binding.buttonSignin.setOnClickListener {
            if (mailV && passwordV) {
                firebaseAuth.signInWithEmailAndPassword(binding.etMail.text.toString(), binding.etPassword.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Please check all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvForgot.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotActivity::class.java))
        }

        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
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

    private fun passwordFocusListener() {
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etPassword.text.toString().isEmpty()) {
                    binding.passwordLayout.helperText = "Name is Required"
                    passwordV = false
                } else {
                    binding.passwordLayout.helperText = null
                    passwordV = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser!=null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}

