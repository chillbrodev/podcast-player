package cbd.podcasts.main

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cbd.podcasts.PodcatsApp
import cbd.podcasts.R
import cbd.podcasts.media.MediaSourceCreator
import cbd.podcasts.podcastRepo.PodcastRepo
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.util.Util

class MainActivity : AppCompatActivity(), MainContract.MainView {

    private lateinit var exoPlayer: SimpleExoPlayer
    private lateinit var presenter: MainContract.MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)
    }

    override fun initExoplayer() {
        val rendersFactory = DefaultRenderersFactory(this)
        val tracksSelector = DefaultTrackSelector()
        val userAgent = Util.getUserAgent(this, "Podcasts")

        exoPlayer = ExoPlayerFactory.newSimpleInstance(this, rendersFactory, tracksSelector)
        val podcastSource = PodcastRepo.fetchPodcast(PodcatsApp.app.assets, 3).podcastUrl
        val mediaSource = MediaSourceCreator.createMediaSource(
            podcastSource,
            this,
            userAgent
        )

        exoPlayer.prepare(mediaSource)

        exoPlayer.playWhenReady = true

    }

    override fun release() = exoPlayer.release()

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            presenter.init()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            presenter.init()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            presenter.shutdown()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            presenter.shutdown()
        }
    }
}
