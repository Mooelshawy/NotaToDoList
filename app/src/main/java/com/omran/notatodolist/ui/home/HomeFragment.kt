/**
2 * Copyright (C), 2015-2021, XXX Limited
3 * FileName: HomeFragment
4 * Author: malek
5 * Date: 11/24/21 7:57 AM
6 * Description: home screen
7 * History:
8 * <author> <time> <version> <desc>
9 * Author's name Modification time Version number Description
10
 */
package com.omran.notatodolist.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.GridLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.omran.notatodolist.R
import com.omran.notatodolist.adapter.ListAdapter
import com.omran.notatodolist.databinding.FragmentHomeBinding
import com.omran.notatodolist.utils.hideKeyboard
import com.omran.notatodolist.utils.shortToast
import com.omran.notatodolist.viewmodel.TodoViewModel
import com.omran.notatodolist.viewmodel.TodoViewModelFactory
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    private lateinit var viewModel: TodoViewModel

    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)
        setHasOptionsMenu(true)
        activity?.hideKeyboard()
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = TodoViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)[TodoViewModel::class.java]

        mLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = ListAdapter(viewModel)

        val mDate = Calendar.getInstance().time
        val formatter = SimpleDateFormat("EEEE, MMM dd yyyy")
        val currentDate = formatter.format(mDate)

        binding.apply {
            date.text = currentDate
            addTaskBtn.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_addFragment)
            )
        }

        // Setup RecyclerView
        setupRecyclerview()

        viewModel.getAllTodos().observe(viewLifecycleOwner, { list ->
            adapter.setData(list)

            if (list.isEmpty()) {
                binding.noDataImage.visibility = View.VISIBLE
                binding.noDataText.visibility = View.VISIBLE
                binding.totalTask.text = "0"
            } else {
                binding.noDataImage.visibility = View.GONE
                binding.noDataText.visibility = View.GONE
                binding.totalTask.text = list.size.toString()
            }
        })

        viewModel.getAllCompleted().observe(viewLifecycleOwner, { list ->
            if (list.isNotEmpty()) binding.completed.text = list.size.toString()
            else binding.completed.text = "0"
        })
    }

    private fun setupRecyclerview() {
        with(binding.rvTodo) {
            adapter = adapter
            layoutManager = StaggeredGridLayoutManager(2, VERTICAL)
            itemAnimator = FadeInUpAnimator().apply {
                addDuration = 100
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val delete: String

        when (item.itemId) {
            R.id.delete_selected -> {
                delete = "selected"
                showDialog(delete)
            }

            R.id.delete_all -> {
                delete = "all"
                showDialog(delete)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showDialog(delete: String) {
        when (delete) {
            "selected" -> {
                AlertDialog.Builder(requireContext(), R.style.MyDialogTheme).apply {
                    setTitle(getString(R.string.delete_checked_lists))
                    setMessage(getString(R.string.all_delete_are_you_sure))
                    setPositiveButton(getString(R.string.yes)) { _, _ ->
                        viewModel.deleteSelected()

                        context.shortToast(getString(R.string.toast_all_deleted))
                    }
                    setNegativeButton(getString(R.string.no)) { dialog, _ ->
                        dialog.cancel()
                    }
                }.create().show()
            }
            "all" -> {
                AlertDialog.Builder(requireContext(), R.style.MyDialogTheme).apply {
                    setTitle(getString(R.string.clear_lists))
                    setMessage(getString(R.string.all_list_deleted_warning))
                    setPositiveButton(getString(R.string.yes)) { _, _ ->
                        viewModel.clearTodos()

                        context.shortToast(getString(R.string.all_deleted_add_todo))
                    }
                    setNegativeButton(getString(R.string.no)) { dialog, _ ->
                        dialog.cancel()
                    }
                }.create().show()
            }
        }
    }

}
