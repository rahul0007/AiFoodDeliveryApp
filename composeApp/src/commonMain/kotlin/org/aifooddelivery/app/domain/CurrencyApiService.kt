package org.aifooddelivery.app.domain
import domain.model.RequestState
import org.aifooddelivery.app.data.model.Currency

interface CurrencyApiService {
    suspend fun getLatestExchangeRates(): RequestState<List<Currency>>
}