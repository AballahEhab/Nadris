package com.example.android.nadris.util

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class InputCheckerTest{
    @Test
    fun testPasswordValidation() {
        //Given
        var password="Test123!"
        //When
        var res = checkPassword(password)
        //Then
       assertEquals(null, res)

    }
}