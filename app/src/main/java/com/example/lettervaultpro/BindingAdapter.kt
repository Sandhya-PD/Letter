package com.example.lettervaultpro

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("lockStatus")
fun bindStatus(statusImageView: ImageView, status:LetterStatus?){
    when(status){
        LetterStatus.LOCK->{
            statusImageView.visibility= View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_baseline_lock)
        }
        LetterStatus.UNLOCK->{
            statusImageView.visibility= View.INVISIBLE
            statusImageView.setImageResource(R.drawable.ic_baseline_lock_open)
        }

        else -> {}
    }
}


