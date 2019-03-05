package cbd.podcasts

import android.app.Application
import timber.log.Timber

class PodcatsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        app = this
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var app: PodcatsApp
    }
}