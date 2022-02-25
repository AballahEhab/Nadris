package com.example.android.nadris.ui.studentActivity.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nadris.NadrisApplication
import com.example.android.nadris.R
import com.example.android.nadris.database.models.DatabasePost
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
        val bindigin = FragmentPostPageBinding.inflate(inflater)
        bindigin.lifecycleOwner = this.viewLifecycleOwner
        bindigin.postViewModle = viewModel

        val userData = NadrisApplication.userData
        userData?.Token?.let { it1 -> viewModel.getPosts(it1) }
        lateinit var databasePosts: List<DatabasePost>
//        var posts=ArrayList<dataRvPost>()
//        posts.add(dataRvPost(R.drawable.ic_google, "عبدالله غراب", "الفيزياء", "إزاي اقدر اعرف المفعول لاجله"))
//        posts.add(dataRvPost(R.drawable.ic_google, "عبدالله غراب", "الفيزياء", "إزاي اقدر اعرف المفعول لاجله"))
//        posts.add(dataRvPost(R.drawable.ic_google, "عبدالله غراب", "الفيزياء", "إزاي اقدر اعرف المفعول لاجله"))
//        posts.add(dataRvPost(R.drawable.ic_google, "عبدالله غراب", "الفيزياء", "إزاي اقدر اعرف المفعول لاجله"))
//        posts.add(dataRvPost(R.drawable.ic_google, "عبدالله غراب", "الفيزياء", "إزاي اقدر اعرف المفعول لاجله"))
//        posts.add(dataRvPost(R.drawable.ic_google, "عبدالله غراب", "الفيزياء", "إزاي اقدر اعرف المفعول لاجله"))

        viewModel.postsList.observe(viewLifecycleOwner) {
            databasePosts = it
            bindigin.recyclerView.adapter = customAdapter(databasePosts)
        }
        bindigin.recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)



        viewModel.navigate_to_add_post.observe(this.viewLifecycleOwner) {
            if (it) {
                this.findNavController()
                    .navigate(PostPageFragmentDirections.actionNavigationPostsToAddPostFragment())
                viewModel.navigate_to_add_post_done()
            }
        }

//        val user = NadrisApplication.instance!!.userData

//        user?.let { viewModel.getPosts(it.Token) }


        return bindigin.root
    }


}