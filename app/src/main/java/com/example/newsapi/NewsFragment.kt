package com.example.newsapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.Models.News

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsRecycler: RecyclerView
    private var newsCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newsCategory = it.getString(ARG_PARAM1)
        }
        newsViewModel= ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_news, container, false)
        newsRecycler= view.findViewById(R.id.recycler_view)
        newsRecycler.layoutManager= LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (newsCategory) {
            "Politics" -> {
                newsViewModel.politicsNewsLiveData.observe(
                    viewLifecycleOwner,
                    Observer { newsItems ->
                        updateUI(newsItems)
                    })
            }
            "Sport" -> {
                newsViewModel.sportNewsLiveData.observe(
                    viewLifecycleOwner,
                    Observer { newsItems ->
                        updateUI(newsItems)
                    })
            }
            "Economic" -> {
                newsViewModel.econoimNewsLiveData.observe(
                    viewLifecycleOwner,
                    Observer { newsItems ->
                        updateUI(newsItems)
                    })

            }
            else -> null
//                newsViewModel.politicsNewsLiveData.observe(
//                    viewLifecycleOwner,
//                    Observer { newsItems ->
//                        updateUI(newsItems)
//                    })
//            }

        }
    }

    private fun updateUI(news: List<News>) {
        newsRecycler.adapter = NewsAdapter(news)
    }

    private inner class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private lateinit var news: News

        private val newsTitle= itemView.findViewById(R.id.tilte) as TextView
        private val newsDate= itemView.findViewById(R.id.date) as TextView

        fun bind(newsItems: News){
            news=newsItems
            newsTitle.text=newsItems.title
            newsDate.text=newsItems.date
        }
    }

    private inner class NewsAdapter(private val news:List<News>):RecyclerView.Adapter<NewsHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent, false)
            return NewsHolder(view)
        }

        override fun getItemCount(): Int {
            return news.size
        }

        override fun onBindViewHolder(holder: NewsHolder, position: Int) {
            val newsItems = news[position]
            holder.bind(newsItems)
        }
    }

    companion object {
        fun newNewsInstance() = NewsFragment()
        fun newInstance(newsCategory: String) = NewsFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, newsCategory)

            }
        }
    }
}