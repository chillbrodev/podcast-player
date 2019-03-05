package cbd.podcasts.main

interface MainContract {

    interface MainView {
        fun initExoplayer()
        fun release()
        fun loadPodcasts()
    }

    interface MainPresenter {

        fun init()
        fun shutdown()
    }
}