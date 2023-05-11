package com.example.righttothecity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CommentActivity : AppCompatActivity() {

    private lateinit var slider: SeekBar
    private lateinit var smiley: ImageView
    private lateinit var commentText: EditText
    private lateinit var commentCounter: TextView
    private lateinit var hashtagText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        // Находим элементы по их идентификаторам
        slider = findViewById(R.id.slider)
        smiley = findViewById(R.id.smiley)
        commentText = findViewById(R.id.comment_text)
        commentCounter = findViewById(R.id.comment_counter)
        hashtagText = findViewById(R.id.hashtag_text)
        submitButton = findViewById(R.id.submit_button)

        // Устанавливаем слушателя для слайдера
        slider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Меняем изображение смайлика в зависимости от значения слайдера
                when {
                    //progress < 3 -> smiley.setImageResource(R.drawable.sad)
                    //progress > 7 -> smiley.setImageResource(R.drawable.happy)
                    //else -> smiley.setImageResource(R.drawable.neutral)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Устанавливаем слушателя для текстового поля комментария
        commentText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Обновляем счетчик символов при изменении текста
                val remaining = 300 - s.toString().length
                commentCounter.text = remaining.toString()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Устанавливаем слушателя для кнопки "Готово"
        submitButton.setOnClickListener {
            // Получаем значения полей комментария и хэштега
            val comment = commentText.text.toString()
            val hashtag = hashtagText.text.toString()

            val intent = Intent(this, YandexMapActivity::class.java)
            startActivity(intent)
            // TODO: обработка данных и переход на другой экран
        }
    }
}
