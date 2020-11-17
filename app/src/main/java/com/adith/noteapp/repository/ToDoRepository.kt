package com.adith.noteapp.repository

import androidx.lifecycle.LiveData
import com.adith.noteapp.data.TodoDao
import com.adith.noteapp.model.TodoData

class TodoRespository(private val todoDao: TodoDao) {
    val getAllData: LiveData<List<TodoData>> = todoDao.getAllData()
    val sortByHighPriority: LiveData<List<TodoData>> = todoDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<TodoData>> = todoDao.sortByLowPriority()

    suspend fun insertData(todoData: TodoData){
        todoDao.insertData(todoData)
    }

    suspend fun updateData(todoData: TodoData){
        todoDao.updatedata(todoData)
    }

    suspend fun deleteData(todoData: TodoData){
        todoDao.deleteData( todoData)
    }

    suspend fun deleteAll(){
        todoDao.deleteAll()
    }

    fun searchDatabase (searchQuery: String): LiveData<List<TodoData>> {
        return  todoDao.searchDatabase(searchQuery)
    }
}