package com.example.righttothecity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmPasswordInput: EditText
    private lateinit var firstNameInput: EditText
    private lateinit var lastNameInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        confirmPasswordInput = findViewById(R.id.confirm_password_input)
        firstNameInput = findViewById(R.id.first_name_input)
        lastNameInput = findViewById(R.id.last_name_input)

        // Устанавливаем обработчик нажатия на кнопку "Зарегистрироваться"
        findViewById<Button>(R.id.register_button).setOnClickListener {
            // Получаем введенные пользователем данные
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val confirmPassword = confirmPasswordInput.text.toString()
            val firstName = firstNameInput.text.toString()
            val lastName = lastNameInput.text.toString()


            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else if (!(isPasswordValid(email, password))) {
                Toast.makeText(this, "Пароль не может состоять из одних только цифр,\n" +
                        " включать специальные символы: ! @ #  $ % ^ & * ( ) - _ + = ; : , ./ ?  | ` ~ [ ] { },\n" +
                        " не может совпадать с именем почтового ящика.\n" +
                        " Пароль должен состоять из 8 - 16 символов", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            } else {
                // Проверка на дубликат email (пустая проверка)
                // Регистрация пользователя и переход на главную страницу
                registerUser(email, password, firstName, lastName)
            }
        }
    }

    private fun registerUser(email: String, password: String, firstName: String, lastName: String) {
        // Регистрация пользователя
        // TODO: реализовать регистрацию пользователя

        // Переход на главную страницу
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    fun isPasswordValid(email: String, password: String): Boolean {
        val emailPrefix = email.substringBefore("@")
        val onlyDigitsRegex = "\\d+".toRegex()
        val specialCharsRegex = """[!@#\$%\^&*\(\)\-_\+=;:,./\?\\\|`~\[\]\{\}]""".toRegex()
        return password.length in 8..16
                && !onlyDigitsRegex.matches(password)                   // false если только из цифр
                && !specialCharsRegex.containsMatchIn(password)         // false если есть совпадения
                && !password.contains(emailPrefix, ignoreCase = true)   // false если есть @
    }
}
