package com.example.todo
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TodoInputBottomSheet(onItemComplete: (String) -> Unit, onDismiss: () -> Unit) {
    var text by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Add Todo Item") },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = { newText -> text = newText },
                label = { Text("Todo") }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onItemComplete(text)
                    text = ""
                }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTodoInputBottomSheet() {
    TodoInputBottomSheet(
        onItemComplete = {},
        onDismiss = {}
    )
}
