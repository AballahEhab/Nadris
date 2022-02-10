package com.example.android.nadris.ui.posts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.databinding.PostPageFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class post_pageFragment : Fragment() {


    val viewModel: PostPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
//        viewModel = ViewModelProvider(this).get(PostPageViewModel::class.java)
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
        viewModel.navigate_to_add_post.observe(this.viewLifecycleOwner) {
            if (it) {
                this.findNavController()
                    .navigate(post_pageFragmentDirections.actionPostFragmentToAddPostFragment())
                viewModel.navigate_to_add_post_done()
            }
        }

        val user = NadrisApplication.instance!!.userData

//        user?.let { viewModel.getPosts(it.Token) }



        return bindigin.root}


}