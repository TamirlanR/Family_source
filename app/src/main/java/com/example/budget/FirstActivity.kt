package com.example.budget

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class FirstActivity : AppCompatActivity() {

  private lateinit var imageSwitcher: ImageSwitcher
  private lateinit var textView: TextView
  private lateinit var nextButton: Button
  private lateinit var previousButton: Button
  private lateinit var circles: Array<ImageView>
  private lateinit var startButton: Button
  private var currentIndex = 0
  private val images = arrayOf(R.drawable.first,
    R.drawable.second,
    R.drawable.third,
    R.drawable.fourth,
    R.drawable.fifth)
  private val texts = arrayOf(
    "Возьмите под \n контроль свои \n финансы",
    "Следите за своими \n финансами и достигайте \n своих финансовых целей",
    "Устраните финансовый \n стресс и никогда больше не \n перерасходуйте деньги",
    "Обеспечьте свое финансовое \n будущее и возьмите на себя \n ответственность за свои  деньги",
    "Ставьте напоминания и планируйте свой день"
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_first_screen)

    imageSwitcher = findViewById(R.id.imageView)
    textView = findViewById(R.id.textView3)
    nextButton = findViewById(R.id.button)
    previousButton = findViewById(R.id.button2)
    startButton = findViewById(R.id.button3)

    circles = arrayOf(
      findViewById(R.id.circle1),
      findViewById(R.id.circle2),
      findViewById(R.id.circle3),
      findViewById(R.id.circle4),
      findViewById(R.id.circle5)
    )

    imageSwitcher.setFactory {
      val imageView = ImageView(applicationContext)
      imageView.scaleType = ImageView.ScaleType.FIT_CENTER
      imageView
    }

    nextButton.setOnClickListener {
      currentIndex = (currentIndex + 1) % images.size
      imageSwitcher.setImageResource(images[currentIndex])
      textView.text = texts[currentIndex]
      updateCircles()
      updateButtonsVisibility()
      updateStartButtonVisibility()
    }


    previousButton.setOnClickListener {
      currentIndex = (currentIndex - 1 + images.size) % images.size
      imageSwitcher.setImageResource(images[currentIndex])
      textView.text = texts[currentIndex]
      updateCircles()
      updateButtonsVisibility()
      updateStartButtonVisibility()

    }

    // Начальная установка
    imageSwitcher.setImageResource(images[currentIndex])
    textView.text = texts[currentIndex]
    updateCircles()
    updateButtonsVisibility()
    updateStartButtonVisibility()
  }

  private fun updateCircles() {
    circles.forEachIndexed { index, imageView ->
      if (index == currentIndex) {
        // Установка цвета выбранного круга
        imageView.setColorFilter(ContextCompat.getColor(this, R.color.buttonColor), PorterDuff.Mode.SRC_IN)
      } else {
        // Установка цвета не выбранных кругов
        imageView.setColorFilter(ContextCompat.getColor(this, R.color.unselectedColor), PorterDuff.Mode.SRC_IN)
      }
    }
  }
  private fun updateButtonsVisibility() {
    if (currentIndex == 0) {
      previousButton.visibility = View.GONE
    } else {
      previousButton.visibility = View.VISIBLE
    }

    if (currentIndex == images.size - 1) {
      nextButton.visibility = View.GONE
    } else {
      nextButton.visibility = View.VISIBLE
    }
  }
  private fun updateStartButtonVisibility() {
    if (currentIndex == images.size - 1) {
      startButton.visibility = Button.VISIBLE
      startButton.setOnClickListener {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.silde_in, R.anim.slide_out)
      }
    } else {
      startButton.visibility = Button.GONE
    }
  }

}

