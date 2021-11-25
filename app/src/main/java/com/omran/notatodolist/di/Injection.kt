package com.omran.notatodolist.di

import android.content.Context
import com.omran.notatodolist.repo.TodoRepository
import com.omran.notatodolist.room.LocalDataSource
import com.omran.notatodolist.room.database.TodoDB

object Injection {

    fun provideRepository(context: Context): TodoRepository {
        val database = TodoDB.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database.todoDao())

        return TodoRepository.getInstance(localDataSource)
    }
}