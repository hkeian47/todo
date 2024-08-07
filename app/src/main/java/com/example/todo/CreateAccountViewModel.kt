import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.RegisterRequest
import com.example.todo.RetrofitInstance
import com.example.todo.SharedPreferencesManager
import kotlinx.coroutines.launch

class CreateAccountViewModel : ViewModel() {
    fun createAccount(email: String, password: String, context: Context, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.register(RegisterRequest(email, password))
                SharedPreferencesManager.saveUserId(context, response.id)
                SharedPreferencesManager.saveToken(context, response.token)
                onSuccess()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}


