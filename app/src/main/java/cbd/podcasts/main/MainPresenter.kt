package cbd.podcasts.main

class MainPresenter(private val view: MainContract.MainView) : MainContract.MainPresenter {

    override fun init() {
        view.initExoplayer()
    }

    override fun shutdown() {
        view.release()
    }
}