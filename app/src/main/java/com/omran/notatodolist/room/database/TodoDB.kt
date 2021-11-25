/**
2 * Copyright (C), 2015-2021, XXX Limited
3 * FileName: TodoDB
4 * Author: malek
5 * Date: 11/24/21 6:40 AM
6 * Description: A Database class: This is an abstract class annotated with @Database, this class creates the database.
7 * History:
8 * <author> <time> <version> <desc>
9 * Author's name Modification time Version number Description
10
 */
package com.omran.notatodolist.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.omran.notatodolist.room.dao.TodoDaO
import com.omran.notatodolist.room.entity.Todo

// entities(this is an array of entity used in the database)
//the version, an optional parameter is the exportSchema which can be set to true or false.
//You can also check out type converters
@Database(entities = [Todo::class] , version = 1 , exportSchema = false)
abstract class TodoDB() : RoomDatabase() {

    /**
     * This is an abstract method that returns a dao for the Db
     * */
    abstract  fun  todoDao()  :  TodoDaO

    //Singleton
    //When creating your database it is important to use the singleton design pattern,
    // this ensures that only one instance is created in the whole application
    companion object{
        //get instance from database class
        @Volatile
        private var INSTANCE: TodoDB? = null

        fun getInstance(context: Context): TodoDB{

            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext ,
                        TodoDB::class.java ,
                        "todo_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE =instance
                }
                return instance
            }

        }
    }
}