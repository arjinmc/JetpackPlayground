package com.arjinmc.jetpackplayground.material.textinputlayout

import android.text.TextUtils

/**
 * Created by Eminem Lo on 11/19/21
 * email: arjinmc@hotmail.com
 */
class EmailValidator(val email: String) : BaseValidator() {
    override fun validate(): ValidateResult {
        val isValid =
            !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        return ValidateResult(
            isValid,
            if (isValid) "Success" else "Email format error"
        )
    }
}