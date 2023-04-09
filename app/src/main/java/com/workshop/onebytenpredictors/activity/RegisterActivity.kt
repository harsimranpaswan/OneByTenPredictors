package com.workshop.onebytenpredictors.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.workshop.onebytenpredictors.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    var nameV: Boolean = false
    var mailV: Boolean = false
    var passwordV: Boolean = false
    var cnfPasswordV: Boolean = false

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        nameFocusListener()
        emailFocusListener()
        passwordValidator()
        cnfValidator()

        binding.buttonSignup.setOnClickListener {
            if (nameV && mailV && passwordV && cnfPasswordV) {
                firebaseAuth.createUserWithEmailAndPassword(binding.etMail.text.toString(), binding.etPassword.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this, "Please check all fields", Toast.LENGTH_SHORT).show()
            }
        }
        binding.tvback.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.tvSignin.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun nameFocusListener() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etName.text.toString().isEmpty()) {
                    binding.nameLayout.helperText = "Name is Required"
                    nameV=false
                } else {
                    binding.nameLayout.helperText = null
                    nameV = true;
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
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

    private fun passwordValidator() {
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.passwordLayout.helperText = validPassword()
                passwordV = validPassword() == null && binding.etCnfPassword.text.toString() == binding.etPassword.text.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun validPassword(): String? {
        val passwordText = binding.etPassword.text.toString()
        if (passwordText.length < 8) {
            return "Minimum 8 Character Password"
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex())) {
            return "Must Contain 1 Upper-case Character"
        }
        if (!passwordText.matches(".*[a-z].*".toRegex())) {
            return "Must Contain 1 Lower-case Character"
        }
        if (!passwordText.matches(".*[@#\$%^&+=].*".toRegex())) {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }
        return null
    }

    private fun cnfValidator() {
        binding.etCnfPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (binding.etCnfPassword.text.toString() != binding.etPassword.text.toString()) {
                    binding.cnfPasswordLayout.helperText = "Passwords Does Not Match"
                    cnfPasswordV=false

                } else {
                    binding.cnfPasswordLayout.helperText = null
                    cnfPasswordV = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }
}
