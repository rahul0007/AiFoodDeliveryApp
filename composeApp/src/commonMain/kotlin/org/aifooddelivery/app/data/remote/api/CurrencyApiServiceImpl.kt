package org.aifooddelivery.app.data.remote.api
import domain.model.RequestState
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import org.aifooddelivery.app.core.network.NetworkClient
import org.aifooddelivery.app.data.model.ApiResponse
import org.aifooddelivery.app.data.model.Currency
import org.aifooddelivery.app.data.model.toCurrency
import org.aifooddelivery.app.domain.CurrencyApiService
class CurrencyApiServiceImpl : CurrencyApiService {
    companion object {
        private const val ENDPOINT = "https://api.currencyapi.com/v3/latest"
        private const val API_KEY = "cur_live_x5vQhKrsY2St4dK8Dd4hjZGgpKJYhphhy40bgecy"
    }

    private val httpClient = NetworkClient.httpClient
    override suspend fun getLatestExchangeRates(): RequestState<List<Currency>> {
        return try {
            val response: HttpResponse = httpClient.get(ENDPOINT) {
                headers {
                    append("apikey", API_KEY)
                }
            }

            if (response.status.value == 200) {
                val responseBody = response.bodyAsText()
                val apiResponse = Json.decodeFromString<ApiResponse>(responseBody)
                val availableCurrencies = apiResponse.data.values.map { it.toCurrency() }

                RequestState.Success(data = availableCurrencies)
            } else {
                RequestState.Error(message = "HTTP Error: ${response.status}")
            }

        } catch (e: Exception) {
            RequestState.Error(message = e.message ?: "Unknown error")
        }
    }
}
