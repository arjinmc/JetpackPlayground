package com.arjinmc.jetpackplayground.material.textinputlayout

/**
 * Created by Eminem Lo on 11/19/21
 * email: arjinmc@hotmail.com
 */
class EmptyValidator(val input: String) : BaseValidator() {
    override fun validate(): ValidateResult {
        val isValid = input.isNotEmpty()
        return ValidateResult(
            isValid,
            if (isValid) "Success" else "It cannot be empty"
        )
    }
}