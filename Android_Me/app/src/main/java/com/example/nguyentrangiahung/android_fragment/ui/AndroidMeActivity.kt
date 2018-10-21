/** Created by @author: Hung Nguyen
 * AndroidMeActivity will be initialized when the phone width is less than 600dp (not a tablet).
 * It will create three fragments for the head, body and leg with the index from the intent bundle
 */
package com.example.nguyentrangiahung.android_fragment.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.nguyentrangiahung.android_fragment.R
import com.example.nguyentrangiahung.android_fragment.data.AndroidImageAssets

class AndroidMeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_me)
        val myBundle = intent.getBundleExtra("My Bundle")
        // New fragments will be created when there is no previously saved state
        if (savedInstanceState == null) {
            val headFragment = BodyPartFragment()
            headFragment.setImageList(AndroidImageAssets().headsList)
            headFragment.setImageIndex(myBundle.getInt("headIndex"))
            supportFragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit()
            val bodyFragment = BodyPartFragment()
            bodyFragment.setImageList(AndroidImageAssets().bodiesList)
            bodyFragment.setImageIndex(myBundle.getInt("bodyIndex"))
            supportFragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit()
            val legFragment = BodyPartFragment()
            legFragment.setImageList(AndroidImageAssets().legsList)
            legFragment.setImageIndex(myBundle.getInt("legIndex"))
            supportFragmentManager.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit()
        }
    }
}