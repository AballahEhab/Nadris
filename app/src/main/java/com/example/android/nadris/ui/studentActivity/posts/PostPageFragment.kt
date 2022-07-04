package com.example.android.nadris.ui.studentActivity.posts

import android.os.Bundle
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostPageFragment() : Fragment() {

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

        subscribeToObservers()

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

        viewModel.postsList.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
        }

        viewModel.navigate_to_add_post.observe(this.viewLifecycleOwner) {
            if (it) {
                this.findNavController()
                    .navigate(PostPageFragmentDirections.actionPostsFragmentToAddPostFragment())
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
                            post.postId, post.subject, post.content, post.hasImage))
                viewModel.aPostToEdit.value = null
            }
        }

        viewModel.postsIsRefreshing.observe(this.viewLifecycleOwner) {
            binding.swipeRefreshLayout.isRefreshing = it
        }
    }

}