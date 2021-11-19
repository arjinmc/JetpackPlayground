package com.arjinmc.jetpackplayground.material.textinputlayout

/**
 * Created by Eminem Lo on 11/19/21
 * email: arjinmc@hotmail.com
 */
class PasswordValidator(val password: String) : BaseValidator() {
    private val minPasswordLength = 6
    private val maxPasswordLength = 12

    override fun validate(): ValidateResult {
        if (password.length < minPasswordLength)
            return ValidateResult(false, "Password length must above $minPasswordLength")
        if (password.length > maxPasswordLength)
            return ValidateResult(false, "Password length must below $maxPasswordLength")
        return ValidateResult(true, "Success")
    }
}