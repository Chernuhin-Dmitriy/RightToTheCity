package com.example.righttothecity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var rememberMeCheckBox: CheckBox
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val rememberMe = rememberMeCheckBox.isChecked()

            // Проверка введенных данных и логин пользователя
            if (isLoginValid(email, password)) {
                // Вход успешный
                // TODO: Запомнить пользователя если выбрана опция "Запомнить меня"
                startMainActivity()
            } else {
                // Ошибка входа, предложить ввести данные еще раз
                Toast.makeText(this, "Неправильно введен логин или пароль", Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            // Переход на страницу регистрации
            startRegisterActivity()
        }
    }

    private fun isLoginValid(email: String, password: String): Boolean {
        // TODO: Реализовать проверку введенных данных и логина пользователя
        return true
    }

    private fun startMainActivity() {
        // Переход на главную страницу
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startRegisterActivity() {
        // Переход на страницу регистрации
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}