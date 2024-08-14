package com.example.todo

import ApiService
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantExecutorExtension::class)
class TodoListViewModelTest {

    private lateinit var viewModel: TodoListViewModel
    private val mockApiService: ApiService = mockk()

    @BeforeEach
    fun setup() {
        viewModel = spyk(TodoListViewModel())
        every { RetrofitInstance.api } returns mockApiService
    }

    @Test
    fun `fetchTodos should succeed when API call is successful`() = runTest {
        // Mocking the API response
        val todos = listOf(TodoItem(1, "Test Todo", false))
        coEvery { mockApiService.getTodos("testUserId", "testApiKey") } returns todos

        // Execute the function
        viewModel.fetchTodos("testUserId", "testApiKey")

        // Verify the result
        assert(viewModel.todoItems.value == todos)
        coVerify { mockApiService.getTodos("testUserId", "testApiKey") }
    }

    @Test
    fun `fetchTodos should handle error when API call fails`() = runTest {
        // Mocking an API failure
        coEvery { mockApiService.getTodos("testUserId", "testApiKey") } throws Exception("API Error")

        // Execute the function
        viewModel.fetchTodos("testUserId", "testApiKey")

        // Verify that the list remains empty
        assert(viewModel.todoItems.value.isEmpty())
        coVerify { mockApiService.getTodos("testUserId", "testApiKey") }
    }
}
