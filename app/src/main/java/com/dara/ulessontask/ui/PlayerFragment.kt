package com.dara.ulessontask.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dara.ulessontask.R
import com.dara.ulessontask.data.Lesson
import com.dara.ulessontask.data.RecentLesson
import com.dara.ulessontask.databinding.FragmentPlayerBinding
import com.dara.ulessontask.viewmodel.MainViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

class PlayerFragment : Fragment(R.layout.fragment_player) {

    private val viewModel by viewModels<MainViewModel>()
    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!
    private var exoPlayer: SimpleExoPlayer? = null
    private lateinit var playerView: PlayerView
    private lateinit var lesson: Lesson
    private val isNewApi = Build.VERSION.SDK_INT >= 24
    private var playLessonWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    companion object {
        private const val LESSON = "lesson"
        fun newInstance(lesson: Lesson) =
            PlayerFragment().apply {
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

    private fun initializePlayer() {
        exoPlayer = SimpleExoPlayer.Builder(requireContext()).build()
        playerView.player = exoPlayer

        // Set media item using the lesson's media url
        val mediaItem = MediaItem.fromUri(lesson.media_url)
        exoPlayer?.addMediaItem(mediaItem)

        exoPlayer?.playWhenReady = playLessonWhenReady
        exoPlayer?.seekTo(currentWindow, playbackPosition)
        exoPlayer?.prepare()
        addLessonToRecents()
    }

    /**
     * Release resources when not in use
     */
    private fun releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer!!.apply {
                playLessonWhenReady = playWhenReady
                currentWindow = currentWindowIndex
                playbackPosition = currentPosition
                release()
            }
            exoPlayer = null
        }
    }

    private fun addLessonToRecents() {
        viewModel.getSubjectById(lesson.subject_id).observe(viewLifecycleOwner, {
            val recentLesson = RecentLesson(
                lesson.id,
                lesson.name,
                lesson.icon,
                lesson.media_url,
                lesson.subject_id,
                lesson.chapter_id,
                it.name
            )
            viewModel.addRecentLesson(recentLesson).observe(viewLifecycleOwner, {
                return@observe
            })
        })

    }

    override fun onStart() {
        super.onStart()
        if (isNewApi) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isNewApi || exoPlayer == null) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (!isNewApi) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (isNewApi) {
            releasePlayer()
        }
    }
}