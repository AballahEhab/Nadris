package com.example.android.nadris.util

import android.graphics.Bitmap
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

fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap? {
    var width = image.width
    var height = image.height
    val bitmapRatio = width.toFloat() / height.toFloat()
    if (bitmapRatio > 1) {
        width = maxSize
        height = (width / bitmapRatio).toInt()
    } else {
        height = maxSize
        width = (height * bitmapRatio).toInt()
    }
    return Bitmap.createScaledBitmap(image, width, height, true)
}

  