package cbd.podcasts.main

import cbd.podcasts.podcastRepo.PodcastStatus

class MainPresenter(private val view: MainContract.MainView) : MainContract.MainPresenter {

    override fun init() {
        view.initExoplayer()
        view.loadPodcasts()
    }

    override fun shutdown() {
        view.release()
    }

    override fun podcastSelected(podcastStatus: PodcastStatus) {
        view.playPodcast(podcastStatus)
    }
}