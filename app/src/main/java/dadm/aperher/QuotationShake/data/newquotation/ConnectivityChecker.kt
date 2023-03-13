package dadm.aperher.QuotationShake.data.newquotation

import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import javax.inject.Inject

class ConnectivityChecker @Inject constructor(private val manager : ConnectivityManager) {
    fun isConnectionAvailable() : Boolean {
        val networkInfo = manager.getNetworkCapabilities(manager.activeNetwork)
        return networkInfo != null && (networkInfo.hasTransport(TRANSPORT_WIFI) || networkInfo.hasTransport(TRANSPORT_CELLULAR))
    }
}