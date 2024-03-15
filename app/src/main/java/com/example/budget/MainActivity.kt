package com.example.budget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val logInButton = findViewById<Button>(R.id.logInButton)
    logInButton.setOnClickListener {
      val intent = Intent(this, BudgetActivity::class.java)
      startActivity(intent)
      overridePendingTransition(R.anim.silde_in, R.anim.slide_out)
    }
  }
}