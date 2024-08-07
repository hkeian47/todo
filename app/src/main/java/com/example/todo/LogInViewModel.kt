package com.example.todo
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch



class LogInViewModel : ViewModel() {
    fun logIn(email: String, password: String, context: Context, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.login(LoginRequest(email, password))
                SharedPreferencesManager.saveUserId(context, response.id)
                SharedPreferencesManager.saveToken(context, response.token)
                onSuccess()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
