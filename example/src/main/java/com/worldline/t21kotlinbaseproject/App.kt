package com.worldline.t21kotlinbaseproject

import android.app.Application
import com.worldline.t21kotlinbaseproject.core.di.appModule
import com.worldline.t21kotlinbaseproject.core.di.dataModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule(this@App))
        import(dataModule(this@App))
    }

}