/**
2 * Copyright (C), 2015-2021, XXX Limited
3 * FileName: EditFragment
4 * Author: malek
5 * Date: 11/24/21 7:55 AM
6 * Description: fragment to edit todo list item
7 * History:
8 * <author> <time> <version> <desc>
9 * Author's name Modification time Version number Description
10
 */
package com.omran.notatodolist.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.omran.notatodolist.R
import com.omran.notatodolist.databinding.FragmentEditBinding
import com.omran.notatodolist.utils.hideKeyboard
import com.omran.notatodolist.utils.shortToast
import com.omran.notatodolist.viewmodel.TodoViewModel
import com.omran.notatodolist.viewmodel.TodoViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class EditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mTodo = EditFragmentArgs.fromBundle(requireArguments()).todo
        // Inflate the layout for this fragment
        val binding = FragmentEditBinding.inflate(inflater).apply {
            todo = mTodo
        }

        val viewModelFactory = TodoViewModelFactory.getInstance(requireContext())
        val todoViewModel = ViewModelProvider(this, viewModelFactory)[TodoViewModel::class.java]

        binding.submitButton.setOnClickListener {
            val updatedTitle = binding.title.text.toString()
            val updatedDesc = binding.description.text.toString()

            if (updatedTitle.isNotBlank() && updatedDesc.isNotBlank()) {
                todoViewModel.updateTodo(mTodo!!.id, updatedTitle, updatedDesc, mTodo.checked)
                activity?.hideKeyboard()
                findNavController().popBackStack()
            } else {
                context?.shortToast(getString(R.string.fill_all_fields))
            }
        }

        return binding.root
    }

}