package com.wisnu.kurniawan.composetodolist.foundation.extension

import com.wisnu.kurniawan.composetodolist.DateFactory
import com.wisnu.kurniawan.composetodolist.foundation.datasource.local.model.ToDoListDb
import com.wisnu.kurniawan.composetodolist.foundation.datasource.local.model.ToDoListWithTasks
import com.wisnu.kurniawan.composetodolist.model.ToDoColor
import com.wisnu.kurniawan.composetodolist.model.ToDoList
import org.junit.Assert
import org.junit.Test

class ToDoListWithTasksExtTest {

    @Test
    fun toDoListWithTasksToLists() {
        Assert.assertEquals(
            listOf(
                ToDoList(
                    id = "id",
                    name = "name",
                    color = ToDoColor.PURPLE,
                    tasks = listOf(),
                    createdAt = DateFactory.constantDate,
                    updatedAt = DateFactory.constantDate,
                )
            ),
            listOf(
                ToDoListWithTasks(
                    list = ToDoListDb(
                        id = "id",
                        name = "name",
                        color = ToDoColor.PURPLE,
                        groupId = "groupId",
                        createdAt = DateFactory.constantDate,
                        updatedAt = DateFactory.constantDate,
                    ),
                    taskWithSteps = listOf()
                )
            ).toDoListWithTasksToList()
        )
    }

    @Test
    fun toDoListWithTasksToList() {
        Assert.assertEquals(
            ToDoList(
                id = "id",
                name = "name",
                color = ToDoColor.PURPLE,
                tasks = listOf(),
                createdAt = DateFactory.constantDate,
                updatedAt = DateFactory.constantDate,

                ),
            ToDoListWithTasks(
                list = ToDoListDb(
                    id = "id",
                    name = "name",
                    color = ToDoColor.PURPLE,
                    groupId = "groupId",
                    createdAt = DateFactory.constantDate,
                    updatedAt = DateFactory.constantDate,
                ),
                taskWithSteps = listOf()
            ).toDoListWithTasksToList()
        )
    }
}
