package com.dara.ulessontask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dara.ulessontask.R
import com.dara.ulessontask.data.Lesson
import com.dara.ulessontask.databinding.FragmentPlayerBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

class PlayerFragment : Fragment(R.layout.fragment_player) {

    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!
    private lateinit var exoPlayer : SimpleExoPlayer
    private lateinit var playerView: PlayerView
    private lateinit var lesson: Lesson

    companion object{
        private const val LESSON = "lesson"
        fun newInstance(lesson: Lesson) =
            ChaptersFragment().apply {
                arguments = Bundle().apply { putParcelable(LESSON, lesson) }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        playerView = binding.playerView
        // Retrieve lesson from arguments
        lesson = arguments?.getParcelable(LESSON)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializePlayer()
    }

    private fun initializePlayer() {
        exoPlayer = SimpleExoPlayer.Builder(requireContext()).build()
        playerView.player = exoPlayer

        // Set media item using the lesson's media url
        val mediaItem = MediaItem.fromUri(lesson.media_url)
        exoPlayer.addMediaItem(mediaItem)

    }
}