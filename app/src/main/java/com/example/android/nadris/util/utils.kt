package com.example.android.nadris.util

import android.view.WindowManager
import androidx.fragment.app.FragmentActivity
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.*
import org.json.JSONObject
import retrofit2.Response
import java.net.HttpURLConnection

//inline fun <ResultType, RequestType> networkBoundResource(
//    crossinline query: () -> Flow<ResultType>,
//    crossinline fetch: suspend () -> RequestType,
//    crossinline saveFetchResult: suspend (RequestType) -> Unit,
//    crossinline shouldFetch: (ResultType) -> Boolean = { true }
//) = flow {
//    val data = query().first()
//
//    val flow = if (shouldFetch(data)) {
//
//        emit(Resource.Loading(data))
//
//        try {
//             val res=  fetch()
//             saveFetchResult(res)
//            query().map { Resource.Success(it) }
//
//        } catch (throwable: Throwable) {
//            query().map { Resource.Error(throwable, it) }
//            }
//
//    } else {
//        query().map { Resource.Success(it) }
//    }
//
//    emitAll(flow)
//}

inline fun <DatabaseModel, NetworkModel> postToApiHandler(
    crossinline request: suspend () -> Response<NetworkModel>,
    crossinline saveFetchResult: suspend (DatabaseModel) -> Unit,
    crossinline convertToSaveModel:  (NetworkModel) -> DatabaseModel
    ) = flow {
    emit(Result.Loading<Nothing>())
    try {

        val response = request()
        if (response.isSuccessful){

            val result = response.body()
            saveFetchResult(convertToSaveModel(result!!))
            emit(Result.Success(data = result))

        }else{
            val errorMessage =when(response.code()){
                HttpURLConnection.HTTP_BAD_REQUEST ->response.errorBody()?.string()
                else ->  "حدث خطأ غير متوقع برجاء المحاولة مرة أخرى فى وقت لاحق"
            }
            emit(Result.Error<Nothing>(error = errorMessage!!))
        }

    } catch (throwable: Throwable) {
        val errorMessage = when (throwable) {
            is java.net.SocketTimeoutException ->  "نفذ الوقت لم نستطع الوصول لخادم برجاء المحاولة مرة أخرى فى وقت لاحق"
            else ->  "حدث خطأ غير متوقع برجاء المحاولة مرة أخرى فى وقت لاحق"
        }
        emit(Result.Error<Nothing>(error = errorMessage))
    }
}

fun disableUserInterAction(activity: FragmentActivity?) =
    activity?.window?.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

fun enableUserInterAction(activity: FragmentActivity?)=
    activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

//class NetworkUtils @Inject constructor( private val context: Context) {
//
//    fun isNetworkAvailable(): Boolean {
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val nw = connectivityManager.activeNetwork ?: return false
//            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
//            return when {
//                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//                //for other device how are able to connect with Ethernet
//                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//                //for check internet over Bluetooth
//                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
//                else -> false
//            }
//        } else {
//            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
//            return nwInfo.isConnected
//        }
//    }
//}
