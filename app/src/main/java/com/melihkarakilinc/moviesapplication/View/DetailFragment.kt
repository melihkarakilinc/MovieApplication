package com.melihkarakilinc.moviesapplication.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.melihkarakilinc.moviesapplication.Utils.ApiUrl
import com.melihkarakilinc.moviesapplication.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var exoPlayer: ExoPlayer? = null
    private var playbackPosition = 0L
    private var playWhenReady = true
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preparePlayer()
    }
    private fun preparePlayer() {
        exoPlayer = ExoPlayer.Builder(requireContext()).build()
        exoPlayer?.playWhenReady = true
        binding.playerView.player = exoPlayer
        val defaultHttpDataSourceFactory = DefaultHttpDataSource.Factory()
        val mediaItem =
            MediaItem.fromUri(ApiUrl.URL)
        val mediaSource =
            HlsMediaSource.Factory(defaultHttpDataSourceFactory).createMediaSource(mediaItem)
        exoPlayer?.setMediaSource(mediaSource)
        exoPlayer?.seekTo(playbackPosition)
        exoPlayer?.playWhenReady = playWhenReady
        exoPlayer?.prepare()
    }

    private fun releasePlayer() {
        exoPlayer?.let { player ->
            playbackPosition = player.currentPosition
            playWhenReady = player.playWhenReady
            player.release()
            exoPlayer = null
        }
    }

    override fun onStart() {
        super.onStart()
        preparePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onResume() {
        super.onResume()
        preparePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }
}