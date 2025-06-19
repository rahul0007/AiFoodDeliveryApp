package org.aifooddelivery.app.data.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mongodb.kbson.ObjectId

@Serializable
data class ApiResponse(
    val meta: MetaData,
    val data: Map<String, CurrencyDto>
)

@Serializable
data class MetaData(
    @SerialName("last_updated_at")
    val lastUpdatedAt: String
)

open class Currency: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var code: String = ""
    var value: Double = 0.0
}

@Serializable
data class CurrencyDto(
    val code: String,
    val value: Double
)

fun CurrencyDto.toCurrency(): Currency {
    val currency = Currency()
    currency.code = this.code
    currency.value = this.value
    return currency
}
