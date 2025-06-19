package org.aifooddelivery.app.presentation.login.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OtpVerificationViewModel : ViewModel() {
    private val _otp = MutableStateFlow("")
    val otp = _otp.asStateFlow()
    fun onOtpChange(value: String) {
        _otp.value = value
    }

    fun isOtpValid(): Boolean = _otp.value.length == 4
}