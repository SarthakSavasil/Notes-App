package com.sarthaksavasil.notes_app

import android.app.AlertDialog
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


    override fun onBackPressed() {
        val fragment =
            this.supportFragmentManager.findFragmentById(R.id.homeFragment)
//        (fragment as? IOnBackPressed)?.hBackPressed()?.not().let {
//            super.onBackPressed()
        if (fragment is IOnBackPressed) {

        } else {
            //    super.onBackPressed()

            exit()
        }

//            when(supportFragmentManager.fragments[0].javaClass.simpleName){
//                "homeFragment" -> finish()
//                else -> supportFragmentManager.popBackStack()
//            }
    }

    fun exit(){
        AlertDialog.Builder(this).apply {
            setTitle("Sure?")
            setMessage("Do you want to exit?")
            setPositiveButton("Yes"){_,_ ->
                finish()
            }
            setNegativeButton("No"){_,_ ->

            }
        }.create().show()
    }
}