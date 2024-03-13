package data.repository

import data.UserHoldingResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class UserHoldingRepository(private val apiService: ApiService) {

        suspend fun getUserHoldings(): UserHoldingResponse {
            return suspendCancellableCoroutine { continuation ->
                apiService.getUserHoldingsData().enqueue(object : Callback<UserHoldingResponse> {
                    override fun onResponse(call: Call<UserHoldingResponse>, response: Response<UserHoldingResponse>) {
                        if (response.isSuccessful) {
                            val userHoldings = response.body()
                            if (userHoldings != null) {
                                continuation.resume(userHoldings)
                            } else {
                                continuation.resumeWithException(NullPointerException("Response body is null"))
                            }
                        } else {
                            continuation.resumeWithException(HttpException(response))
                        }
                    }

                    override fun onFailure(call: Call<UserHoldingResponse>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }
                })

                continuation.invokeOnCancellation {
                    // Cancel Retrofit call if coroutine is cancelled
                    // You can handle cancellation cleanup here
                    // call.cancel()
                }
            }
        }
}