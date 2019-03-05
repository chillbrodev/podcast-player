package cbd.podcasts.podcastRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cbd.podcasts.R
import cbd.podcasts.podcastRepo.PodcastStatus

class PodcastAdapter(private val podcasts: MutableList<PodcastStatus>,
                     private val podcastSelected: (PodcastStatus) -> Unit) :
    RecyclerView.Adapter<PodcastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_podcast_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = podcasts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(podcasts[position], podcastSelected)
    }

    fun updateData(userData: List<PodcastStatus>) {
        this.podcasts.clear()
        this.podcasts.addAll(userData)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val podcastTitle = itemView.findViewById<TextView>(R.id.podcastTitle)
        private val podcastPlayBtn = itemView.findViewById<ImageButton>(R.id.playBtn)

        fun bind(podcast: PodcastStatus, podcastSelected: (PodcastStatus) -> Unit) {
            podcastTitle.text = podcast.podcastTitle

            podcastPlayBtn.setOnClickListener {
                podcastSelected(podcast)
            }
        }
    }
}