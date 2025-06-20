package org.aifooddelivery.app.core.database.di

import org.aifooddelivery.app.core.database.dao.UserDao
import org.koin.dsl.module


val databaseModule = module{
    single<UserDao> { UserDao(get()) }

}
