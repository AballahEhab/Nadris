package com.example.android.nadris.util

import android.util.Patterns
import com.example.android.nadris.InputError

fun checkTextLength(value: String): InputError? {
    return if (value.length > 2)
        InputError.EMPTY_FIELD
    else
        null

}

fun checkEmpty(value: String): InputError? {
    return if (value.isEmpty())
        InputError.EMPTY_FIELD
    else
        null

}

fun matchPasswords(password1: String, password2: String): InputError? {
    return if (password1 == password2)
        null
    else
        InputError.EMPTY_FIELD

}
//sure an email match pattern
fun matchEmailPattern(email: String): InputError? {
    return if (email.matches(Patterns.EMAIL_ADDRESS.toRegex()))
        null
    else
        InputError.NOT_AN_EMAIL_FORMAT
}
 //check for password on SpecialLetter -LowerLetter -UpperLetter -Empty ) and  return errorFlag
fun checkPassword(password: String): InputError? {
    var errorFlag = checkPasswordLength(password)

    containsSpecialLetter(password)?.let {
        errorFlag = it
    }
    containsLowerLetter(password)?.let {
        errorFlag = it
    }
    containsUpperLetter(password)?.let {
        errorFlag = it
    }

    checkEmpty(password)?.let {
        errorFlag = it
    }
    return errorFlag
}

fun containsUpperLetter(value: String): InputError? {
    return if (value.matches(".*[A-Z].*".toRegex()))
        null
    else
        InputError.NOT_CONTAIN_UPPERCASE_LETTER

}

fun containsLowerLetter(value: String): InputError? {
    return if (value.matches(".*[a-z].*".toRegex()))
        null
    else
        InputError.NOT_CONTAIN_A_LOWER_CASE_LETTER

}

fun containsSpecialLetter(value: String): InputError? {
    return if (value.matches(".*[!@#\$%^&+=].*".toRegex()))
        null
    else
        InputError.NOT_CONTAIN_A_SPECIAL_CHARACTER_LETTER

}

fun checkPasswordLength(value: String): InputError? {
    return if (value.length >= 8)
        null
    else
        InputError.SHORT_PASSWORD

}

fun getErrorMessage(flag: InputError?): String? {
    return when (flag) {
        InputError.EMPTY_FIELD -> "برجاء ملئ هذا الحقل"
        InputError.NOT_AN_EMAIL_FORMAT -> "القيمة الدخله لا تطابق صيغة بريد إلكترونى"
        InputError.SHORT_PASSWORD -> "كلمة المرور يجب أن تكون 8 رموز بحد أدنى"
        InputError.NOT_CONTAIN_UPPERCASE_LETTER -> "يجب أن تحتوى كلمة المرور على حرف كبير واحد على الأقل"
        InputError.NOT_CONTAIN_A_LOWER_CASE_LETTER -> "يجب أن تحتوى كلمة المرور على حرف صغير واحد على الأقل"
        InputError.NOT_CONTAIN_A_SPECIAL_CHARACTER_LETTER -> "يجب أن يحتوى كلمة المرور على حرف خاص !@#$%^&*_+ "
        InputError.PASSWORDS_NOT_MATCHED -> "غير متطابق"
        InputError.NOT_VALID_MOBILE_NUMB -> "القيمة الدخله لا تطابق صيغة رقم موبايل"
        else -> null
    }
}