package com.example.bitfitpart1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AddFoodActivity:  AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val confirmButton =  findViewById<Button>(R.id.confirmFood)

        confirmButton.setOnClickListener {
            val intent = Intent(this@AddFoodActivity, MainActivity::class.java)
            startActivity(intent)
        }




    }
}