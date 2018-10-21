/** Created by @author: Hung Nguyen
 * MainActivity implements an interface from MasterListFragment and overrides onImageSelected method
 * to handle image selection click. Furthermore, it checks if the device is a tablet or not and the
 * existence of savedInstanceState to create fragments when the device is a tablet
 */
package com.example.nguyentrangiahung.android_fragment.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.nguyentrangiahung.android_fragment.R
import com.example.nguyentrangiahung.android_fragment.data.AndroidImageAssets
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_master_list.*

class MainActivity : AppCompatActivity(), MasterListFragment.OnImageClickListener {
    private val bundle = Bundle()
    private var mTwoPane = false

    //Function to be called when image is selected
    override fun onImageSelected(position: Int) {
        val headIndex: Int
        val bodyIndex: Int
        val legIndex: Int
        Toast.makeText(this, "Position clicked = $position", Toast.LENGTH_SHORT).show()
        val myType = position / 12
        val listIndex = position - myType * 12
        if (mTwoPane) {
            when (myType) {
                0 -> {
                    val headFragment = BodyPartFragment()
                    headFragment.setImageList(AndroidImageAssets().headsList)
                    headFragment.setImageIndex(listIndex)
                    supportFragmentManager.beginTransaction()
                            .add(R.id.head_container, headFragment)
                            .commit()
                }
                1 -> {
                    val bodyFragment = BodyPartFragment()
                    bodyFragment.setImageList(AndroidImageAssets().bodiesList)
                    bodyFragment.setImageIndex(listIndex)
                    supportFragmentManager.beginTransaction()
                            .add(R.id.body_container, bodyFragment)
                            .commit()
                }
                2 -> {
                    val legFragment = BodyPartFragment()
                    legFragment.setImageList(AndroidImageAssets().legsList)
                    legFragment.setImageIndex(listIndex)
                    supportFragmentManager.beginTransaction()
                            .add(R.id.leg_container, legFragment)
                            .commit()
                }
            }
        } else {
            when (myType) {
                0 -> {
                    headIndex = listIndex
                    bundle.putInt("headIndex", headIndex)
                }
                1 -> {
                    bodyIndex = listIndex
                    bundle.putInt("bodyIndex", bodyIndex)
                }
                2 -> {
                    legIndex = listIndex
                    bundle.putInt("legIndex", legIndex)
                }
            }
            next_button.setOnClickListener {
                intent = Intent(this, AndroidMeActivity::class.java)
                intent.putExtra("My Bundle", bundle)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Check the condition if the device is a tablet
        if (android_me_linear_layout != null) {
            mTwoPane = true
            grid_view.numColumns = 2
            next_button.visibility = View.GONE
            if (savedInstanceState == null) {
                val headFragment = BodyPartFragment()
                headFragment.setImageList(AndroidImageAssets().headsList)
                supportFragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit()
                val bodyFragment = BodyPartFragment()
                bodyFragment.setImageList(AndroidImageAssets().bodiesList)
                supportFragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit()
                val legFragment = BodyPartFragment()
                legFragment.setImageList(AndroidImageAssets().legsList)
                supportFragmentManager.beginTransaction()
                        .add(R.id.leg_container, legFragment)
                        .commit()
            }
        } else {
            mTwoPane = false
        }
    }
}
