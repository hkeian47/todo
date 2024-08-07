import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*

import com.example.todo.RetrofitInstance
import com.example.todo.SharedPreferencesManager
import com.example.todo.TodoInputBottomSheet
import com.example.todo.TodoItem
import com.example.todo.TodoList
import com.example.todo.TodoListViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import android.content.Context

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(viewModel: TodoListViewModel, context: Context) {
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Todo List App") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showBottomSheet = true }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            TodoList(viewModel.todoItems.value, viewModel, context)
            if (showBottomSheet) {
                TodoInputBottomSheet(
                    onItemComplete = { itemName ->
                        val userId = SharedPreferencesManager.getUserId(context)!!
                        val apiKey = RetrofitInstance.getApiKey()
                        viewModel.addTodo(userId, apiKey, TodoItem(id = 0, name = itemName))
                        showBottomSheet = false
                    },
                    onDismiss = { showBottomSheet = false }
                )
            }
        }
    }
}

