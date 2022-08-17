package com.example.lettervaultpro


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import com.example.lettervaultpro.data.Letter
import com.example.lettervaultpro.data.LetterRoomDatabase
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(){
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

//        val args: OpenMessageArgs by navArgs()
        setupActionBarWithNavController(navController)

        val preferences =this.getSharedPreferences("com.example.lettervaultpro_preferences",0)
        val darkModeValue=preferences!!.getInt("darkModeValue",0)

        if(darkModeValue==1){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        if(intent.action.equals("open_fragment")){
            val model=intent.getIntExtra("ModelID",0)
            Log.d("ID", model.toString())

            val action=HomeFragmentDirections.actionHomeFragmentToOpenMessage(model)
            navController.navigate(action)
       }
    }

    override fun onBackPressed() {
        if(!navController.navigateUp()){
            super.onBackPressed()
        }
        navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}




