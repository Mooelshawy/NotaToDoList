package com.omran.notatodolist.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.omran.notatodolist.room.entity.Todo


@Dao
interface TodoDaO {


    /**
     * SELECT -> This retrieve rows from a table in a database
     * FROM -> You specify the table to retrieve the rows from
     * ORDER BY -> This is just a sort algorithm
     * ASC -> Ascending order
     * WHERE -> This is a condition used to query data
     * */
    @Query("SELECT * FROM todo")
    fun loadTodos(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo WHERE todo_checked = 1")
    fun loadCompleted(): LiveData<List<Todo>>


    /**
     * @param todo is what we want to save in our database
     * so many conflict can occur when a data is to be saved, the strategy is used to handle such conflicts
     * Abort -> this aborts the transaction
     * Ignore -> this ignores and continues the transaction
     * Replace -> this replace the data
     * others includes fail, and roolback
     * */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("DELETE FROM todo WHERE todo_checked = 1")
    suspend fun deleteSelectedTodos()

    @Query("DELETE  FROM todo")
    fun clearAll()
}