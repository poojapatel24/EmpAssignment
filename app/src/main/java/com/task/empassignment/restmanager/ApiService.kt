package com.task.empassignment.restmanager

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.task.empassignment.model.Department
import com.task.empassignment.model.DeptEmployees
import com.task.empassignment.model.DeptManager
import com.task.empassignment.model.Employees
import com.task.empassignment.model.Salaries
import com.task.empassignment.model.Titles
import com.task.empassignment.utils.Constant.BASE_URL
import com.task.empassignment.utils.Constant.GET_DEPT
import com.task.empassignment.utils.Constant.GET_DEPT_EMP
import com.task.empassignment.utils.Constant.GET_DEPT_MANAGER
import com.task.empassignment.utils.Constant.GET_EMPLOYEES
import com.task.empassignment.utils.Constant.GET_SALARY
import com.task.empassignment.utils.Constant.GET_TITLES
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


interface ApiService {


    @GET(GET_DEPT)
    suspend fun getDeptData(): Response<List<Department>>

    @GET(GET_EMPLOYEES)
    suspend fun getEmployees(): Response<List<Employees>>

    @GET(GET_DEPT_EMP)
    suspend fun getDeptEmp(): Response<List<DeptEmployees>>

    @GET(GET_DEPT_MANAGER)
    suspend fun getDeptManager(): Response<List<DeptManager>>

    @GET(GET_SALARY)
    suspend fun getSalary(): Response<List<Salaries>>

    @GET(GET_TITLES)
    suspend fun getTitles(): Response<List<Titles>>

    companion object {
        var retrofitService: ApiService? = null
        fun getInstance(): ApiService {
            if (retrofitService == null) {
                var retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiService::class.java)
            }
            return retrofitService!!
        }

    }
}