package com.example.bitfitpart1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var foodRecyclerView: RecyclerView
    private val foodItemList = mutableListOf<DisplayFood>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addfoodButton = findViewById<Button>(R.id.enter_food_button)
        foodRecyclerView = findViewById<RecyclerView>(R.id.food_RecyclerView)
      val foodAdapter= FoodAdapter(this,foodItemList)


        foodRecyclerView.layoutManager= LinearLayoutManager(this)
        foodRecyclerView.adapter =foodAdapter
        lifecycleScope.launch {
            (application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayFood(
                        entity.foodName,
                        entity.calories
                    )
                }.also { mappedList ->
                    foodItemList.clear()
                    foodItemList.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }
        }

        addfoodButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddFoodActivity::class.java)
            startActivity(intent)
        }


    }
}