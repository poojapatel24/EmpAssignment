package com.task.empassignment.localdatabase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.task.empassignment.model.Department
import com.task.empassignment.model.DeptEmployees
import com.task.empassignment.model.DeptManager
import com.task.empassignment.model.Employees
import com.task.empassignment.model.Salaries
import com.task.empassignment.model.Titles
import com.task.empassignment.utils.Constant.COLUMN_BIRTH_DATE
import com.task.empassignment.utils.Constant.COLUMN_DEPT_EMP_FROM_DATE
import com.task.empassignment.utils.Constant.COLUMN_DEPT_EMP_TO_DATE
import com.task.empassignment.utils.Constant.COLUMN_DEPT_MANAGER_FROM_DATE
import com.task.empassignment.utils.Constant.COLUMN_DEPT_MANAGER_TO_DATE
import com.task.empassignment.utils.Constant.COLUMN_DEPT_NAME
import com.task.empassignment.utils.Constant.COLUMN_DEPT_NO
import com.task.empassignment.utils.Constant.COLUMN_EMP_NO
import com.task.empassignment.utils.Constant.COLUMN_FIRST_NAME
import com.task.empassignment.utils.Constant.COLUMN_GENDER
import com.task.empassignment.utils.Constant.COLUMN_HIRE_DATE
import com.task.empassignment.utils.Constant.COLUMN_LAST_NAME
import com.task.empassignment.utils.Constant.COLUMN_SALARIES_FROM_DATE
import com.task.empassignment.utils.Constant.COLUMN_SALARIES_TO_DATE
import com.task.empassignment.utils.Constant.COLUMN_SALARY
import com.task.empassignment.utils.Constant.COLUMN_TITLE
import com.task.empassignment.utils.Constant.COLUMN_TITLE_FROM_DATE
import com.task.empassignment.utils.Constant.COLUMN_TITLE_TO_DATE
import com.task.empassignment.utils.Constant.TABLE_DEPT
import com.task.empassignment.utils.Constant.TABLE_DEPT_EMP
import com.task.empassignment.utils.Constant.TABLE_DEPT_MANAGER
import com.task.empassignment.utils.Constant.TABLE_EMP
import com.task.empassignment.utils.Constant.TABLE_SALARIES
import com.task.empassignment.utils.Constant.TABLE_TITLE

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "mydatabase.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createDepartmentTable = """
            CREATE TABLE $TABLE_DEPT (
                $COLUMN_DEPT_NO TEXT PRIMARY KEY,
                $COLUMN_DEPT_NAME TEXT
            )
        """.trimIndent()

        db?.execSQL(createDepartmentTable)


        val createEmployeesTable = """
            CREATE TABLE $TABLE_EMP (
                $COLUMN_EMP_NO TEXT PRIMARY KEY,
                $COLUMN_BIRTH_DATE TEXT,
                $COLUMN_FIRST_NAME TEXT,
                $COLUMN_LAST_NAME TEXT,
                $COLUMN_GENDER TEXT,
                $COLUMN_HIRE_DATE TEXT
            )
        """.trimIndent()

        db?.execSQL(createEmployeesTable)

        val createDeptEmployeesTable = """
            CREATE TABLE $TABLE_DEPT_EMP (               
                $COLUMN_DEPT_EMP_FROM_DATE TEXT,
                $COLUMN_DEPT_EMP_TO_DATE TEXT,
                $COLUMN_EMP_NO TEXT,
                $COLUMN_DEPT_NO TEXT,
                FOREIGN KEY ($COLUMN_EMP_NO) REFERENCES $TABLE_EMP ($COLUMN_EMP_NO),
                FOREIGN KEY ($COLUMN_DEPT_NO) REFERENCES $TABLE_DEPT ($COLUMN_DEPT_NO)
                
            )
        """.trimIndent()

        db?.execSQL(createDeptEmployeesTable)

        val createDeptManagerTable = """
            CREATE TABLE $TABLE_DEPT_MANAGER (               
                $COLUMN_DEPT_MANAGER_FROM_DATE  TEXT,
                $COLUMN_DEPT_MANAGER_TO_DATE TEXT,
                $COLUMN_EMP_NO TEXT,
                $COLUMN_DEPT_NO TEXT,
                FOREIGN KEY ($COLUMN_EMP_NO) REFERENCES $TABLE_EMP ($COLUMN_EMP_NO),
                FOREIGN KEY ($COLUMN_DEPT_NO) REFERENCES $TABLE_DEPT ($COLUMN_DEPT_NO)
                
            )
        """.trimIndent()

        db?.execSQL(createDeptManagerTable)

        val createSalaryTable = """
            CREATE TABLE $TABLE_SALARIES (               
                $COLUMN_SALARIES_FROM_DATE  TEXT PRIMARY KEY,
                $COLUMN_SALARIES_TO_DATE TEXT,
                $COLUMN_SALARY TEXT,
                $COLUMN_EMP_NO TEXT,
                FOREIGN KEY ($COLUMN_EMP_NO) REFERENCES $TABLE_EMP ($COLUMN_EMP_NO)              
                
            )
        """.trimIndent()

        db?.execSQL(createSalaryTable)

        val createTitleTable = """
            CREATE TABLE $TABLE_TITLE (               
                $COLUMN_TITLE_FROM_DATE  TEXT PRIMARY KEY,
                $COLUMN_TITLE_TO_DATE TEXT,
                $COLUMN_TITLE TEXT,
                $COLUMN_EMP_NO TEXT,
                FOREIGN KEY ($COLUMN_EMP_NO) REFERENCES $TABLE_EMP ($COLUMN_EMP_NO)              
                
            )
        """.trimIndent()

        db?.execSQL(createTitleTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_DEPT")
        onCreate(db)
    }

    fun insertDepartment(department: Department) {
        val values = ContentValues().apply {
            put(COLUMN_DEPT_NO, department.dept_no)
            put(COLUMN_DEPT_NAME, department.dept_name)
        }
        val db = writableDatabase
        db.insert(TABLE_DEPT, null, values)
        db.close()
    }

    fun insertEmployees(employees: Employees) {
        val values = ContentValues().apply {
            put(COLUMN_EMP_NO, employees.emp_no)
            put(COLUMN_BIRTH_DATE, employees.birth_date)
            put(COLUMN_FIRST_NAME, employees.first_name)
            put(COLUMN_LAST_NAME, employees.last_name)
            put(COLUMN_GENDER, employees.gender)
            put(COLUMN_HIRE_DATE, employees.hire_date)
        }
        val db = writableDatabase
        db.insert(TABLE_EMP, null, values)
        db.close()
    }

    fun insertDeptEmployees(deptEmployees: DeptEmployees) {
        val values = ContentValues().apply {
            put(COLUMN_EMP_NO, deptEmployees.emp_no)
            put(COLUMN_DEPT_NO, deptEmployees.dept_no)
            put(COLUMN_DEPT_EMP_FROM_DATE, deptEmployees.from_date)
            put(COLUMN_DEPT_EMP_TO_DATE, deptEmployees.to_date)
        }
        val db = writableDatabase
        db.insert(TABLE_DEPT_EMP, null, values)
        db.close()
    }

    fun insertDeptManager(deptManager: DeptManager) {
        val values = ContentValues().apply {
            put(COLUMN_EMP_NO, deptManager.emp_no)
            put(COLUMN_DEPT_NO, deptManager.dept_no)
            put(COLUMN_DEPT_MANAGER_FROM_DATE, deptManager.from_date)
            put(COLUMN_DEPT_MANAGER_TO_DATE, deptManager.to_date)
        }
        val db = writableDatabase
        db.insert(TABLE_DEPT_MANAGER, null, values)
        db.close()
    }

    fun insertSalary(salaries: Salaries) {
        val values = ContentValues().apply {
            put(COLUMN_EMP_NO, salaries.emp_no)
            put(COLUMN_SALARY, salaries.salary)
            put(COLUMN_SALARIES_FROM_DATE, salaries.from_date)
            put(COLUMN_SALARIES_TO_DATE, salaries.to_date)
        }
        val db = writableDatabase
        db.insert(TABLE_SALARIES, null, values)
        db.close()
    }

    fun insertTitle(titles: Titles) {
        val values = ContentValues().apply {
            put(COLUMN_EMP_NO, titles.emp_no)
            put(COLUMN_TITLE, titles.title)
            put(COLUMN_TITLE_FROM_DATE, titles.from_date)
            put(COLUMN_TITLE_TO_DATE, titles.to_date)
        }
        val db = writableDatabase
        db.insert(TABLE_TITLE, null, values)
        db.close()
    }

    fun getRowCount(tableName: String): Int {
        val db = readableDatabase
        val query = "SELECT COUNT(*) FROM $tableName"
        val cursor = db.rawQuery(query, null)
        var rowCount = 0

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                rowCount = cursor.getInt(0)
            }
            cursor.close()
        }

        db.close()
        return rowCount
    }

}