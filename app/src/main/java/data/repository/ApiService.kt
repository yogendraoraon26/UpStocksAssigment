package data.repository

import data.UserHolding
import data.UserHoldingResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("v3/bde7230e-bc91-43bc-901d-c79d008bddc8")
    fun getUserHoldingsData(): Call<UserHoldingResponse>
}
