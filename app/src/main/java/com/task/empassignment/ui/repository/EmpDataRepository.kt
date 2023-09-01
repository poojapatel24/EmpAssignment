package com.task.empassignment.ui.repository

import com.task.empassignment.model.Department
import com.task.empassignment.model.DeptEmployees
import com.task.empassignment.model.DeptManager
import com.task.empassignment.model.Employees
import com.task.empassignment.model.Salaries
import com.task.empassignment.model.Titles
import com.task.empassignment.restmanager.ApiService
import retrofit2.Call
import retrofit2.Response

class EmpDataRepository(private val apiService: ApiService) {

    suspend fun getDeptData(): Response<List<Department>> {
        return apiService.getDeptData()
    }

    suspend fun getEmployees(): Response<List<Employees>> {
        return apiService.getEmployees()
    }

    suspend fun getDeptEmp(): Response<List<DeptEmployees>> {
        return apiService.getDeptEmp()
    }

    suspend fun getDeptManager(): Response<List<DeptManager>> {
        return apiService.getDeptManager()
    }

    suspend fun getSalary(): Response<List<Salaries>> {
        return apiService.getSalary()
    }

    suspend fun getTitles(): Response<List<Titles>> {
        return apiService.getTitles()
    }
}