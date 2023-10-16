package com.example.bitfitpart1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFoodActivity:  AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val confirmButton =  findViewById<Button>(R.id.confirmFood)


        confirmButton.setOnClickListener {
            val enterFood= findViewById<EditText>(R.id.typeinFood)
            val enterCalories= findViewById<EditText>(R.id.typeinCalories)
            lifecycleScope.launch(Dispatchers.IO)  {
                (application as FoodApplication).db.foodDao().insert(
                    FoodEntity(enterFood.text.toString(), enterCalories.text.toString() )
                )
            }


            val intent = Intent(this@AddFoodActivity, MainActivity::class.java)
            startActivity(intent)
        }




    }
}