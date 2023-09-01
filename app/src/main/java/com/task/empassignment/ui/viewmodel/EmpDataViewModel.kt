package com.task.empassignment.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.empassignment.model.Department
import com.task.empassignment.model.DeptEmployees
import com.task.empassignment.model.DeptManager
import com.task.empassignment.model.Employees
import com.task.empassignment.model.Salaries
import com.task.empassignment.model.Titles
import com.task.empassignment.restmanager.ApiService
import com.task.empassignment.ui.repository.EmpDataRepository
import com.task.empassignment.utils.Constant.BASE_URL
import com.task.empassignment.utils.Constant.GET_DEPT
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type


class EmpDataViewModel(application: Application) : AndroidViewModel(application) {
    val deptList = MutableLiveData<List<Department>>()
    var errormsg = MutableLiveData<String>()

    val empList = MutableLiveData<List<Employees>>()
    val deptEmpList = MutableLiveData<List<DeptEmployees>>()
    val deptManagerList = MutableLiveData<List<DeptManager>>()
    val salaryList = MutableLiveData<List<Salaries>>()
    val titleList = MutableLiveData<List<Titles>>()

    //val empData: LiveData<Employees> = _empData
    private var empDataRepository: EmpDataRepository

    init {
        var retrofitService = ApiService.getInstance()
        empDataRepository = EmpDataRepository(retrofitService)
        getDeptData()
        getEmployeesData()
        getDeptEmpData()
        getDeptManagerData()
        getSalaryData()
        getTitleData()
    }

    fun getDeptData() {
        CoroutineScope(Dispatchers.IO).launch {
            var response = empDataRepository.getDeptData()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    deptList.postValue(response.body())
                } else {
                    errormsg.postValue(response.message())
                }
            }
        }
    }

    fun getEmployeesData() {
        CoroutineScope(Dispatchers.IO).launch {
            var response = empDataRepository.getEmployees()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    empList.postValue(response.body())
                } else {
                    errormsg.postValue(response.message())
                }
            }
        }
    }

    fun getDeptEmpData() {
        CoroutineScope(Dispatchers.IO).launch {
            var response = empDataRepository.getDeptEmp()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    deptEmpList.postValue(response.body())
                } else {
                    errormsg.postValue(response.message())
                }
            }
        }
    }

    fun getDeptManagerData() {
        CoroutineScope(Dispatchers.IO).launch {
            var response = empDataRepository.getDeptManager()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    deptManagerList.postValue(response.body())
                } else {
                    errormsg.postValue(response.message())
                }
            }
        }
    }

    fun getSalaryData() {
        CoroutineScope(Dispatchers.IO).launch {
            var response = empDataRepository.getSalary()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    salaryList.postValue(response.body())
                } else {
                    errormsg.postValue(response.message())
                }
            }
        }
    }

    fun getTitleData() {
        CoroutineScope(Dispatchers.IO).launch {
            var response = empDataRepository.getTitles()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    titleList.postValue(response.body())
                } else {
                    errormsg.postValue(response.message())
                }
            }
        }
    }


}