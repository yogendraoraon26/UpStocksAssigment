package ui.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import data.StocksViewData
import viewmodels.HomeViewModel

@BindingAdapter("setCurrentValue")
fun setCurrentValueViewData(tv : TextView, data : HomeViewModel?){
    val value = data?.userPortfolio?.currentValue?.toDouble()
    val formattedValue = "%.2f".format(value)
    val finalVal = "₹ $formattedValue"
    tv.text = finalVal
}

@BindingAdapter("setTotalInvestment")
fun setTotalInvestmentViewData(tv : TextView, data : HomeViewModel?){
    val value = data?.userPortfolio?.totalInvestment?.toDouble()
    val formattedValue = "%.2f".format(value)
    val finalVal = "₹ $formattedValue"
    tv.text = finalVal
}
@BindingAdapter("setTodayPL")
fun setTodayProfitLoss(tv : TextView, data : HomeViewModel?){
    val value = data?.userPortfolio?.todayProfitLoss?.toDouble()
    val formattedValue = "%.2f".format(value)
    val finalVal = "₹ $formattedValue"
    tv.text = finalVal

}
@BindingAdapter("setTotalProfitLoss")
fun setTotalProfitLoss(tv : TextView, data : HomeViewModel?){
    val value = data?.userPortfolio?.totalProfitLoss?.toDouble()
    val formattedValue = "%.2f".format(value)
    val finalVal = "₹ $formattedValue"
    tv.text = finalVal
}

@BindingAdapter("setLtp")
fun setLtp(tv : TextView, data : StocksViewData?){
    val value = data?.ltp?.toDouble()
    val formattedValue = "%.2f".format(value)
    val finalVal = "LTP: ₹ $formattedValue"
    tv.text = finalVal
}

@BindingAdapter("setProfitLoss")
fun setProfitLoss(tv : TextView, data : StocksViewData?){
    val value = data?.pl?.toDouble()
    val formattedValue = "%.2f".format(value)
    val finalVal = "P/L: ₹ $formattedValue"
    tv.text = finalVal
}