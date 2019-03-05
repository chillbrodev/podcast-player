package cbd.podcasts.podcastRepo

import android.content.res.AssetManager
import timber.log.Timber

object PodcastRepo : IPodcastRepo {

    override fun fetchAllPodcasts(assetManager: AssetManager): Array<String> {
        return assetManager.list("podcasts") ?: emptyArray()
    }

    override fun fetchPodcast(assetManager: AssetManager): PodcastStatus {
        val podcasts = fetchAllPodcasts(assetManager)
        Timber.d("Fetched ${podcasts.size} podcasts")

        return if (podcasts.isEmpty()) {
            PodcastStatus("", PodcastState.ERROR)
        } else {
            PodcastStatus("asset:///podcasts/${podcasts.first()}", PodcastState.SUCCESS)
        }
    }
}