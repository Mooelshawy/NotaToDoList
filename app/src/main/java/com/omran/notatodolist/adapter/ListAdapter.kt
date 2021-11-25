/**
2 * Copyright (C), 2015-2021, XXX Limited
3 * FileName: ListAdapter
4 * Author: malek
5 * Date: 11/24/21 7:50 AM
6 * Description: recycler view adapter
7 * History:
8 * <author> <time> <version> <desc>
9 * Author's name Modification time Version number Description
10
 */
package com.omran.notatodolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.omran.notatodolist.R
import com.omran.notatodolist.databinding.ItemTodoBinding
import com.omran.notatodolist.room.entity.Todo
import com.omran.notatodolist.viewmodel.TodoViewModel

class ListAdapter (private val viewModel: TodoViewModel) :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var dataList = emptyList<Todo>()

    class MyViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo, viewModel: TodoViewModel) {
            binding.todo = todo
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: ItemTodoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_todo,
            parent,
            false
        )

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem, viewModel)
    }

    fun setData(toDoData: List<Todo>) {
        val toDoDiffUtil = TodoDiffUtil(dataList, toDoData)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.dataList = toDoData
        toDoDiffResult.dispatchUpdatesTo(this)
    }

}