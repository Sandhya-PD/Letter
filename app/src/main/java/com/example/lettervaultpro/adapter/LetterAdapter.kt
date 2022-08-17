package com.example.lettervaultpro.adapter


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lettervaultpro.R
import com.example.lettervaultpro.data.Letter
import com.example.lettervaultpro.databinding.ListViewBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class LetterAdapter(val clickListener: (letter: Letter) -> Unit) :
    PagingDataAdapter<Letter, LetterAdapter.LetterViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LetterViewHolder {
        return LetterViewHolder(
            ListViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {

        val data = getItem(position)

        if (data != null) {
            holder.bind( data)
        }
    }

    companion object {
        object DiffCallBack : DiffUtil.ItemCallback<Letter>() {
            override fun areItemsTheSame(oldItem: Letter, newItem: Letter): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Letter, newItem: Letter): Boolean {
                return oldItem == newItem
            }
        }

    }

   inner class LetterViewHolder(val binding: ListViewBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(letter: Letter) {

            val dateTimeCurrent =
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd yyyy,h:mma"))

            binding.letter = letter
            binding.letterViewHolder = this

            if (letter.selected_date <= dateTimeCurrent && !letter.status) {
                binding.unlockMsg.text = "Ready to Open"
                binding.keyImg.setImageResource(R.drawable.ic_baseline_lock)

            } else if (letter.status) {
                binding.keyImg.visibility = View.INVISIBLE
                binding.msgDetail.text = letter.detail
                binding.unlockMsg.text = " Opened:${letter.selected_date}"
            } else if (letter.selected_date > dateTimeCurrent && !letter.status) {
                binding.keyImg.setImageResource(R.drawable.ic_baseline_lock)
                binding.unlockMsg.text = "Letter opening at:${letter.selected_date}"

            }

//                binding.clickListener=clickListener
            binding.executePendingBindings()

        }

       fun onClick(letter: Letter){
           clickListener(letter)
       }
    }


//    override fun getFilter(): android.widget.Filter {
//        TODO("Not yet implemented")
//    }


}

//class LetterListener(val clickListener: (letter: Letter) -> Unit) {
//    fun onClick(letter: Letter) = clickListener(letter)
//}
