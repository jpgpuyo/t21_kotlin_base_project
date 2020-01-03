package com.worldline.t21kotlinbaseproject.core.di

import android.content.Context
import com.worldline.t21kotlinbaseproject.core.coroutines.CoroutineContextProvider
import com.worldline.t21kotlinbaseproject.core.error.AndroidErrorHandler
import com.worldline.t21kotlinbaseproject.core.error.ErrorHandler
import com.worldline.t21kotlinbaseproject.data.users.UsersNetworkDataSource
import com.worldline.t21kotlinbaseproject.data.users.UsersRepositoryImpl
import com.worldline.t21kotlinbaseproject.domain.repository.UsersRepository
import com.worldline.t21kotlinbaseproject.navigation.Navigator
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

fun appModule(context: Context) = Kodein.Module("appModule") {
    bind<Context>() with singleton { context }
    bind<ErrorHandler>() with singleton { AndroidErrorHandler(context = context) }
    bind<Navigator>() with singleton { Navigator() }
    bind<CoroutineContextProvider>() with singleton { CoroutineContextProvider() }
}

fun dataModule(context: Context) = Kodein.Module("dataModule") {
    bind<UsersRepository>() with singleton { UsersRepositoryImpl(instance()) }
    bind<UsersNetworkDataSource>() with singleton { UsersNetworkDataSource() }
}
