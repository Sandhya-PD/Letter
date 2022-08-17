package com.example.lettervaultpro

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lettervaultpro.data.Letter
import com.example.lettervaultpro.data.LetterDao
import com.example.lettervaultpro.repository.LetterRepository
import kotlinx.coroutines.launch



class OpenMessageViewModel(letterDao: LetterDao, application: Application) : ViewModel() {

    private val letterRepository = LetterRepository(letterDao, application)

    var status: LiveData<LetterStatus> = letterRepository.status

    var subject: LiveData<String> = letterRepository.letterSubject
    var details: LiveData<String> = letterRepository.letterDetails


    var singleLetter: LiveData<Letter> = letterRepository.singleLetter


    @SuppressLint("SimpleDateFormat")
    fun onFetchLetter(id: Int) {
        viewModelScope.launch {
            letterRepository.getLetterById(id)
        }
    }

    fun deleteLetter(id: Int) {
        viewModelScope.launch {
            letterRepository.deleteLetter(id)
        }
    }

}


class OpenLetterViewModelFactory(
    private val letterDao: LetterDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OpenMessageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OpenMessageViewModel(letterDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}