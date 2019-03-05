package cbd.podcasts.media

import timber.log.Timber

object PodcastTitleParser : IPodcastTitleParser {
    override fun extractTitle(podcastFile: String): String {
        val split = podcastFile.split("-")
        val title = if(split.isEmpty()){
            "Unknown Title"
        } else {
            "${split[0]}-${split[2]}"
        }

        Timber.d("Podcast Title: $title")
        return title
    }
}