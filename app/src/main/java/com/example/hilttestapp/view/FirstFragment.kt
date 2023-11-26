package com.example.hilttestapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hilttestapp.R
import com.example.hilttestapp.databinding.FragmentFirstBinding
import com.example.hilttestapp.model.HeroUI
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.hilttestapp.viewmodel.MainViewModel
import com.example.hilttestapp.viewmodel.SharedViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
     val viewModel:MainViewModel by viewModels()
    val sharedViewModel :SharedViewModel by activityViewModels()
    private lateinit var heroRecyclerView :RecyclerView
    private var heroList: List<HeroUI> = listOf()
    private lateinit var heroRecyclerViewDataChanged: RecyclerViewDataChanged
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.
        inflate(inflater, container, false)


          initRecyclerView()

           viewModel.getHeroByID()

            sharedViewModel.getAndObserveHeroNameLD().observe(requireActivity(), Observer {


                viewModel.displayHeroList(it)
                val test = 0

            })

             observeHeroesListBuID()
             observeHeroList()
             observeErrorMessage()



        return binding.root

    }

    private fun observeHeroesListBuID(){

        viewModel.heroesListByID.observe(requireActivity(), Observer {

            heroRecyclerViewDataChanged.dataSetChanged(it)


        })

    }

    fun observeErrorMessage(){

        viewModel.apiResultMessage.observe(requireActivity(), Observer {

            Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
            Toast.makeText(requireContext(),"Please Enter again",Toast.LENGTH_LONG).show()

        })
    }
    fun observeHeroList(){

        viewModel.heroListLiveData.observe(requireActivity(), Observer {

            heroRecyclerViewDataChanged.dataSetChanged(it)
            Toast.makeText(requireActivity(),"Test",Toast.LENGTH_LONG).show()

        })


    }

    fun initRecyclerView(){

        heroRecyclerView = binding.heroRecyclerView
        heroRecyclerView.layoutManager = GridLayoutManager(requireActivity(),3)
        val adapter = HeroRecyclerViewAdapter(heroList,::navigateToDetailsFragment)
        heroRecyclerViewDataChanged = adapter
        heroRecyclerView.adapter = adapter


    }

    fun navigateToDetailsFragment(id:String){


        val bundle = Bundle()
        bundle.putString("id",id)
        findNavController().navigate(R.id
            .action_FirstFragment_to_SecondFragment,bundle)





    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  binding.buttonFirst.setOnClickListener {

        //}

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}