package com.example.vacunas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.vacunas.R


class RecoverAccountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recover_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getView()?.findViewById<ImageButton>(R.id.topbar_exit_x)
            ?.setOnClickListener {
                activity?.findViewById<ConstraintLayout>(R.id.login_container)?.visibility = View.VISIBLE
                activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
            }
    }


}