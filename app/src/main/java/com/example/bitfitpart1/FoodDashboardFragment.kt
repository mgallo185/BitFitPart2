package com.example.bitfitpart1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [FoodDashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodDashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val food= mutableListOf<DisplayFood>()
    private lateinit var foodAdapter: FoodAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_food_dashboard, container, false)

        val maxTextView= view.findViewById<TextView>(R.id.maxNum)
        val minTextView= view.findViewById<TextView>(R.id.minNum)
        val averageTextView= view.findViewById<TextView>(R.id.averageNum)

        foodAdapter= FoodAdapter(view.context,food )

        val addfoodButton= view.findViewById<Button>(R.id.enter_food_button2)

        lifecycleScope.launch {
            (activity?.application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayFood(
                        entity.foodName,
                        entity.calories.toString().toInt()
                    )
                }.also { mappedList ->
                    food.clear()
                    food.addAll(mappedList)



                    var highest : Int = 0
                    for (calories in food.map{it.calories} ){
                        if(highest < calories!!) {
                            highest = calories
                        }
                    }
                    maxTextView.text = highest.toString()

                    var lowest : Int = highest
                    for (calories in food.map{it.calories} ){
                        if(lowest > calories!!) {
                            lowest = calories
                        }
                    }
                    minTextView.text = lowest.toString()

                    var avgCal : Double = 0.00
                    var total :Double = 0.00
                    for (calories in food.map{it.calories}){
                        if (calories != null) {
                            total += calories
                        }
                    }
                    avgCal = total/food.size
                    averageTextView.text = avgCal.toString()







                    foodAdapter.notifyDataSetChanged()
                }
            }
        }


        addfoodButton.setOnClickListener {
            val intent = Intent(requireActivity(), AddFoodActivity::class.java)
            startActivity(intent)
        }




    return view
    }

    companion object {
      fun newInstance(): FoodDashboardFragment{
          return FoodDashboardFragment()
      }
    }
}