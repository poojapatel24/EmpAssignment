package com.task.empassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.Visibility
import com.task.empassignment.R
import com.task.empassignment.databinding.ActivitySplashBinding
import com.task.empassignment.localdatabase.DBHelper
import com.task.empassignment.ui.viewmodel.EmpDataViewModel
import com.task.empassignment.utils.Constant
import com.task.empassignment.utils.Constant.TABLE_DEPT
import com.task.empassignment.utils.Constant.TABLE_DEPT_EMP
import com.task.empassignment.utils.Constant.TABLE_DEPT_MANAGER
import com.task.empassignment.utils.Constant.TABLE_EMP
import com.task.empassignment.utils.Constant.TABLE_SALARIES
import com.task.empassignment.utils.Constant.TABLE_TITLE


class SplashActivity : AppCompatActivity() {
    private lateinit var empDataViewModel: EmpDataViewModel
    lateinit var binding: ActivitySplashBinding
    private lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        observeTheData()

    }

    private fun initData() {
        dbHelper = DBHelper(applicationContext)
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun observeTheData() {
        empDataViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[EmpDataViewModel::class.java]

        //observe the department data

        val deptRowCount = dbHelper.getRowCount(TABLE_DEPT)
        if (deptRowCount == 0) {
            empDataViewModel.deptList.observe(this, Observer {
                for (columnInfo in it) {
                    dbHelper.insertDepartment(columnInfo)
                }

            })
        }
        empDataViewModel.errormsg.observe(this, Observer {
            Log.d("error", "error msg  $it")
        })

        //observe the emp data
        val empRowCount = dbHelper.getRowCount(TABLE_EMP)
        if (empRowCount == 0) {
            empDataViewModel.empList.observe(this, Observer {
                for (columnInfo in it) {
                    dbHelper.insertEmployees(columnInfo)
                }
            })
        }


        //observe the department emp data
        val empDeptRowCount = dbHelper.getRowCount(TABLE_DEPT_EMP)
        if (empDeptRowCount == 0) {
            empDataViewModel.deptEmpList.observe(this, Observer {
                for (columnInfo in it) {
                    dbHelper.insertDeptEmployees(columnInfo)
                }
            })
        }

        //observe the department manager data
        val managerDeptRowCount = dbHelper.getRowCount(TABLE_DEPT_MANAGER)
        if (managerDeptRowCount == 0) {
            empDataViewModel.deptManagerList.observe(this, Observer {
                for (columnInfo in it) {
                    dbHelper.insertDeptManager(columnInfo)
                }
            })
        }


        //observe the salary data
        val salaryRowCount = dbHelper.getRowCount(TABLE_SALARIES)
        if (salaryRowCount == 0) {
            empDataViewModel.salaryList.observe(this, Observer {
                for (columnInfo in it) {
                    dbHelper.insertSalary(columnInfo)
                }
            })
        }

        //observe the title data
        val titleRowCount = dbHelper.getRowCount(TABLE_TITLE)
        if (titleRowCount == 0) {
            empDataViewModel.titleList.observe(this, Observer {
                for (columnInfo in it) {
                    dbHelper.insertTitle(columnInfo)
                }
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.progressBar.visibility = View.VISIBLE
                    startActivity(Intent(this, MainActivity::class.java))
                    //  finish()
                }, 3000)
            })
        }
        if (dbHelper.getRowCount(TABLE_DEPT) > 0 && dbHelper.getRowCount(TABLE_EMP) > 0 && dbHelper.getRowCount(TABLE_DEPT_EMP) > 0 && dbHelper.getRowCount(TABLE_DEPT_MANAGER) > 0 && dbHelper.getRowCount(TABLE_SALARIES) > 0 && dbHelper.getRowCount(TABLE_TITLE) > 0) {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.progressBar.visibility = View.VISIBLE
                startActivity(Intent(this, MainActivity::class.java))
                //  finish()
            }, 3000)
        }
    }
}