package com.example.hilttestapp.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.hilttestapp.R
import com.example.hilttestapp.databinding.FragmentDetailsBinding
import com.example.hilttestapp.model.HeroDetails
import com.example.hilttestapp.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null

    val detailsViewModel:DetailsViewModel by viewModels()

    lateinit var heroNameTextView :TextView
    lateinit var heroGender :TextView
    lateinit var heroBiography:TextView
    lateinit var heroAppearance:TextView
    lateinit var heroConnections:TextView
    lateinit var heroImageView: ImageView
    lateinit var heroWork:TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.
        inflate(inflater, container, false)
        setHasOptionsMenu(true);
        requireActivity().closeOptionsMenu()

        arguments?.getString("id")?.let { detailsViewModel.retriveHeroDetailsFromAPI(it) }
        observeHeroDetailsLiveData()


        initView()
        return binding.root

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val item = menu.findItem(com.example.hilttestapp.R.id.action_search)
        if (item != null) item.isVisible = false
    }
    fun initView(){

        heroNameTextView = binding.tvHeroName
        heroBiography = binding.tvBiography
        heroImageView=binding.ivHero
        heroAppearance = binding.tvAppearance
        heroConnections = binding.tvConnection
        heroWork=binding.tvWork



    }
    fun observeHeroDetailsLiveData(){

        detailsViewModel.heroDetailsLiveData.observe(requireActivity(), Observer {

                   buildDetailsScreen(it!!)


        })



    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // binding.buttonSecond.setOnClickListener {
           // findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
       // }
    }

    fun buildDetailsScreen(details: HeroDetails){

        heroNameTextView.text =details.name
        Glide.with(requireActivity()).load(details.image).into(heroImageView)
        heroBiography.text=details.biography.publisher
        heroAppearance.text = details.appearance.gender
        heroConnections.text = details.connections.relatives
        heroWork.text= details.work.occupation



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}