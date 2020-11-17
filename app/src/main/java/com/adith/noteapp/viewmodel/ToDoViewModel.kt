package com.adith.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.adith.noteapp.data.TodoDao
import com.adith.noteapp.data.TodoDatabase
import com.adith.noteapp.model.TodoData
import com.adith.noteapp.repository.TodoRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application): AndroidViewModel(application) {
    private val todoDao: TodoDao = TodoDatabase.getDatabase(application).todoDao()
    private val respository: TodoRespository

    val getAllData: LiveData<List<TodoData>>
    val sortByHighPriority: LiveData<List<TodoData>>
    val sortByLowPriority: LiveData<List<TodoData>>

    init {
        respository = TodoRespository(todoDao)
        getAllData = respository.getAllData
        sortByHighPriority = respository.sortByHighPriority
        sortByLowPriority = respository.sortByLowPriority
    }

    fun insertData(todoData: TodoData){
        viewModelScope.launch(Dispatchers.IO){
            respository.insertData(todoData)
        }
    }

    fun updateData(todoData: TodoData){
        viewModelScope.launch(Dispatchers.IO){
            respository.updateData(todoData)
        }
    }

    fun deleteData(todoData: TodoData){
        viewModelScope.launch(Dispatchers.IO){
            respository.deleteData(todoData)
        }
    }

    fun deleteAllData(){
        viewModelScope.launch(Dispatchers.IO){
            respository. deleteAll()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<TodoData>> {
        return respository.searchDatabase(searchQuery )
    }

}