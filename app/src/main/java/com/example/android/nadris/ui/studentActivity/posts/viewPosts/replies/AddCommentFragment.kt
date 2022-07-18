package com.example.android.nadris.ui.studentActivity.posts.viewPosts.replies

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentAddCommentBinding
import com.example.android.nadris.ui.studentActivity.posts.viewPosts.PostPageFragmentDirections
import com.example.android.nadris.util.isVisible
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class AddCommentFragment : Fragment() {

    val args: AddCommentFragmentArgs by navArgs()
    val viewModel: AddCommentViewModel by viewModels()
    private lateinit var binding: FragmentAddCommentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentAddCommentBinding.inflate(inflater, container, false)

        binding.viewmodel = viewModel

        binding.lifecycleOwner = this

        viewModel.postId = args.postId

        viewModel.getCurrentPost()

        viewModel.getAllComments()

        setupRV()

        val postRootView = requireActivity().findViewById<LinearLayout>(R.id.post_root_layout)


        viewModel.currentPostData.observe(viewLifecycleOwner){

            it?.let{
                binding.includedPost.postRootLayout.isVisible(true)
                if (!viewModel.currentPostData.value?.imageFilePath.isNullOrEmpty()) {
                    val file = File(viewModel.currentPostData.value?.imageFilePath)
                    if (file.exists()) {
                        val img = BitmapFactory.decodeFile(file.absolutePath)
                        binding.includedPost.imgPost.setImageBitmap(img!!)
                        binding.includedPost.imgPost.visibility = View.VISIBLE
                    } else {
                        binding.includedPost.imgPost.visibility = View.GONE
                    }
                } else {
                    binding.includedPost.imgPost.visibility = View.GONE
                }
                binding.includedPost.imgVote.setOnClickListener {
                    viewModel.currentPostData.value?.toggleVote()
                    viewModel.voteToPost()
                }

                binding.includedPost.bookmark.setOnClickListener {
                    viewModel.currentPostData.value?.toggleBookMark()
                    viewModel.bookMark()
                }

                binding.includedPost.profileImage.setOnClickListener {
                    navigateToPublicProfilePage(viewModel.currentPostData.value?.userId)
                }

                try {
                    if (!viewModel.currentPostData.value?.userImageFilePath.isNullOrEmpty()) {
                        val file = File(viewModel.currentPostData.value?.userImageFilePath)
                        if (file.exists()) {
                            val img = BitmapFactory.decodeFile(file.absolutePath)
                            binding.includedPost.profileImage.setImageBitmap(img!!)
                        }
                    }

                } catch (e: Throwable) {
                    Snackbar
                        .make(binding.root, e.message.toString(), Snackbar.LENGTH_LONG)
                        .show()
                }
            } ?: binding.includedPost.postRootLayout.isVisible(false)
        }

        viewModel.commentsResult.observe(this.viewLifecycleOwner) { result ->
            result.handleRepoResponse(
                onPreExecute = {

                },
                onLoading = {

                },
                onError = {
                    Snackbar.make(binding.root, result.error!!, Snackbar.LENGTH_LONG)
                        .show()
                },
                onSuccess = {
                    viewModel.commentsList.value = result.data
                }
            )

        }

        viewModel.postDataResult.observe(this.viewLifecycleOwner) { result ->
            result.handleRepoResponse(
                onPreExecute = {

                },
                onLoading = {

                },
                onError = {
                    Snackbar.make(binding.root, result.error!!, Snackbar.LENGTH_LONG)
                        .show()
                },
                onSuccess = {
                    viewModel.currentPostData.value = result.data
                }
            )

        }

        viewModel.addingNewCommentResult.observe(this.viewLifecycleOwner) { result ->
            result.handleRepoResponse(
                onPreExecute = {

                },
                onLoading = {

                },
                onError = {
                    Snackbar.make(binding.root, result.error!!, Snackbar.LENGTH_LONG)
                        .show()
                },
                onSuccess = {
                    if (result.data == true)
                        viewModel.getAllComments()
                }
            )

        }

        return binding.root
    }

    private fun navigateToPublicProfilePage(destinationProfileEmail: String?) {
        destinationProfileEmail?.let {
            this.findNavController()
                .navigate(PostPageFragmentDirections
                    .actionPostsFragmentToPublicProfileFragment( it))
        } ?: Snackbar
            .make(binding.root, "can't navigate to profile image user id is null", Snackbar.LENGTH_LONG)
            .show()

    }

    private fun setupRV() {

        binding.RVComment.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        val adapter = CustomAdapterComment()

        viewModel.commentsList.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
        }

        binding.RVComment.adapter = adapter

    }

}