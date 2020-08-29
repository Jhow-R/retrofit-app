package br.com.fiap.roomdatabase.service

import br.com.fiap.roomdatabase.task.Task
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TaskService {

    @GET("tasks")
    suspend fun getTasks() : List<Task>

    @POST("new-task")
    suspend fun newTask(@Body task: Task)
}

private var service: TaskService? = null

fun getService() : TaskService? {

    if(service == null) {
        // 10.0.2.2 - localhost padrão do mobile
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(TaskService::class.java)
    }

    return service
}


