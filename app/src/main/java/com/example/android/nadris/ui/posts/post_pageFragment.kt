package com.example.android.nadris.ui.posts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.R
import com.example.android.nadris.databinding.PostPageFragmentBinding

class post_pageFragment : Fragment() {

    companion object {
        fun newInstance() = post_pageFragment()
    }

    private lateinit var viewModel: PostPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this).get(PostPageViewModel::class.java)
        inflater.inflate(R.layout.post_page_fragment, container, false)
        val bindigin = PostPageFragmentBinding.inflate(inflater)
        bindigin.postViewModle = viewModel

        var posts=ArrayList<dataRvPost>()
        posts.add(dataRvPost(R.drawable.ic_google, "عبدالله غراب", "الفيزياء", "إزاي اقدر اعرف المفعول لاجله"))
        posts.add(dataRvPost(R.drawable.ic_google, "عبدالله غراب", "الفيزياء", "إزاي اقدر اعرف المفعول لاجله"))
        posts.add(dataRvPost(R.drawable.ic_google, "عبدالله غراب", "الفيزياء", "إزاي اقدر اعرف المفعول لاجله"))
        posts.add(dataRvPost(R.drawable.ic_google, "عبدالله غراب", "الفيزياء", "إزاي اقدر اعرف المفعول لاجله"))
        posts.add(dataRvPost(R.drawable.ic_google, "عبدالله غراب", "الفيزياء", "إزاي اقدر اعرف المفعول لاجله"))
        posts.add(dataRvPost(R.drawable.ic_google, "عبدالله غراب", "الفيزياء", "إزاي اقدر اعرف المفعول لاجله"))

        bindigin.recyclerView.layoutManager= LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        bindigin.recyclerView.adapter= customAdapter(posts)
        viewModel.navigate_to_add_post.observe(this.viewLifecycleOwner,{
            if (it){
                this.findNavController().navigate(post_pageFragmentDirections.actionPostFragmentToAddPostFragment())
                viewModel.navigate_to_add_post_done()
            }
        })


        return bindigin.root}


}