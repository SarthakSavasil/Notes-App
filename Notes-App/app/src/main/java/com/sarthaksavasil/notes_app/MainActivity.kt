package com.sarthaksavasil.notes_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.fragment),
            null
        )
    }


//    override fun onBackPressed() {
//        val fragment =
//            this.supportFragmentManager.findFragmentById(R.id.homeFragment)
////        (fragment as? IOnBackPressed)?.hBackPressed()?.not().let {
////            super.onBackPressed()
//            if (fragment is IOnBackPressed) {
//
//            } else {
//                super.onBackPressed()
////                finish()
////        }
//            }
//
//
////            when(supportFragmentManager.fragments[0].javaClass.simpleName){
////                "homeFragment" -> finish()
////                else -> supportFragmentManager.popBackStack()
////            }
//        }

    }


