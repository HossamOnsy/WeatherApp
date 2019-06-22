package com.sam.iotblue.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sam.iotblue.R
import com.sam.iotblue.model.Coord
import com.sam.iotblue.ui.adapters.BookmarkedCoordsAdapter
import com.sam.iotblue.utils.AppUtils.getListOfCoords
import kotlinx.android.synthetic.main.bookmark_list_fragment.*

class BookmarkListFragment : Fragment() {

    companion object {
        fun newInstance() = BookmarkListFragment()
    }

    lateinit var bookmarkedCoordsAdapter: BookmarkedCoordsAdapter
    var coordsList = ArrayList<Coord>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bookmark_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerInitation()
    }

    override fun onResume() {
        super.onResume()

        if (activity != null) {
            bookmarkedCoordsAdapter.clearList()
            bookmarkedCoordsAdapter.updateList(getListOfCoords(activity!!.applicationContext))

        }
    }

    private fun recyclerInitation() {
        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            setUpRecyclerAdapter(coordsList)
        }
    }

    fun setUpRecyclerAdapter(requests: ArrayList<Coord>) {
        recycler_view.adapter = null
        bookmarkedCoordsAdapter = BookmarkedCoordsAdapter(
            requests, activity!!.baseContext
        )
//        (recycler_view.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        recycler_view.adapter = bookmarkedCoordsAdapter
    }

}
