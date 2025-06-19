package org.aifooddelivery.app.domain.repository

import com.aifood.UserEntity
import domain.model.RequestState
import org.aifooddelivery.app.data.model.LoginResponse


interface UserRepository {

    suspend fun loginUser(username: String, password: String): RequestState<LoginResponse>

    suspend fun registerUser(user: UserEntity): Boolean

    suspend fun isEmailExists(email: String): Boolean

    suspend fun login(email: String, password: String): UserEntity?

    suspend fun loginWithValidation(email: String, password: String): UserEntity?



}