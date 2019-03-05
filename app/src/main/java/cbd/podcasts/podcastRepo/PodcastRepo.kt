package cbd.podcasts.podcastRepo

import android.content.res.AssetManager
import cbd.podcasts.media.PodcastTitleParser
import timber.log.Timber

object PodcastRepo : IPodcastRepo {

    override fun fetchAllPodcasts(assetManager: AssetManager): Array<String> {
        return assetManager.list("podcasts") ?: emptyArray()
    }

    override fun fetchPodcast(assetManager: AssetManager, position: Int): PodcastStatus {
        val podcasts = fetchAllPodcasts(assetManager)
        Timber.d("Fetched ${podcasts.size} podcasts")

        return if (podcasts.isEmpty()) {
            PodcastStatus(state = PodcastState.ERROR)
        } else {
            val podcast = podcasts[position]
            val podcastSourceUrl = "asset:///podcasts/$podcast"
            Timber.d("Returning podcast: $podcastSourceUrl")
            PodcastStatus(podcastSourceUrl, PodcastTitleParser.extractTitle(podcast), PodcastState.SUCCESS)
        }
    }
}