/**
2 * Copyright (C), 2015-2021, XXX Limited
3 * FileName: TodoViewModel
4 * Author: malek
5 * Date: 11/24/21 7:40 AM
6 * Description: ViewModel Class To connect with view And show data on screen
7 * History:
8 * <author> <time> <version> <desc>
9 * Author's name Modification time Version number Description
10
 */
package com.omran.notatodolist.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.omran.notatodolist.di.Injection
import com.omran.notatodolist.repo.TodoRepository
import com.omran.notatodolist.room.entity.Todo
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    fun getAllTodos(): LiveData<List<Todo>> = repository.getAllTodos()

    fun getAllCompleted(): LiveData<List<Todo>> = repository.getAllCompleted()

    fun addTodo(title: String, desc: String) {
        viewModelScope.launch {
            repository.insert(Todo(0, title, desc, false))
        }
    }

    fun updateTodo(id: Int, title: String, desc: String, checked: Boolean) {
        viewModelScope.launch {
            repository.update(Todo(id, title, desc, checked))
        }
    }

    fun deleteSelected() {
        viewModelScope.launch {
            repository.deleteSelectedTodos()
        }
    }

    fun clearTodos() {
        viewModelScope.launch {
            repository.clearTodos()
        }
    }


}

@Suppress("UNCHECKED_CAST")
class TodoViewModelFactory(private val mTodoRepository: TodoRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: TodoViewModelFactory? = null

        fun getInstance(context: Context): TodoViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: TodoViewModelFactory(Injection.provideRepository(context))
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodoViewModel(mTodoRepository) as T
    }
}