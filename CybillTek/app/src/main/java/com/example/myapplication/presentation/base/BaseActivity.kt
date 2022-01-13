package com.example.myapplication.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.myapplication.R

abstract class BaseActivity<B: ViewDataBinding>(private val layoutId: Int):
    AppCompatActivity()
{
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.anim_to_left_enter, R.anim.anim_to_left_exit)
        binding = DataBindingUtil.setContentView(this, layoutId)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_to_right_enter, R.anim.anim_to_right_exit)
    }
}