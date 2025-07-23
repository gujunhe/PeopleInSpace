package com.surrus.common.repository

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.surrus.common.di.PeopleInSpaceDatabaseWrapper
import com.surrus.peopleinspace.db.PeopleInSpaceDatabase
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.*
import org.koin.dsl.module


actual fun platformModule() = module {
    single {
        val driver = NativeSqliteDriver(PeopleInSpaceDatabase.Schema.synchronous(), "peopleinspace.db")
        PeopleInSpaceDatabaseWrapper(driver, PeopleInSpaceDatabase(driver))
    }
    single<HttpClientEngine> { Darwin.create() }
}
