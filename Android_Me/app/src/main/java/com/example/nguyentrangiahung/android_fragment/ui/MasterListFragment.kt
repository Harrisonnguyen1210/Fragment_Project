/** Created by @author: Hung Nguyen
 * MasterListFragment has an OnImageClickListener interface. In onAttach function, check the activity
 * containing this fragment if it already implements the interface
 */
package com.example.nguyentrangiahung.android_fragment.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nguyentrangiahung.android_fragment.R
import com.example.nguyentrangiahung.android_fragment.data.AndroidImageAssets
import kotlinx.android.synthetic.main.fragment_master_list.view.*
import android.content.Context
import android.widget.AdapterView


class MasterListFragment : Fragment() {
    lateinit var myCallBack: OnImageClickListener
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_master_list, container, false)
        val myAdapter = MasterListAdapter(context!!, AndroidImageAssets().all)
        view.grid_view.adapter = myAdapter
        view.grid_view.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            myCallBack.onImageSelected(position)
        }
        return view
    }

    // This makes sure that the host activity has implemented the callback interface
    // If not, it throws an exception
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            myCallBack = activity as OnImageClickListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement OnHeadlineSelectedListener")
        }
    }

    //An interface to calls a method in the host activity named onImageSelected
    interface OnImageClickListener {
        fun onImageSelected(position: Int)
    }
}
