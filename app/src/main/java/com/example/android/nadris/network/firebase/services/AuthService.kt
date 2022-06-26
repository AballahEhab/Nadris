package com.example.android.nadris.network.firebase.services

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject

class AuthService @Inject constructor(private val auth: FirebaseAuth) {

     fun signUPWithEmailAndPassword(checkedEmail:String, checkedPassword:String)=
        auth.createUserWithEmailAndPassword(checkedEmail,checkedPassword)

     fun singInWithEmailAndPassword(checkedEmail:String,checkedPassword:String)=
        auth.signInWithEmailAndPassword(checkedEmail,checkedPassword)

     fun currentUser()=
        auth.currentUser

     fun reAuthenticate(credentials:AuthCredential ) =
         currentUser()?.reauthenticate(credentials)

     fun sendEmailVerification( )=
        currentUser()?.sendEmailVerification()

     fun sendPasswordResetEmail(checkedEmail:String, )=
        auth.sendPasswordResetEmail(checkedEmail)

     fun updateProfile(profileUpdates:UserProfileChangeRequest )=
        currentUser()?.updateProfile(profileUpdates)

     fun updateEmail(checkedEmail:String)=
        currentUser()?.updateEmail(checkedEmail)

     fun updatePassword(checkedPassword:String)=
        currentUser()?.updatePassword(checkedPassword)

     fun signOut()=
        auth.signOut()

     fun delete()=
        currentUser()?.delete()

}