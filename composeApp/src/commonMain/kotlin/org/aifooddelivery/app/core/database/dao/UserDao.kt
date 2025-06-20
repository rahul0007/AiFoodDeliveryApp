package org.aifooddelivery.app.core.database.dao

import app.cash.sqldelight.db.SqlDriver
import com.aifood.MyDataBase
import com.aifood.UserEntity

class UserDao(driver: SqlDriver) {

    private val db = MyDataBase(driver)
    private val queries = db.userEntityQueries

    fun insertUser(username: String, email: String, password: String) {
        queries.insertUser(username, email, password)
    }

    fun getUserByEmail(email: String): UserEntity? {
        return queries.getUserByEmail(email).executeAsOneOrNull()
    }

    fun validateUser(email: String, password: String): UserEntity? {
        return queries.validateUser(email, password).executeAsOneOrNull()
    }
}