/**
2 * Copyright (C), 2015-2021, XXX Limited
3 * FileName: Todo
4 * Author: malek
5 * Date: 11/24/21 6:28 AM
6 * Description: An Entity class: This represents a database table in room, they are created by annotating the class with @Entity
7 * History:
8 * <author> <time> <version> <desc>
9 * Author's name Modification time Version number Description
10
 */
package com.omran.notatodolist.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "todo_title")
    var title: String,
    @ColumnInfo(name = "todo_description")
    var description: String,
    @ColumnInfo(name = "todo_checked")
    var checked: Boolean

): Parcelable