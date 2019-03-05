package cbd.podcasts.media

import android.content.Context
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource

interface IMediaSourceCreator {

    fun createMediaSource(sourceUrl: String, context: Context, userAgent: String): ExtractorMediaSource
}