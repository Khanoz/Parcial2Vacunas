package com.example.vacunas.ui.vaccines

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vacunas.MyNewsAdapter
import com.example.vacunas.MyVaccineAdapter
import com.example.vacunas.R
import com.example.vacunas.Vaccine
import com.example.vacunas.databinding.FragmentVaccineBinding
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class VaccineFragment : Fragment() {

    private var _binding: FragmentVaccineBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentVaccineBinding.inflate(inflater, container, false)
        return binding.root
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

        db.collection("users").document("user1")
            .addSnapshotListener { value, error ->
                val ref: ArrayList<DocumentReference> = value!!
                    .get("userVaccines") as ArrayList<DocumentReference>
                val list: MutableList<Vaccine> = mutableListOf()

                ref.forEach {
                    it.addSnapshotListener { value, error ->
                        val vac_title = value!!.get("title")
                        list.add(Vaccine(vac_title.toString()))
                        if(list.size >= ref.size){
                            val adapter = MyVaccineAdapter(list)
                            recyclerView.adapter = adapter
                        }
                    }
                }
            }
        /*db.collection("users").addSnapshotListener { value, error ->
            val news = value!!.toObjects(News::class.java)

            news.forEachIndexed { index, publicacion ->
                publicacion.uid = value.documents[index].id
            }
            val adapter = MyAdapter(news, this.requireContext())
            recyclerView.adapter = adapter
        }*/
    }

}