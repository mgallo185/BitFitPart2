package com.example.bitfitpart1


import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(private val context: Context, private val foodItem: List<DisplayFood>):
    RecyclerView.Adapter<FoodAdapter.Viewholder>() {
    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // subviews within an item(or row)
        val foodTextView: TextView
        val calorieTextView: TextView


        // Act as a constructor to accept each row(or item) and look for subviews
        init{
            foodTextView = itemView.findViewById<TextView>(R.id.foodName)
            calorieTextView = itemView.findViewById<TextView>(R.id.calorieNum)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val context = parent.context // get reference of the xml file
        val inflater = LayoutInflater.from(context)

        // LayoutInflater takes an XML file as input and builds the View objects from it.
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.item_food, parent, false)
        // Return a new holder instance
        return Viewholder(contactView)
    }
    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        // Get the data model based on position
        val caloriesItem = foodItem.get(position)

        // Set item views based on views and data model
        holder.foodTextView.text = caloriesItem.foodName
        holder.calorieTextView.text = caloriesItem.calories.toString()
    }


    override fun getItemCount(): Int {
        return foodItem.size
    }

    }





