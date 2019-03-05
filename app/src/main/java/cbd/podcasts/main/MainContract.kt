package cbd.podcasts.main

interface MainContract {

    interface MainView {
        fun initExoplayer()
        fun release()
    }

    interface MainPresenter {

        fun init()
        fun shutdown()
    }
}