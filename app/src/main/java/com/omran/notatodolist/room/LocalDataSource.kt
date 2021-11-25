/**
2 * Copyright (C), 2015-2021, XXX Limited
3 * FileName: LocalDataSource
4 * Author: malek
5 * Date: 11/24/21 6:53 AM
6 * Description: using db
7 * History:
8 * <author> <time> <version> <desc>
9 * Author's name Modification time Version number Description
10
 */
package com.omran.notatodolist.room

import androidx.lifecycle.LiveData
import com.omran.notatodolist.room.dao.TodoDaO
import com.omran.notatodolist.room.entity.Todo


// higher level to commuincation with database layer
class LocalDataSource private constructor(private val todoDAO: TodoDaO){

    companion object{
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(todoDAO: TodoDaO): LocalDataSource =
            INSTANCE ?: LocalDataSource(todoDAO)
    }

    fun getAllTodos(): LiveData<List<Todo>> = todoDAO.loadTodos()

    fun getAllCompleted(): LiveData<List<Todo>> = todoDAO.loadCompleted()

    suspend fun insert(todo: Todo) = todoDAO.insertTodo(todo)

    suspend fun update(todo: Todo) = todoDAO.updateTodo(todo)

    suspend fun deleteSelectedTodos() = todoDAO.deleteSelectedTodos()

    suspend fun clearTodos() = todoDAO.clearAll()

}