package org.aifooddelivery.app.domain.usecase

import domain.model.RequestState
import org.aifooddelivery.app.data.model.LoginResponse
import org.aifooddelivery.app.domain.repository.UserRepository

class LoginUseCase(private val api: UserRepository) {
    suspend operator fun invoke(username: String, password: String): RequestState<LoginResponse> {
        return api.loginUser(username, password)
    }


}