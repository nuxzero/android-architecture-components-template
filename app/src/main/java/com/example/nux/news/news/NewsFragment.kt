package com.example.nux.news.news


import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nux.news.R
import com.example.nux.news.app.NewsApplication
import com.example.nux.news.data.models.News
import com.example.nux.news.databinding.NewsFragmentBinding
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : LifecycleFragment() {

    private var title: String? = null

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

    private lateinit var binding: NewsFragmentBinding
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            title = arguments.getString(NewsArgument.ARG_TITLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        DaggerNewsViewModelComponent.builder()
                .newsRepositoryComponent((activity.application as NewsApplication).newsRepositoryComponent)
                .build()
                .inject(this)

        newsViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(NewsViewModel::class.java)

        subscribeUi()
    }

    private fun subscribeUi() {
        newsViewModel.loadNews(title!!)
                .observe(this, Observer<News> { news ->
                    if (news != null) {
                        binding.news = news
                    }
                })
    }

    companion object {
        fun newInstance(title: String): NewsFragment {
            val fragment = NewsFragment()
            val args = Bundle()
            args.putString(NewsArgument.ARG_TITLE, title)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
