package cbd.podcasts.media

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

object MediaSourceCreator : IMediaSourceCreator {

    override fun createMediaSource(sourceUrl: String, context: Context, userAgent: String) = ExtractorMediaSource(
        Uri.parse(sourceUrl),
        DefaultDataSourceFactory(context, userAgent),
        DefaultExtractorsFactory(),
        null,
        null
    )

}