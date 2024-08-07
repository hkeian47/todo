package com.example.todo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoListViewModel : ViewModel() {
    var todoItems = mutableStateOf(listOf<TodoItem>())

    fun fetchTodos(userId: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val todos = RetrofitInstance.api.getTodos(userId, apiKey)
                todoItems.value = todos
            } catch (e: Exception) {

            }
        }
    }

    fun addTodo(userId: String, apiKey: String, todo: TodoItem) {
        viewModelScope.launch {
            try {
                val newTodo = RetrofitInstance.api.createTodo(userId, todo, apiKey)
                fetchTodos(userId, apiKey)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun updateTodo(userId: String, apiKey: String, todo: TodoItem) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.updateTodo(userId, todo.id.toString(), todo, apiKey)
                fetchTodos(userId, apiKey)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
