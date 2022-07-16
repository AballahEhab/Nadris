package com.example.android.nadris.ui.studentActivity.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.Mode
import com.example.android.nadris.databinding.FragmentPostPageBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostPageFragment() : Fragment() {

    private val TAG = "PostPageFragment"

    val viewModel: PostPageViewModel by viewModels()
    lateinit var binding: FragmentPostPageBinding
    lateinit var adapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentPostPageBinding.inflate(layoutInflater, container, false)

        initialization()

        binding.lifecycleOwner = this.viewLifecycleOwner

        binding.postViewModle = viewModel

        subscribeToObservers()

        viewModel.getPosts()

        constructInquiresRecyclerView()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getPosts()
        }


        return binding.root
    }

    private fun constructInquiresRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }

    private fun initialization() {
        adapter = CustomAdapter(viewModel)
    }

    private fun subscribeToObservers() {

        viewModel.postsResults.observe(viewLifecycleOwner) { result ->
            result.handleRepoResponse(
                onPreExecute = {
                    binding.swipeRefreshLayout.isRefreshing = false

                },
                onLoading = {
                    binding.swipeRefreshLayout.isRefreshing = true
                    result.data?.let { adapter.differ.submitList(it) }
                },
                onError = {
                    Snackbar.make(binding.root, result.error!!, Snackbar.LENGTH_LONG)
                        .show()
                },
                onSuccess = {
                    Log.v(TAG, result.data.toString())
                    viewModel.updatePostsList(result.data!!)
                    adapter.differ.submitList(result.data)
                }
            )

        }

        viewModel.updatedPostResult.observe(viewLifecycleOwner) { result ->
            result.handleRepoResponse(
                onError = {
                    Snackbar.make(binding.root, result.error!!, Snackbar.LENGTH_LONG)
                        .show()
                },
                onSuccess = {
                    viewModel.updateASpecificPost(result.data!!)
                    adapter.differ.submitList(viewModel.postsList.value)
                }
            )

        }

        viewModel.navigateToAddPost.observe(this.viewLifecycleOwner) {
            if (it) {
                this.findNavController()
                    .navigate(PostPageFragmentDirections.actionPostsFragmentToAddPostFragment(Mode.CREATE,
                        "",
                        "",
                        "",
                        ""))
                viewModel.navigateToAddPostDone()
            }
        }

        viewModel.destinationProfileEmail.observe(this.viewLifecycleOwner) {
            it?.let { destinationProfileEmail ->
                this.findNavController()
                    .navigate(PostPageFragmentDirections
                        .actionPostsFragmentToPublicProfileFragment(destinationProfileEmail))
                viewModel.navigationToPublicProfileDone()
            }
        }

        viewModel.aPostToEdit.observe(viewLifecycleOwner) { post ->
            post?.let {
                findNavController().navigate(
                    PostPageFragmentDirections
                        .actionPostsFragmentToAddPostFragment(Mode.EDIT,
                            post.postId, post.subject, post.content, post.imageFilePath!!))
                viewModel.navigationToEditPostDone()
            }
        }
    }

}