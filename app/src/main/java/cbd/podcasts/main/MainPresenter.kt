package cbd.podcasts.main

class MainPresenter(private val view: MainContract.MainView) : MainContract.MainPresenter {

    override fun init() {
        view.initExoplayer()
        view.loadPodcasts()
    }

    override fun shutdown() {
        view.release()
    }
}