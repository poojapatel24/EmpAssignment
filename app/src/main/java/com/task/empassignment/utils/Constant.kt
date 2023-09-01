package com.task.empassignment.utils

object Constant {
    //API Endpoint
    const val BASE_URL = "https://raw.githubusercontent.com/"
    const val GET_EMPLOYEES = "poojapatel24/EmpAndroidAssignment/main/employees.json"
    const val GET_DEPT = "poojapatel24/EmpAndroidAssignment/main/departments.json"
    const val GET_DEPT_EMP="poojapatel24/EmpAndroidAssignment/main/emp_departments.json"
    const val GET_DEPT_MANAGER="poojapatel24/EmpAndroidAssignment/main/department_manager.json"
    const val GET_SALARY="poojapatel24/EmpAndroidAssignment/main/salaries.json"
    const val GET_TITLES="poojapatel24/EmpAndroidAssignment/main/titles.json"

    //table & column
    //department table
    const val TABLE_DEPT="department"
    const val COLUMN_DEPT_NO="dept_no"
    const val COLUMN_DEPT_NAME="dept_name"
    //employees table
    const val TABLE_EMP="employees"
    const val COLUMN_EMP_NO="emp_no"
    const val COLUMN_BIRTH_DATE="birth_date"
    const val COLUMN_FIRST_NAME="first_name"
    const val COLUMN_LAST_NAME="last_name"
    const val COLUMN_GENDER="gender"
    const val COLUMN_HIRE_DATE="hire_date"
    //department of employees table
    const val TABLE_DEPT_EMP="dept_emp"
    const val COLUMN_DEPT_EMP_FROM_DATE="from_date"
    const val COLUMN_DEPT_EMP_TO_DATE="to_date"
    //department of manager table
    const val TABLE_DEPT_MANAGER="dept_MANAGER"
    const val COLUMN_DEPT_MANAGER_FROM_DATE="from_date"
    const val COLUMN_DEPT_MANAGER_TO_DATE="to_date"
    //department of salary table
    const val TABLE_SALARIES="salaries"
    const val COLUMN_SALARIES_FROM_DATE="from_date"
    const val COLUMN_SALARIES_TO_DATE="to_date"
    const val COLUMN_SALARY="salary"
    //department of title table
    const val TABLE_TITLE="title"
    const val COLUMN_TITLE_FROM_DATE="from_date"
    const val COLUMN_TITLE_TO_DATE="to_date"
    const val COLUMN_TITLE="title"

    const val rowCount="rowCount"
}