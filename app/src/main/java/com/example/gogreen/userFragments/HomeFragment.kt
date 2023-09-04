package com.example.gogreen.userFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.codebyashish.autoimageslider.Enums.ImageAnimationTypes
import com.codebyashish.autoimageslider.Enums.ImageScaleType
import com.codebyashish.autoimageslider.Models.ImageSlidesModel
import com.example.gogreen.R

import com.example.gogreen.admin_Model.category_Model
import com.example.gogreen.databinding.FragmentHomeBinding
import com.example.gogreen.userAdapter.CategoryAdapter
import com.example.gogreen.userAdapter.ProductuserAdapter
import com.example.gogreen.user_Model.addProductuserModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase



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


     /* val preferences = requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)
        if (preferences.getBoolean("isCart",false))
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)*/


        val autoImageSlider = binding.slider
        val autoImageList : ArrayList<ImageSlidesModel> = ArrayList()
        val profile = view?.findViewById<ImageView>(R.id.profile)

        autoImageList.add(ImageSlidesModel("https://picsum.photos/id/237/200/300", ""))
        autoImageList.add(ImageSlidesModel("https://picsum.photos/id/238/200/300", ""))
        autoImageList.add(ImageSlidesModel("https://picsum.photos/id/239/200/300", ""))
        profile?.setOnClickListener{
            val ProfileFragment = ProfileFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.homeFragment, ProfileFragment)
            transaction.commit()
        }

        autoImageSlider.setImageList(autoImageList, ImageScaleType.CENTER_CROP)

        autoImageSlider.setSlideAnimation(ImageAnimationTypes.DEPTH_SLIDE)

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


