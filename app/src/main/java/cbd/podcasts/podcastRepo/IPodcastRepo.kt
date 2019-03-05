package cbd.podcasts.podcastRepo

import android.content.res.AssetManager

interface IPodcastRepo {

    fun fetchAllPodcasts(assetManager: AssetManager): List<PodcastStatus>
    fun fetchPodcast(assetManager: AssetManager, position: Int): PodcastStatus
}