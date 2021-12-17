package com.arjinmc.jetpackplayground.material.textinputlayout

import android.os.Bundle
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActTextInputLayoutBinding

/**
 * Created by Eminem Lo on 11/19/21
 * email: arjinmc@hotmail.com
 * reference to :https://medium.com/huawei-developers/how-to-easily-validate-user-inputs-on-android-80c8e5744de7
 */
class TextInputLayoutActivity : BasicActivity() {

    private val binding by viewBinding(ActTextInputLayoutBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()

    }

    override fun initView() {
    }

    override fun initListener() {

        binding.buttonLogin.setOnClickListener {

            val username = binding.inputEditTextUsername.text.toString()
            val email = binding.inputEditTextEmail.text.toString()
            val password = binding.inputEditTextPassword.text.toString()

            val usernameEmptyValidation = EmptyValidator(username).validate()
            if (!usernameEmptyValidation.isSuccess)
                binding.inputLayoutUsername.error =
                    usernameEmptyValidation.message else binding.inputLayoutUsername.helperText =
                "Username ok"

            val emailValidations = BaseValidator.validate(
                EmptyValidator(email), EmailValidator(email)
            )
            binding.inputLayoutEmail.error =
                if (!emailValidations.isSuccess)
                    emailValidations.message else null

            val passwordValidations = BaseValidator.validate(
                EmptyValidator(password), PasswordValidator(password)
            )
            binding.inputLayoutPassword.error =
                if (!passwordValidations.isSuccess) passwordValidations.message else null

        }
    }

    override fun initData() {
        setTitle(R.string.material_text_input_layout)
    }
}