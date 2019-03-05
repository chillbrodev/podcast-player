package cbd.podcasts.main

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cbd.podcasts.PodcatsApp
import cbd.podcasts.R
import cbd.podcasts.media.MediaSourceCreator
import cbd.podcasts.podcastRecyclerView.PodcastAdapter
import cbd.podcasts.podcastRepo.PodcastRepo
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.MainView {

    private lateinit var exoPlayer: SimpleExoPlayer
    private lateinit var presenter: MainContract.MainPresenter
    private lateinit var podcastAdapter: PodcastAdapter
    private lateinit var userAgent: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)
        userAgent = Util.getUserAgent(this, "Podcasts")

        podcastAdapter = PodcastAdapter(mutableListOf()) { podcastStatus ->
            val mediaSource = MediaSourceCreator.createMediaSource(
                podcastStatus.podcastUrl,
                this,
                userAgent
            )

            exoPlayer.prepare(mediaSource)

            exoPlayer.playWhenReady = true
        }
        rvPodcasts.adapter = podcastAdapter
        rvPodcasts.layoutManager = LinearLayoutManager(this)
    }

    override fun initExoplayer() {
        val rendersFactory = DefaultRenderersFactory(this)
        val tracksSelector = DefaultTrackSelector()
        exoPlayer = ExoPlayerFactory.newSimpleInstance(this, rendersFactory, tracksSelector)
    }

    override fun loadPodcasts() {
        val podcasts = PodcastRepo.fetchAllPodcasts(PodcatsApp.app.assets)
        podcastAdapter.updateData(podcasts)
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
