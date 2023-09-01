package com.example.gogreen.adminFragments

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gogreen.R
import com.example.gogreen.admin_Adapter.addProductImageAdapter
import com.example.gogreen.databinding.FragmentAddProductAdminBinding
import com.example.gogreen.databinding.FragmentProductAdminBinding
import com.example.gogreen.databinding.ImageItemBinding


class addProduct_adminFragment : Fragment() {
     private lateinit var binding:FragmentAddProductAdminBinding
     private lateinit var list:ArrayList<Uri>
    private lateinit var listImages:ArrayList<String>
    private lateinit var adapter :addProductImageAdapter
    private var coverImage : Uri?= null
    private lateinit var dialog:Dialog
    private lateinit var categoryList : ArrayList<String>
    private var coverImgUrl :String?=null

    override fun onCreateView(
        inflater:LayoutInflater, container:ViewGroup?,
        savedInstanceState:Bundle?,
    ):View? {
        binding = FragmentAddProductAdminBinding.inflate(layoutInflater)
        return binding.root
    }


    }
