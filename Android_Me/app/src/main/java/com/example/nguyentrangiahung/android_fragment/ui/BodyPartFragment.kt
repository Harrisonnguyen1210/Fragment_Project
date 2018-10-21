/** Created by @author: Hung Nguyen
 * BodyPartFragment extends from Fragment class, inflates fragment_body_part layout and  checks if
 * there is a savedInstanceState (when the phone is rotated)
 */
package com.example.nguyentrangiahung.android_fragment.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nguyentrangiahung.android_fragment.R
import kotlinx.android.synthetic.main.fragment_body_part.view.*
import java.util.*

class BodyPartFragment : Fragment() {

    //Constant string for array image list and array index
    companion object {
        const val LIST_INDEX = "List index"
        const val IMAGE_LIST = "Image List"
    }
    var myIndex = 0
    lateinit var myArrayList: ArrayList<Int>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Load the saved state (the list of images and list index) if there is one
        if (savedInstanceState != null) {
            myIndex = savedInstanceState.getInt(LIST_INDEX)
            myArrayList = savedInstanceState.getIntegerArrayList(IMAGE_LIST)
        }
        val view = inflater.inflate(R.layout.fragment_body_part, container, false)
        view.image_view.setImageResource(myArrayList[myIndex])
        view.image_view.setOnClickListener {
            val number = Random().nextInt(11)
            myIndex = number
            view.image_view.setImageResource(myArrayList[myIndex])
        }
        return view
    }

    fun setImageIndex(index: Int) {
        myIndex = index
    }

    fun setImageList(arrayList: ArrayList<Int>) {
        myArrayList = arrayList
    }

    //Save current state of this fragment
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putIntegerArrayList(IMAGE_LIST, myArrayList)
        outState.putInt(LIST_INDEX, myIndex)
    }
}