package com.example.android.registrationapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.android.registrationapplication.constance.Constance
import com.example.android.registrationapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindingClass: ActivityMainBinding

    private var login: String = "empty"
    private var password: String = "empty"
    private var name: String = "empty"
    private var surname: String = "empty"
    private var patronymic: String = "empty"
    private var avatarImageId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constance.REQUEST_CODE_SIGN_IN){

            val loginNew = data?.getStringExtra(Constance.LOGIN)
            val passwordNew = data?.getStringExtra(Constance.PASSWORD)
            if (login == loginNew && password == passwordNew){

                bindingClass.imageViewAvatar.visibility = View.VISIBLE
                bindingClass.imageViewAvatar.setImageResource(avatarImageId)

                val textInfo = "$name $surname $patronymic"
                bindingClass.textViewInfo.text = textInfo

                bindingClass.buttonHide.visibility = View.GONE
                bindingClass.buttonExit.text = "Exit"
            }else{

                bindingClass.imageViewAvatar.visibility = View.VISIBLE
                bindingClass.imageViewAvatar.setImageResource(R.drawable.invalid)
                bindingClass.textViewInfo.text = "This account does not exist. Please enter correct data."
            }

        }else if(requestCode == Constance.REQUEST_CODE_SIGN_UP){

            login = data?.getStringExtra(Constance.LOGIN)!!
            password = data.getStringExtra(Constance.PASSWORD)!!
            name = data.getStringExtra(Constance.NAME)!!
            surname = data.getStringExtra(Constance.SURNAME)!!
            patronymic = data.getStringExtra(Constance.PATRONYMIC)!!
            avatarImageId = data.getIntExtra(Constance.AVATAR_ID, 0)
            bindingClass.imageViewAvatar.visibility = View.VISIBLE

            bindingClass.imageViewAvatar.setImageResource(avatarImageId)

            val textInfo = "$name $surname $patronymic"
            bindingClass.textViewInfo.text = textInfo

            bindingClass.buttonHide.visibility = View.GONE
            bindingClass.buttonExit.text = "Exit"

        }

    }

    fun onCLickSIgnIn(view: View){

        if(bindingClass.imageViewAvatar.isVisible && bindingClass.textViewInfo.text.toString() !=
            "This account does not exist. Please enter correct data."){

            bindingClass.imageViewAvatar.visibility = View.INVISIBLE
            bindingClass.textViewInfo.text = ""
            bindingClass.buttonHide.visibility = View.VISIBLE
            bindingClass.buttonExit.text = getString(R.string.sign_in)

        }else {

            val intent = Intent(this, SignInUpActivity::class.java)
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_IN)
        }

    }

    fun onCLickSIgnUp(view: View){

        val intent = Intent(this, SignInUpActivity::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
        startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_UP)

    }
}