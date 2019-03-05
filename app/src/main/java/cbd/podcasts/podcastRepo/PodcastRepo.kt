package cbd.podcasts.podcastRepo

import android.content.res.AssetManager
import cbd.podcasts.media.PodcastTitleParser
import timber.log.Timber

object PodcastRepo : IPodcastRepo {

    override fun fetchAllPodcasts(assetManager: AssetManager): List<PodcastStatus> {
        val podcasts = assetManager.list("podcasts") ?: emptyArray()
        Timber.d("Fetched ${podcasts.size} podcasts")

        return podcasts.map { podcastFile ->
            val podcastSourceUrl = "asset:///podcasts/$podcastFile"
            val title = PodcastTitleParser.extractTitle(podcastFile)
            
            PodcastStatus(podcastSourceUrl, title, PodcastState.SUCCESS)
        }
    }

    override fun fetchPodcast(assetManager: AssetManager, position: Int): PodcastStatus {
        val podcasts = fetchAllPodcasts(assetManager)

        return if (podcasts.isEmpty()) {
            PodcastStatus(state = PodcastState.ERROR)
        } else {
            val podcast = podcasts[position]

            Timber.d("Returning podcast: ${podcast.podcastTitle}")
            podcast
        }
    }
}