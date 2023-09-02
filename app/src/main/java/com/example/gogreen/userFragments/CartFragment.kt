package com.example.gogreen.userFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.gogreen.R
import com.example.gogreen.databinding.FragmentCartBinding
import com.example.gogreen.roomdb.AppDatabase
import com.example.gogreen.userAdapter.CartAdapter


class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var list: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater)

        val dao = AppDatabase.getInstance(requireContext()).productDao()

        list = ArrayList()

        dao.getAllProducts().observe(viewLifecycleOwner) {
            binding.cartRecycler.adapter = CartAdapter(requireContext(), it)

            list.clear()
            for (data in it) {
                list.add(data.productId)
            }

            // totalCost(it)
        }

        return binding.root
    }
}
