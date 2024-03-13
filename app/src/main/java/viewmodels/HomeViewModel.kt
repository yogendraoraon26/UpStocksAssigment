package viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.StocksViewData
import data.UserHolding
import data.UserHoldingResponse
import data.repository.PortfolioData
import data.repository.RetrofitClient
import data.repository.UserHoldingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.HttpException

class HomeViewModel : ViewModel() {

    private val _userHoldings = MutableLiveData<List<StocksViewData>>(null)
    val userHoldings: LiveData<List<StocksViewData>> = _userHoldings
    var myList: List<UserHolding> = listOf()
    private var userHoldingRepository : UserHoldingRepository

    var userPortfolio : PortfolioData? = null

    init {
        RetrofitClient.initialize()
        val apiService = RetrofitClient.apiService// Create ApiService instance (replace with your implementation)
        userHoldingRepository = UserHoldingRepository(apiService)
        getUserHoldings()
    }

    private fun getUserHoldings() {
        var viewDataList = emptyList<StocksViewData>()
        viewModelScope.launch {
            try {
                val response = userHoldingRepository.getUserHoldings()
                response.userHolding.map {
                    myList = myList.plus(it)
                    viewDataList = viewDataList.plus(prepareViewData(it))
                }

                _userHoldings.postValue(viewDataList)
            } catch (e: Exception) {
                _userHoldings.postValue(emptyList())
            }
        }
    }

    private fun prepareViewData(userHolding: UserHolding): StocksViewData {

        return StocksViewData(
            userHolding.symbol,
            userHolding.quantity.toString(),
            userHolding.ltp.toString(),
            (userHolding.avgPrice * userHolding.quantity).toString()
        )
    }

    fun prepareUsrHolding() {
        var CTV: Double = 0.0
        var TI: Double = 0.0
        var TPL: Double = 0.0
        var TotalPL: Double = 0.0
        myList.map {

            CTV += it.ltp * it.quantity
            TI += it.avgPrice * it.quantity
            TPL += (it.close - it.ltp) * it.quantity
        }
        TotalPL = CTV - TI
        userPortfolio = PortfolioData("","","","")
        userPortfolio?.currentValue = CTV.toString()
        userPortfolio?.totalInvestment = TI.toString()
        userPortfolio?.todayProfitLoss = TPL.toString()
        userPortfolio?.totalProfitLoss = TotalPL.toString()
    }
}
