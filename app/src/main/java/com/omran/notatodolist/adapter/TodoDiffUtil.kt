/**
2 * Copyright (C), 2015-2021, XXX Limited
3 * FileName: TodoDiffUtil
4 * Author: malek
5 * Date: 11/24/21 7:47 AM
6 * Description: class to compaer item when user make update old item with new item
7 * History:
8 * <author> <time> <version> <desc>
9 * Author's name Modification time Version number Description
10
 */
package com.omran.notatodolist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.omran.notatodolist.room.entity.Todo

class TodoDiffUtil (
    private val oldList: List<Todo>,
    private val newList: List<Todo>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].description == newList[newItemPosition].description
                && oldList[oldItemPosition].checked == newList[newItemPosition].checked
    }
}