package com.omran.notatodolist.repo

import androidx.lifecycle.LiveData
import com.omran.notatodolist.room.entity.Todo

interface ITodoRepository {
    fun getAllTodos(): LiveData<List<Todo>>

    fun getAllCompleted(): LiveData<List<Todo>>

    suspend fun insert(todo: Todo)

    suspend fun update(todo: Todo)

    suspend fun deleteSelectedTodos()

    suspend fun clearTodos()
}