package com.example.bitfitpart1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [FoodDashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FoodDashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var average: String? =null;
    private var min: String?= null;
    private var max: String? =null;
   private lateinit var averageValue: TextView
   private lateinit var minValue: TextView
    private lateinit var maxValue: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_food_dashboard, container, false)

        averageValue=view.findViewById(R.id.averageNum)
     minValue = view.findViewById(R.id.minNum)
        maxValue= view.findViewById(R.id.maxNum)

        averageValue.text= average
        minValue.text=  min
        maxValue.text= max


    return view
    }

    companion object {
      fun newInstance(): FoodDashboardFragment{
          return FoodDashboardFragment()
      }
    }
}