package cbd.podcasts.media

interface IPodcastTitleParser {
    fun extractTitle(podcastFile: String): String
}
