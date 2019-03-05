package cbd.podcasts.main

import cbd.podcasts.podcastRepo.PodcastStatus

interface MainContract {

    interface MainView {
        fun initExoplayer()
        fun release()
        fun loadPodcasts()
        fun playPodcast(podcastStatus: PodcastStatus)
    }

    interface MainPresenter {

        fun init()
        fun shutdown()
        fun podcastSelected(podcastStatus: PodcastStatus)
    }
}