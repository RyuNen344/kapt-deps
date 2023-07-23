package io.github.ryunen344.kapt.deps.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.ryunen344.kapt.deps.db.sqlite.Database
import io.github.ryunen344.kapt.deps.db.sqlite.PlayerQueries
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {
    @Provides
    @Singleton
    fun provideSqlDriver(
        @ApplicationContext context: Context,
    ): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context)
    }

    @Provides
    @Singleton
    fun provideCache(sqlDriver: SqlDriver): Database {
        return Database(sqlDriver)
    }

    @Provides
    @Singleton
    fun providePlayerQueries(database: Database): PlayerQueries {
        return database.playerQueries
    }
}
