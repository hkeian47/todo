package com.example.todo
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.content.Context



@Composable
fun TodoRow(todoItem: TodoItem, viewModel: TodoListViewModel, context: Context) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Checkbox(
            checked = todoItem.isChecked,
            onCheckedChange = { isChecked ->
                val userId = SharedPreferencesManager.getUserId(context)!!
                val apiKey = RetrofitInstance.getApiKey()
                viewModel.updateTodo(userId, apiKey, todoItem.copy(isChecked = isChecked))
            }
        )
        Text(
            text = todoItem.name,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
