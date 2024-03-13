package ui.App

import android.app.Application
import data.repository.RetrofitClient

class UserHoldings: Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.initialize()
    }
}