package com.example.android.nadris.ui.phoneVerification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhoneViewModel(val receivedOTB: String) : ViewModel() {


    private var _OTBdigit1 = MutableLiveData<String?>()
    val OTBdigit1: LiveData<String?> get() = _OTBdigit1


    private var _OTBdigit2 = MutableLiveData<String?>()
    val OTBdigit2: LiveData<String?> get() = _OTBdigit2


    private var _OTBdigit3 = MutableLiveData<String?>()
    val OTBdigit3: LiveData<String?> get() = _OTBdigit3


    private var _OTBdigit4 = MutableLiveData<String?>()
    val OTBdigit4: LiveData<String?> get() = _OTBdigit4


    private var _digit1FocusState = MutableLiveData<Boolean>(true)
    val digit1FocusState: LiveData<Boolean> get() = _digit1FocusState

    private var _digit2FocusState = MutableLiveData<Boolean>(false)
    val digit2FocusState: LiveData<Boolean> get() = _digit2FocusState

    private var _digit3FocusState = MutableLiveData<Boolean>(false)
    val digit3FocusState: LiveData<Boolean> get() = _digit3FocusState

    private var _digit4FocusState = MutableLiveData<Boolean>(false)
    val digit4FocusState: LiveData<Boolean> get() = _digit4FocusState


    private var _isOTBMatched = MutableLiveData<Boolean>()
    val isOTBMatched: LiveData<Boolean> get() = _isOTBMatched


    private var _selectedDigit: Int = 0


    init {
    }

    fun MoveToNextDigit() {
        if (_selectedDigit < 3) {
            onDigitRequestFocus(_selectedDigit + 1)

        }
    }

    fun MoveToPreviousDigit() {
        if (_selectedDigit > 0) {
            onDigitRequestFocus(_selectedDigit - 1)

        }
    }

    fun onKeyboardButtonPressed(digit: Int) {
        setDigitValue(digit.toString())
        MoveToNextDigit()

    }

    private fun setDigitValue(digitValue: String) {
        when (_selectedDigit) {
            0 -> _OTBdigit1.value = digitValue
            1 -> _OTBdigit2.value = digitValue
            2 -> _OTBdigit3.value = digitValue
            3 -> _OTBdigit4.value = digitValue
            else -> return
        }
    }

    private fun deleteDigitValue() {
        when (_selectedDigit) {
            0 -> _OTBdigit1.value = null
            1 -> _OTBdigit2.value = null
            2 -> _OTBdigit3.value = null
            3 -> _OTBdigit4.value = null
        }
    }


    fun onDigitRequestFocus(digitIndex: Int) {
        disableFocus()
        _selectedDigit = digitIndex
        enableFocus()

    }

    private fun enableFocus() {
        when (_selectedDigit) {
            0 -> _digit1FocusState.value = true
            1 -> _digit2FocusState.value = true
            2 -> _digit3FocusState.value = true
            3 -> _digit4FocusState.value = true

        }
    }

    private fun disableFocus() {
        when (_selectedDigit) {
            0 -> _digit1FocusState.value = false
            1 -> _digit2FocusState.value = false
            2 -> _digit3FocusState.value = false
            3 -> _digit4FocusState.value = false

        }
    }

    fun onDelete() {
        deleteDigitValue()
        MoveToPreviousDigit()

    }

    fun onConfirm() {

            val OTB = _OTBdigit1.value + _OTBdigit2.value + _OTBdigit3.value + _OTBdigit4.value

            _isOTBMatched.value = OTB == receivedOTB


        }


}