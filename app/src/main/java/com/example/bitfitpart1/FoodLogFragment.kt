package com.example.bitfitpart1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [FoodLogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodLogFragment : Fragment() {
    private val food= mutableListOf<DisplayFood>()
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_food_log, container, false)


        val layoutManager = LinearLayoutManager(context)
        foodRecyclerView= view.findViewById(R.id.food_RecyclerView)
        foodAdapter= FoodAdapter(view.context,food )
        foodRecyclerView.layoutManager = layoutManager
        foodRecyclerView.setHasFixedSize(true)
        foodRecyclerView.adapter= foodAdapter

        lifecycleScope.launch {
            (activity?.application as FoodApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayFood(
                        entity.foodName,
                        entity.calories
                    )
                }.also { mappedList ->
                    food.clear()
                    food.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }
        }

        val addfoodButton = view.findViewById<Button>(R.id.enter_food_button)


        addfoodButton.setOnClickListener {
            val intent = Intent(requireActivity(), AddFoodActivity::class.java)
            startActivity(intent)
        }

    return view
    }



    companion object {
        fun newInstance(): FoodLogFragment{
            return FoodLogFragment()
        }
    }
}