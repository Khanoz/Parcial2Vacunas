package com.example.vacunas.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vacunas.activities.LoginActivity
import com.example.vacunas.R
import com.example.vacunas.Utils
import com.example.vacunas.databinding.FragmentInformationBinding
import com.example.vacunas.userData
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat

class InfoFragment : Fragment() {

    private var _binding: FragmentInformationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private lateinit var dlayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInformationBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db.collection("users").document(Utils.userid)
            .addSnapshotListener { value, error ->
                val userdata = value!!.toObject(userData::class.java)
                val formatter = SimpleDateFormat("dd/MM/yyyy")
                val date: String = formatter.format(userdata!!.birthdate!!)
                val name: String =
                    userdata.name + " " + userdata.lastname_F + " " + userdata.lastname_M

                requireView().findViewById<TextView>(R.id.info_name).text = name
                requireView().findViewById<TextView>(R.id.birthdate_infotv).text = date
                requireView().findViewById<TextView>(R.id.weight_infotv).text =
                    userdata.birth_weight
                requireView().findViewById<TextView>(R.id.sex_infotv).text = userdata.gender
                requireView().findViewById<TextView>(R.id.gestal_infotv).text = userdata.edad_gestal
                requireView().findViewById<TextView>(R.id.blood_type_infotv).text =
                    userdata.blood_type
            }

        dlayout = requireView().findViewById(R.id.drawer_layout_info)
        navigationView = requireView().findViewById(R.id.nav_view_info)
        navigationView.bringToFront()
        toggle =
            ActionBarDrawerToggle(activity, dlayout, R.string.open_drawer, R.string.close_drawer)
        dlayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.logout -> {
                    Log.d("logut", "LoggedOut")
                    Utils.auth.signOut()
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                    true
                }
                R.id.new_register -> {
                    Log.d("drawerTest", "new register")
                }
                else -> {
                    true
                }
            }
            dlayout.closeDrawer(GravityCompat.START)
            true
        }
        requireView().findViewById<ImageButton>(R.id.drawer_btn).setOnClickListener {
            dlayout.openDrawer(GravityCompat.START)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}