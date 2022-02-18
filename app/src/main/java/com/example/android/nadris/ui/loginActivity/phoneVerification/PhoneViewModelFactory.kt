package com.example.android.nadris.ui.loginActivity.phoneVerification

//class Factory(
//    private val assistedFactory: PhoneViewModelAssistedFactory,
//    private val OTB: String,
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return assistedFactory.create(OTB) as T
//    }
//}
//class PhoneViewModelFactory(private val receivedOTB:String):ViewModelProvider.Factory {
//
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//
//        @Suppress("unchecked_cast")
//        if(modelClass.isAssignableFrom(PhoneViewModel::class.java)){
//            return PhoneViewModel(receivedOTB) as T
//        }
//                throw IllegalArgumentException("Unknown ViewModel class")
//    }
//
//
//}