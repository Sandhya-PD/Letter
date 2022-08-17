package com.example.lettervaultpro


import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.*


class SettingsFragment : PreferenceFragmentCompat(),SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings)
        val switch = findPreference<SwitchPreferenceCompat>("notifications")

        preferenceManager.sharedPreferences?.apply {
            switch?.isChecked = getBoolean("notif_state", false)
        }


        switch?.setOnPreferenceChangeListener { preference, newValue ->
            var isEnabled = false
            if (newValue is Boolean) {
                isEnabled = newValue
            }
            if (isEnabled) {
//                gotoNotificationOn()
                preferenceManager.sharedPreferences?.apply {
                    edit().putBoolean("notif_state", true).apply()
                }
            } else {
                preferenceManager.sharedPreferences?.apply {
                    edit().putBoolean("notif_state", false).apply()
                }
            }

            true
        }


    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        val darkModeString = getString(R.string.dark_mode)
        key?.let {
            if (it == darkModeString) sharedPreferences?.let { pref ->
                val darkModeValues = resources.getStringArray(R.array.night_mode_values)
                when (pref.getString(darkModeString, darkModeValues[0])) {
                    darkModeValues[0] -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                        preferenceManager.sharedPreferences?.apply {
                            edit().putInt("darkModeValue", 0).apply()
                        }
                    }
                    darkModeValues[1] ->{
                        Log.d("msg","Dark Mode")
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        preferenceManager.sharedPreferences?.apply {
                            edit().putInt("darkModeValue", 1).apply()
                        }

                    }
                    darkModeValues[2] -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        preferenceManager.sharedPreferences?.apply {
                            edit().putInt("darkModeValue", 2).apply()
                        }
                    }

                    else -> {}
                }
            }
        }



    }

//    override fun provideSummary(preference: ListPreference): CharSequence? =if (preference.key == getString(R.string.dark_mode)) preference.entry
//    else "Unknown Preference"
}

//private fun goInDarkMode():Boolean{
//    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//    return true
//
//}
//
//private fun goToLightMode():Boolean{
//    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//    return true
//}
//
//
//
//
//private fun goToNotification(): Boolean {
//    return false
//}


