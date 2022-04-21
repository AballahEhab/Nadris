package com.example.android.nadris.ui.studentActivity.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.FragmentPostPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostPageFragment : Fragment() {
    val viewModel: PostPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        inflater.inflate(R.layout.fragment_post_page, container, false)
        val binding = FragmentPostPageBinding.inflate(inflater)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.postViewModle = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        viewModel.getPosts()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val adapter = CustomAdapter(viewModel)
        binding.recyclerView.adapter = adapter
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
            it?.let{ destinationProfileEmail ->
                this.findNavController()
                    .navigate(PostPageFragmentDirections
                        .actionPostsFragmentToPublicProfileFragment(destinationProfileEmail))
                viewModel.navigationToPublicProfileDone()
            }
        }


        return binding.root
    }


}