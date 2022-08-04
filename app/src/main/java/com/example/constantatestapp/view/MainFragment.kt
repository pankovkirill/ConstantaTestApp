package com.example.constantatestapp.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.constantatestapp.R
import com.example.constantatestapp.databinding.FragmentMainBinding
import com.example.constantatestapp.model.data.AppState
import com.example.constantatestapp.viewmodel.MainViewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val onListItemClickListener: OnItemClickListener =
        object : OnItemClickListener {
            override fun itemClick(title: String) {
                showDialog(title)
            }
        }

    private fun showDialog(title: String) {
        AlertDialog
            .Builder(context)
            .setMessage("Фильм $title был нажат")
            .create()
            .show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        viewModel.liveDataToObserve.observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.recyclerView.adapter = adapter
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                appState.data?.let {
                    if (it.items.isEmpty()) {
                        Toast.makeText(context, getString(R.string.no_data_available), Toast.LENGTH_SHORT).show()
                    } else {
                        adapter.update(it.items)
                    }
                }
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Toast.makeText(context, appState.error.message, Toast.LENGTH_SHORT).show()

            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
        }
    }
}