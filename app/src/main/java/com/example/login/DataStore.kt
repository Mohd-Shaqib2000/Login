//1
package com.example.login

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
val Context.dataStore by preferencesDataStore(name = "user_prefs")

object UserKeys {
    val EMAIL = stringPreferencesKey("email")
    val PASSWORD = stringPreferencesKey("password")
}

suspend fun saveUserData(context: Context, email: String, password: String) {
    context.dataStore.edit { prefs ->
        prefs[UserKeys.EMAIL] = email
        prefs[UserKeys.PASSWORD] = password
    }
}

suspend fun getUserData(context: Context): Pair<String?, String?> {
    val prefs = context.dataStore.data.first()
    val email = prefs[UserKeys.EMAIL]
    val password = prefs[UserKeys.PASSWORD]
    return Pair(email, password)
}
