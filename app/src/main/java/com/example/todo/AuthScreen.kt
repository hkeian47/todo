import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo.LogInViewModel

@Composable
fun AuthScreen(
    onLoginSuccess: () -> Unit,
    createAccountViewModel: CreateAccountViewModel = viewModel(),
    logInViewModel: LogInViewModel = viewModel()
) {
    var showLogin by remember { mutableStateOf(true) }

    if (showLogin) {
        LogInScreen(
            onLoggedIn = onLoginSuccess,
            onCreateAccountClick = { showLogin = false },
            viewModel = logInViewModel
        )
    } else {
        CreateAccountScreen(
            onAccountCreated = onLoginSuccess,
            onLogInClick = { showLogin = true },
            viewModel = createAccountViewModel
        )
    }
}
