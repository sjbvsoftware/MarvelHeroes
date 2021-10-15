package com.openbank.marvel_app_heroes

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.transition.TransitionInflater
import com.openbank.marvel_app_heroes.ui.CharacterList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AppCompat_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(CharacterList(), "characterList")
    }

    /**
     * function to show the fragment
     *
     * @param name fragment to be shown
     * @param tag  fragment tag
     */
    private fun showFragment(name: Fragment, tag: String) {
        val fragmentManager: FragmentManager = supportFragmentManager
        // check if the fragment is in back stack
        val fragmentPopped: Boolean = fragmentManager.popBackStackImmediate(tag, 0)
        if (!fragmentPopped) {
            // fragment is pop from backStack
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, name, tag)
            fragmentTransaction.addToBackStack(tag)
            fragmentTransaction.commit()
        }
    }

    /**
     * function to show the fragment
     *
     * @param current current visible fragment
     * @param tag     fragment tag
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun showFragmentWithTransition(
        current: Fragment,
        newFragment: Fragment,
        tag: String?,
        sharedCard: View?,
        sharedImage: View?
    ) {
        val fragmentManager: FragmentManager = supportFragmentManager
        // check if the fragment is in back stack
        val fragmentPopped: Boolean = fragmentManager.popBackStackImmediate(tag, 0)
        if (fragmentPopped) {
            // fragment is pop from backStack
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                current.sharedElementReturnTransition =
                    TransitionInflater.from(this).inflateTransition(R.transition.default_transition)
                current.exitTransition = TransitionInflater.from(this)
                    .inflateTransition(android.R.transition.no_transition)
                newFragment.sharedElementEnterTransition =
                    TransitionInflater.from(this).inflateTransition(R.transition.default_transition)
                newFragment.enterTransition = TransitionInflater.from(this)
                    .inflateTransition(android.R.transition.no_transition)
            }

            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, newFragment, tag)
            fragmentTransaction.addToBackStack(tag)

            addSharedElement(fragmentTransaction, sharedCard)
            addSharedElement(fragmentTransaction, sharedImage)

            fragmentTransaction.commit()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun addSharedElement(fragmentTransaction: FragmentTransaction, sharedView: View?) {
        if (sharedView != null) {
            fragmentTransaction.addSharedElement(sharedView, sharedView.transitionName)
        }
    }

    /**
     * function to go back to previous fragment
     */
    private fun oneStepBack() {
        val fts: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentManager: FragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount >= 2) {
            fragmentManager.popBackStackImmediate()
            fts.commit()
        } else {
            doubleClickToExit()
        }
    }

    private var backPressed: Long = 0
    /**
     * function to go exit on double click
     */
    private fun doubleClickToExit() {
        if (backPressed + 2000 > System.currentTimeMillis()) finish() else Toast.makeText(
            this@MainActivity,
            getString(R.string.click_exit_message),
            Toast.LENGTH_SHORT
        ).show()
        backPressed = System.currentTimeMillis()
    }

    override fun onBackPressed() {
        oneStepBack()
    }

}