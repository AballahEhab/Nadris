package com.example.android.nadris

enum class PasswordError   {
    SHORT_PASSWORD,
    NOT_CONTAIN_UPPERCASE,
    NOT_CONTAIN_LOWER_CASE,
    NOT_CONTAIN_SPECIAL_CHARACTER
}

enum class InputError   {
    EMPTY_FIELD,
    NOT_AN_EMAIL_FORMAT,
    SHORT_INPUT,
    SHORT_PASSWORD,
    NOT_CONTAIN_UPPERCASE_LETTER,
    NOT_CONTAIN_A_LOWER_CASE_LETTER,
    NOT_CONTAIN_A_SPECIAL_CHARACTER_LETTER,
    PASSWORDS_NOT_MATCHED,
    NOT_VALID_MOBILE_NUMB
}

enum class Mode {
    EDIT,
    CREATE;

    fun isInEditMode():Boolean {
        return this == EDIT
    }
}

    const val TOKEN_PREFIX = "Bearer "
    const val REQUEST_IMAGE_CAPTURE = 1
    const val PHOTO_PICKER_REQUEST_CODE = 2
    const val CAMERA_PERMISSION_REQUEST_CODE = 3
    const val STORAGE_PERMISSION_REQUEST_CODE = 4