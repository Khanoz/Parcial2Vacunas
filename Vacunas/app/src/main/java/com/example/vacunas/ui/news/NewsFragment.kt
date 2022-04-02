package com.example.vacunas.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vacunas.MyNewsAdapter
import com.example.vacunas.News
import com.example.vacunas.R
import com.example.vacunas.databinding.FragmentNewsBinding
import com.google.firebase.firestore.FirebaseFirestore

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NewsViewModel::class.java)

        _binding = com.example.vacunas.databinding.FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = FirebaseFirestore.getInstance()

        val recyclerView: RecyclerView? = getView()?.findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        db.collection("news").addSnapshotListener { value, error ->
            val news = value!!.toObjects(News::class.java)

            news.forEachIndexed { index, post ->
                post.uid = value.documents[index].id
            }
            val adapter = MyNewsAdapter(news, this.requireContext())
            recyclerView.adapter = adapter
        }
    }
}