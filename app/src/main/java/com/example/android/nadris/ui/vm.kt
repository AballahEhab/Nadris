package com.example.android.nadris.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android.nadris.Repo.UserDataUseCase
import com.example.android.nadris.database.UserData

class userVM constructor(useCase: UserDataUseCase) : ViewModel() {
    val user : LiveData<UserData> = useCase.execute()
}