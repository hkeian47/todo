package com.example.todo
data class TodoItem(
    val id: Int,
    val name: String,
    var isChecked: Boolean = false
)


