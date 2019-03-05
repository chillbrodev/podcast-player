package cbd.podcasts.podcastRepo

import android.content.res.AssetManager

interface IPodcastRepo {

    fun fetchAllPodcasts(assetManager: AssetManager): Array<String>
    fun fetchPodcast(assetManager: AssetManager, position: Int): PodcastStatus
}