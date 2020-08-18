package com.example.wangku.onboarding

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.core.view.size
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.wangku.R
import com.example.wangku.onboarding.screens.FirstScreen
import com.example.wangku.onboarding.screens.SecondScreen
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.viewPager.adapter = adapter

        view.next.setOnClickListener {
            if(view.viewPager.currentItem < fragmentList.size - 1) {
                view.viewPager.currentItem++
            } else {
                findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
                onBoardingFinished()
            }

        }

        return view
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}