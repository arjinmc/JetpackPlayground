package com.arjinmc.jetpackplayground.material.textinputlayout

/**
 * Created by Eminem Lo on 11/19/21
 * email: arjinmc@hotmail.com
 */
abstract class BaseValidator : IValidator {

    companion object {
        fun validate(vararg validators: IValidator): ValidateResult {
            validators.forEach {
                val result = it.validate()
                if (!result.isSuccess)
                    return result
            }
            return ValidateResult(true, "Success")
        }
    }
}