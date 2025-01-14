package com.wisnu.kurniawan.composetodolist.features.dashboard.data

import com.wisnu.kurniawan.composetodolist.model.ToDoTaskDiff
import com.wisnu.kurniawan.composetodolist.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface IDashboardEnvironment {
    val dispatcher: CoroutineDispatcher
    fun getUser(): Flow<User>
    fun listenToDoTaskDiff(): Flow<ToDoTaskDiff>
}
