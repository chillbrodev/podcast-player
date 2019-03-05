package cbd.podcasts.main

import cbd.podcasts.PodcatsApp
import cbd.podcasts.podcastRepo.PodcastRepo
import cbd.podcasts.podcastRepo.PodcastStatus

class MainPresenter(private val view: MainContract.MainView) : MainContract.MainPresenter {

    override fun init() {
        view.initExoplayer()
        fetchPodcasts()
    }

    override fun shutdown() {
        view.release()
    }

    override fun podcastSelected(podcastStatus: PodcastStatus) {
        view.playPodcast(podcastStatus)
    }

    private fun fetchPodcasts(){
        val podcasts = PodcastRepo.fetchAllPodcasts(PodcatsApp.app.assets)
        view.loadPodcasts(podcasts)
    }
}