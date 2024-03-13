package data

import com.google.gson.annotations.SerializedName

data class UserHoldingResponse(
    @SerializedName("userHolding") val userHolding: List<UserHolding>
)

data class UserHolding(
    @SerializedName("symbol") val symbol: String,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("ltp") val ltp: Double,
    @SerializedName("avgPrice") val avgPrice: Double,
    @SerializedName("close")val close: Double
)  {
    override fun toString(): String {
        return super.toString()
    }
}