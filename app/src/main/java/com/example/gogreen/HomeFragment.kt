package com.example.gogreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gogreen.admin_Adapter.addProductImageAdapter
import com.example.gogreen.admin_Model.category_Model
import com.example.gogreen.databinding.FragmentHomeBinding
import com.example.gogreen.userAdapter.CategoryAdapter
import com.example.gogreen.userAdapter.ProductuserAdapter
import com.example.gogreen.user_Model.addProductuserModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.checkerframework.checker.units.qual.C


class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        getCategories()
        getProducts()
        return binding.root
    }
    private fun getProducts() {
        val list = ArrayList<addProductuserModel>()
        Firebase.firestore.collection("Products")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents) {
                    val data = doc.toObject(addProductuserModel::class.java)
                    list.add(data!!)
                }
                binding.productRecyclerview.adapter = ProductuserAdapter(requireContext(), list)
            }
    }

    private fun getCategories() {
        val list = ArrayList<category_Model>()
        Firebase.firestore.collection("cate")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents) {
                    val data = doc.toObject(category_Model::class.java)
                    list.add(data!!)
                }
                binding.categoryRecyclerview.adapter = CategoryAdapter(requireContext(), list)
            }
    }


}