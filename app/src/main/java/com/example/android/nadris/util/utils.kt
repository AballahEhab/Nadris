package com.example.android.nadris.util

import android.view.WindowManager
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.HttpURLConnection

inline fun <DatabaseModel, NetworkModel> getFromApiAndSaveToDataBase(
    crossinline query: suspend () -> DatabaseModel,
    crossinline fetch: suspend () -> Response<NetworkModel>,
    crossinline saveFetchResult: suspend (DatabaseModel) -> Unit,
    crossinline convertToDatabaseModel:  (NetworkModel) -> DatabaseModel,
    crossinline shouldFetch: (DatabaseModel) -> Boolean = { true }
) = flow {

    val data = query()

     if (shouldFetch(data!!)) {

        emit(Result.Loading(data))

        try {
            val response = fetch()
             val res=  convertToDatabaseModel(response.body()!!)
             saveFetchResult(res)
            emit(Result.Success(res))

        } catch (throwable: Throwable) {
            emit(Result.Error(throwable.message.toString(),data))

            }

    } else {
         emit(Result.Success(data))
    }

}

inline fun < NetworkModel> requestAPI(
    crossinline fetch: suspend () -> Response<NetworkModel>,
) = flow {

        emit(Result.Loading<Nothing>())

        try {
            val response = fetch()
             val res=  response.body()!!
            emit(Result.Success(res))

        } catch (throwable: Throwable) {
            emit(Result.Error(throwable.message.toString(),null))

            }

}

inline fun <DatabaseModel, T> postToApiAndSaveToDatabase(
    crossinline request: suspend () -> Response<T>,
    crossinline saveFetchResult: suspend (DatabaseModel) -> Unit,
    crossinline convertToDatabaseModel:  (T) -> DatabaseModel,
    ) = flow {

    emit(Result.Loading<Nothing>())
    try {

        val response = request()
        if (response.isSuccessful){

            val resultAsDatabaseModel = convertToDatabaseModel(response.body()!!)
            saveFetchResult(resultAsDatabaseModel)
            emit(Result.Success(data = resultAsDatabaseModel))

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
            else ->  throwable.message
        }
        emit(errorMessage?.let { Result.Error<Nothing>(error = it) })
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
