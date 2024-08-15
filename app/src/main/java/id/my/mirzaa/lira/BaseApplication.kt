package id.my.mirzaa.lira

import android.app.Application

class BaseApplication : Application() {
    val healthConnectManager by lazy {
        HealthConnectManager(this)
    }
}