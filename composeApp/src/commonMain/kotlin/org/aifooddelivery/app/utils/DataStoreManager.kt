package org.aifooddelivery.app.utils

// commonMain
interface DataStoreManager {
    suspend fun setOnboardingCompleted(completed: Boolean)
    suspend fun setUserEmail(string: String)
    suspend fun getEmail():String
    suspend fun isOnboardingCompleted(): Boolean
    suspend fun isLogin(): Boolean
    suspend fun setLoginCompleted(completed: Boolean)
    suspend fun clearAll()

}