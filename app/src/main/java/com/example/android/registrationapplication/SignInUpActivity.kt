package com.example.android.registrationapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.android.registrationapplication.constance.Constance
import com.example.android.registrationapplication.databinding.ActivitySignInUpBinding

class SignInUpActivity : AppCompatActivity() {

    lateinit var bindingClass: ActivitySignInUpBinding

    private var signState = "empty"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingClass = ActivitySignInUpBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        signState = intent.getStringExtra(Constance.SIGN_STATE)!!
        if (signState == Constance.SIGN_IN_STATE){

            bindingClass.editName.visibility = View.GONE
            bindingClass.editSurname.visibility = View.GONE
            bindingClass.editPatronymic.visibility = View.GONE
            bindingClass.buttonAvatar.visibility = View.INVISIBLE
        }
    }

    fun onCLickDone(view:View){

        if (signState == Constance.SIGN_UP_STATE){

            val intent = Intent()
            intent.putExtra(Constance.LOGIN, bindingClass.editLogin.text.toString())
            intent.putExtra(Constance.PASSWORD, bindingClass.editPassword.text.toString())
            intent.putExtra(Constance.NAME, bindingClass.editName.text.toString())
            intent.putExtra(Constance.SURNAME, bindingClass.editSurname.text.toString())
            intent.putExtra(Constance.PATRONYMIC, bindingClass.editPatronymic.text.toString())
            if (bindingClass.imageViewAvatar.isVisible)intent.putExtra(Constance.AVATAR_ID, R.drawable.person_face)
            setResult(RESULT_OK, intent)
            finish()


        }else if (signState == Constance.SIGN_IN_STATE){

            intent.putExtra(Constance.LOGIN, bindingClass.editLogin.text.toString())
            intent.putExtra(Constance.PASSWORD, bindingClass.editPassword.text.toString())
            setResult(RESULT_OK, intent)
            finish()

        }

    }

    fun onClickAvatar(view: View){

        bindingClass.imageViewAvatar.visibility = View.VISIBLE
    }
}