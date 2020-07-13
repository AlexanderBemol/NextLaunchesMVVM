package com.nordokod.nextlaunches.ui.launches

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nordokod.nextlaunches.R
import kotlinx.android.synthetic.main.launches_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class LaunchesFragment : Fragment() {
    private val viewModel by viewModel<LaunchesViewModel>()
    private lateinit var launchesRVAdapter : LaunchesRVAdapter

    companion object {
        fun newInstance(): LaunchesFragment {
            return LaunchesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.launches_fragment, container, false)
    }


    override fun onStart() {
        super.onStart()
        viewModel.getLaunches()


        viewModel.launchesList.observe(viewLifecycleOwner,
            Observer { launchesList ->
                launchesRVAdapter = LaunchesRVAdapter(launchesList)
                rv_launches_list.adapter = launchesRVAdapter
                rv_launches_list.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            })
        viewModel.error.observe(viewLifecycleOwner,
            Observer { e ->
                Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
            })

    }



}