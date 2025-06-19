package org.aifooddelivery.app.data.remote.api

import com.aifood.UserEntity
import domain.model.RequestState
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.aifooddelivery.app.data.model.LoginRequest
import org.aifooddelivery.app.data.model.LoginResponse
import org.aifooddelivery.app.database.UserDao
import org.aifooddelivery.app.domain.repository.UserRepository

class UserRepositoryImpl(private val dao: UserDao) : UserRepository {
    companion object {
        const val ENDPOINT = "https://dummyjson.com/user/login"
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("KTOR LOG => $message")
                }
            }
            level = LogLevel.ALL
        }
    }

    override suspend fun loginUser(
        username: String,
        password: String
    ): RequestState<LoginResponse> {
        return try {
            val loginRequest = LoginRequest(username = username, password = password)
            println("Sending login request: $loginRequest")

            val response = httpClient.post(ENDPOINT) {
                contentType(ContentType.Application.Json)
                setBody(loginRequest)
            }

            val bodyString = response.bodyAsText()
            println("Response code: ${response.status}")
            println("Response body: $bodyString")

            if (response.status.value == 200) {
                val loginResponse = Json.decodeFromString<LoginResponse>(bodyString)
                RequestState.Success(loginResponse)
            } else {
                val errorBody = response.bodyAsText()
                val message = try {
                    val json = kotlinx.serialization.json.Json.parseToJsonElement(errorBody)
                    json.jsonObject["message"]?.jsonPrimitive?.contentOrNull
                } catch (e: Exception) {
                    null
                }
                RequestState.Error("Login failed with status: ${message ?: "Login failed"}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            RequestState.Error("Exception: ${e.message}")
        }
    }

    override suspend fun registerUser(user: UserEntity): Boolean {
        dao.insertUser(user.username, user.email, user.password)
        return true
    }

    override suspend fun isEmailExists(email: String): Boolean {
        return dao.getUserByEmail(email) != null
    }

    override suspend fun login(email: String, password: String): UserEntity? {
        return dao.validateUser(email, password)
    }

    override suspend fun loginWithValidation(email: String, password: String): UserEntity? {
        return  dao.getUserByEmail(email)

    }


}


/*{
    "username": "emilys",
    "password": "emilyspass"

}*/