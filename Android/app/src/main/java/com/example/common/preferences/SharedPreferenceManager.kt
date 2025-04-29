package com.example.common.preferences

import android.content.Context
import android.content.SharedPreferences


object SharedPreferenceManager {
    private const val PREF_NAME = "my_app_prefs"
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun set(key: String, value: Any) {
        with(preferences.edit()) {
            when(value) {
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is String -> putString(key, value)
                else -> throw IllegalArgumentException("Unsupported type: ${value::class}")
            }
            apply()
        }
    }

    fun <T> getValue(key: String,type: Class<T>, default: T): T {
        return when(type) {
            Boolean::class.java -> preferences.getBoolean(key, default as Boolean) as T
            Float::class.java -> preferences.getFloat(key, default as Float) as T
            Int::class.java -> preferences.getInt(key, default as Int) as T
            Long::class.java -> preferences.getLong(key, default as Long) as T
            String::class.java -> preferences.getString(key, default as String) as T
            else -> throw IllegalArgumentException("Unsupported type: ${type.simpleName}")
        }
    }

    fun remove(key: String) {
        preferences.edit().remove(key).apply()
    }

    fun clear() {
        preferences.edit().clear().apply()
    }
}