package org.aifooddelivery.app

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.aifooddelivery.app.utils.DataStoreManager

private val Context.dataStore by preferencesDataStore(name = "settings")

class AndroidDataStoreManager(private val context: Context) : DataStoreManager {

    private val ONBOARDING_COMPLETED_KEY = booleanPreferencesKey("onboarding_completed")
    private val LOGIN_COMPLETED = booleanPreferencesKey("login_completed")
    private val USER_EMAIL = stringPreferencesKey("user_email")

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[ONBOARDING_COMPLETED_KEY] = completed
        }
    }

    override suspend fun setUserEmail(string: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_EMAIL] = string
        }
    }

    override suspend fun getEmail():String {
        return context.dataStore.data.map { prefs ->
            prefs[USER_EMAIL] ?: ""
        }.first()
    }

    override suspend fun isOnboardingCompleted(): Boolean {
        return context.dataStore.data.map { prefs ->
            prefs[ONBOARDING_COMPLETED_KEY] ?: false
        }.first()
    }


    override suspend fun setLoginCompleted(completed: Boolean) {
        context.dataStore.edit { it[LOGIN_COMPLETED] = completed }
    }

    override suspend fun isLogin(): Boolean {
        return context.dataStore.data.map { prefs ->
            prefs[LOGIN_COMPLETED] ?: false
        }.first()
    }

    override suspend fun clearAll() {
        context.dataStore.edit { it.clear() }
    }
}