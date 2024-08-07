
import com.example.todo.LoginRequest
import com.example.todo.RegisterRequest
import com.example.todo.TodoItem
import com.example.todo.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("/api/users/register")
    suspend fun register(@Body request: RegisterRequest): UserResponse

    @POST("/api/users/login")
    suspend fun login(@Body request: LoginRequest): UserResponse

    @GET("/api/users/{user_id}/todos")
    suspend fun getTodos(@Path("user_id") userId: String, @Query("apikey") apiKey: String): List<TodoItem>

    @POST("/api/users/{user_id}/todos")
    suspend fun createTodo(
        @Path("user_id") userId: String,
        @Body todo: TodoItem,
        @Query("apikey") apiKey: String
    ): TodoItem

    @PUT("/api/users/{user_id}/todos/{id}")
    suspend fun updateTodo(
        @Path("user_id") userId: String,
        @Path("id") todoId: String,
        @Body todo: TodoItem,
        @Query("apikey") apiKey: String
    ): TodoItem
}
