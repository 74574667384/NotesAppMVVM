package com.example.notesmvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.notesmvvm.database.room.AppRoomDatabase
import com.example.notesmvvm.database.room.repository.RoomRepository
import com.example.notesmvvm.model.Note
import com.example.notesmvvm.utils.REPOSTIRY
import com.example.notesmvvm.utils.TYPE_FIREBASE
import com.example.notesmvvm.utils.TYPE_ROOM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        Log.d("checkData", "MainViewModel initDatabase with type: $type")
        when(type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSTIRY = RoomRepository(dao)
                onSuccess()
            }
        }
    }

    fun addNote(note: Note, onSuccess: () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        REPOSTIRY.create(note = note) {
            viewModelScope.launch(Dispatchers.Main) {
                onSuccess()
            }
        }
    }


    fun readAllNotes() = REPOSTIRY.readAll
}


class MainViewModelFactory(private val Application: Application): ViewModelProvider.Factory {
   override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = Application) as T
        }
        throw IllegalArgumentException ("Unknown Viewmodel clas")
    }
}