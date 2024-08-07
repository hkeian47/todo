package com.example.todo

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import android.content.Context

@Composable
fun TodoList(items: List<TodoItem>, viewModel: TodoListViewModel, context: Context) {
    LazyColumn {
        items(items) { item ->
            TodoRow(todoItem = item, viewModel = viewModel, context = context)
        }
    }
}
