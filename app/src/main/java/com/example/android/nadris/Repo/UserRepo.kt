package com.example.android.nadris.Repo


import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.android.nadris.database.UserDao
import com.example.android.nadris.database.UserData
import com.example.android.nadris.network.AuthModel
import com.example.android.nadris.network.ResultData
import com.example.android.nadris.network.UserDataSource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val dao: UserDao,
    private val remoteSource: UserDataSource,
) {
        val userdata = responseLiveData(
            roomQueryToRetriveData = {dao.get() },
            networkRequest = { remoteSource.getData() },
            roomQueryToSaveData = { dao.insert(DataMapper.getUserLocalModel((it))) })
}

fun <T, L> responseLiveData(
    roomQueryToRetriveData: suspend () -> LiveData<T>,
    networkRequest: suspend () -> ResultData<L>,
    roomQueryToSaveData: suspend (L) -> Unit
): LiveData<ResultData<T>> =
    liveData(Dispatchers.IO) {
        emit(ResultData.loading(null))
        val source = roomQueryToRetriveData().map { ResultData.success(it) }
        emitSource(source)

        val responseStatus = networkRequest()

        when (responseStatus) {
            is ResultData.Success -> {
                roomQueryToSaveData(responseStatus.value)
            }

            is ResultData.Failure -> {
                emit(ResultData.failure(responseStatus.message))
                emitSource(source)
            }

            else -> {
                emit(ResultData.failure("Something went wrong, please try again later"))
                emitSource(source)
            }
        }

    }

class DataMapper {
    companion object{
    fun getUserLocalModel(remoteModel: AuthModel): UserData {
        return UserData(Email = remoteModel.Email,
            PhoneNumber = remoteModel.PhoneNumber,
            Gender = remoteModel.Gender,
            Exp = remoteModel.Exp,
            Token = remoteModel.Token,
            College = remoteModel.College,
            ExpiresOn = remoteModel.ExpiresOn,
            Grade = remoteModel.Grade,
            Type = remoteModel.Type,
            University = remoteModel.University,
            firstName = remoteModel.FirstName,
            lastName = remoteModel.LastName
        )}
    }

}

interface LiveDataUseCaseWithParams<R> {
    fun execute(): LiveData<R>
}

class UserDataUseCase constructor(private val repository: UserRepository) :
    LiveDataUseCaseWithParams<UserData> {
    override fun execute(): LiveData<UserData> {
        return repository.userdata as LiveData<UserData>
    }
}