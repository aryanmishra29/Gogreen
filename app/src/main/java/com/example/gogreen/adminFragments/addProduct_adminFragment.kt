package com.example.gogreen.adminFragments

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gogreen.R
import com.example.gogreen.admin_Adapter.CategoryAdapter
import com.example.gogreen.admin_Adapter.addProductImageAdapter
import com.example.gogreen.admin_Model.category_Model
import com.example.gogreen.databinding.FragmentAddProductAdminBinding
import com.example.gogreen.databinding.FragmentProductAdminBinding
import com.example.gogreen.databinding.ImageItemBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class addProduct_adminFragment : Fragment() {
     private lateinit var binding:FragmentAddProductAdminBinding
     private lateinit var list:ArrayList<Uri>
    private lateinit var listImages:ArrayList<String>
    private lateinit var adapter :addProductImageAdapter
    private var coverImage : Uri?= null
    private lateinit var dialog:Dialog
    private lateinit var categoryList : ArrayList<String>
    private var coverImgUrl :String?=null

    private var launchGalleryActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            coverImage = it.data!!.data
            binding.productCoverImg.setImageURI(coverImage)
            binding.productCoverImg.visibility =  VISIBLE
        }
    }
    private var launchProductActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val imageUrl = it.data!!.data
            list.add(imageUrl!!)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater:LayoutInflater, container:ViewGroup?,
        savedInstanceState:Bundle?,
    ):View? {
        binding = FragmentAddProductAdminBinding.inflate(layoutInflater)


        list = ArrayList()
        listImages = ArrayList()
        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.prgress_layout)
        dialog.setCancelable(false)

       binding.selectcoverimg.setOnClickListener {
              val intent = Intent("android.intent.action.GET_CONTENT")
                intent.type = "image/*"
                launchGalleryActivity.launch(intent)
        }
        binding.prodImgButton.setOnClickListener {
            val intent = Intent("android.intent.action.GET_CONTENT")
            intent.type = "image/*"
            launchProductActivity.launch(intent)
        }
        adapter =addProductImageAdapter(list)
        binding.productImgRecyclerView.adapter = adapter

        setProductrCategory()
        return binding.root
    }
       private fun setProductrCategory(){
           categoryList = ArrayList()
           Firebase.firestore.collection("cate")
               .get().addOnSuccessListener {
                 categoryList.clear()
                   for (doc in it.documents){
                       val data = doc.toObject(category_Model::class.java)
                       categoryList.add(data!!.cate!!)
                   }
                   categoryList.add(0,"Select category")

                   val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdown_item,categoryList)
                   binding.addProductDropdown.adapter = arrayAdapter

               }
       }

    }
