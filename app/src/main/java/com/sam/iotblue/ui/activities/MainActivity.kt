package com.sam.iotblue.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sam.iotblue.R
import com.sam.iotblue.ui.adapters.MyViewPageStateAdapter
import com.sam.iotblue.ui.fragments.BookmarkListFragment
import com.sam.iotblue.ui.fragments.MapFragment

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setStatePageAdapter()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                viewPager.currentItem = tab.position
                val fm = supportFragmentManager
                val ft = fm.beginTransaction()
                val count = fm.backStackEntryCount
                if (count >= 1) {
                    supportFragmentManager.popBackStack()
                }
                ft.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // setAdapter();
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                //   viewPager.notifyAll();
            }
        })
    }

    private fun  initViews(){
        viewPager = findViewById(R.id.pager)
        tabLayout = findViewById(R.id.tabs)
    }

    private fun setStatePageAdapter(){

        val myViewPageStateAdapter = MyViewPageStateAdapter(supportFragmentManager)
        myViewPageStateAdapter.addFragment(MapFragment.newInstance(),"Map")
        myViewPageStateAdapter.addFragment(BookmarkListFragment.newInstance(),"Bookmarks")

        viewPager.adapter=myViewPageStateAdapter
        tabLayout.setupWithViewPager(viewPager,true)


    }

}

