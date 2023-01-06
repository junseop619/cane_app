package com.example.first_project

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.first_project.databinding.ActivityMainBinding
import com.example.first_project.databinding.ActivityPagerBinding

class PagerActivity : AppCompatActivity() {
    class  ExViewPagerAdapter(fa:FragmentActivity) : FragmentStateAdapter(fa) {
        var fragments = listOf<Fragment>()

        override fun getItemCount(): Int  = fragments.size

        override fun createFragment(position: Int): Fragment {
           return fragments.get(position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentList = listOf(OneFragment(),TwoFragment(),ThreeFragment(),FourFragment(),FiveFragment(),SixFragment(),SevenFragment())
        val adapter = ExViewPagerAdapter(this)
        adapter.fragments = fragmentList
        binding.vPager2.adapter = adapter
    }
}

