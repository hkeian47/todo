package com.example.todo
import AuthScreen
import CreateAccountViewModel
import MainContent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    private val createAccountViewModel: CreateAccountViewModel by viewModels()
    private val logInViewModel: LogInViewModel by viewModels()
    private val todoListViewModel: TodoListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            var isLoggedIn by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                val userId = SharedPreferencesManager.getUserId(context)
                val token = SharedPreferencesManager.getToken(context)
                if (userId != null && token != null) {
                    isLoggedIn = true
                    todoListViewModel.fetchTodos(userId, token)
                }
            }

            if (isLoggedIn) {
                MainContent(viewModel = todoListViewModel, context = context)
            } else AuthScreen(
                onLoginSuccess = {
                    isLoggedIn = true
                    val userId = SharedPreferencesManager.getUserId(context)!!
                    val token = SharedPreferencesManager.getToken(context)!!
                    todoListViewModel.fetchTodos(userId, token)
                },
                createAccountViewModel = createAccountViewModel,
                logInViewModel = logInViewModel
            )
        }
    }
}
