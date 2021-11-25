/**
2 * Copyright (C), 2015-2021, XXX Limited
3 * FileName: TodoRepository
4 * Author: malek
5 * Date: 11/24/21 7:27 AM
6 * Description:  CRUD operation on Room Database
7 * History:
8 * <author> <time> <version> <desc>
9 * Author's name Modification time Version number Description
10
 */
package com.omran.notatodolist.repo

import androidx.lifecycle.LiveData
import com.omran.notatodolist.room.LocalDataSource
import com.omran.notatodolist.room.entity.Todo

class TodoRepository(private val localDataSource: LocalDataSource) : ITodoRepository {

    companion object {
        @Volatile
        private var instance: TodoRepository? = null

        fun getInstance(localData: LocalDataSource): TodoRepository =
            instance ?: synchronized(this) {
                instance ?: TodoRepository(localData)
            }
    }


    override fun getAllTodos(): LiveData<List<Todo>> {
        return localDataSource.getAllTodos()
    }

    override fun getAllCompleted(): LiveData<List<Todo>> {
        return localDataSource.getAllCompleted()
    }

    override suspend fun insert(todo: Todo) {
        return localDataSource.insert(todo)
    }

    override suspend fun update(todo: Todo) {
        return localDataSource.update(todo)
    }

    override suspend fun deleteSelectedTodos() {
        return localDataSource.deleteSelectedTodos()
    }

    override suspend fun clearTodos() {
        return localDataSource.clearTodos()
    }
}