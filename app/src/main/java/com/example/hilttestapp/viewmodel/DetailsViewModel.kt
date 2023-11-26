package com.example.hilttestapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilttestapp.model.HeroDetails
import com.example.hilttestapp.model.networking.Appearance
import com.example.hilttestapp.model.networking.Biography
import com.example.hilttestapp.model.networking.Connections
import com.example.hilttestapp.model.networking.Work
import com.example.hilttestapp.model.networking.idrequest.RootID
import com.example.hilttestapp.model.reopsitory.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor
    (private val detailsRepository: DetailsRepository):ViewModel() {

    val heroDetailsLiveData: MutableLiveData<HeroDetails?> = MutableLiveData()

    fun retriveHeroDetailsFromAPI(id:String){


        viewModelScope.launch(Dispatchers.IO){


            val responseHeroDetails = detailsRepository.getHeroDetailsFromAPI(id)
            val response = responseHeroDetails.body()?.response

            if(responseHeroDetails.isSuccessful && response!="error"){

                val rootID = responseHeroDetails.body()

                val heroDetails = rootID?.let { apiResponseToHeroDetailsMapper(it) }

                heroDetailsLiveData.postValue(heroDetails)


            }else{




            }



        }




    }

    fun apiResponseToHeroDetailsMapper(rootID: RootID):HeroDetails{

        val heroName = rootID.name
        val heroImage = rootID.image.url
        val biography = rootID.biography
        val appearance=rootID.appearance
        val work= rootID.work
        val connections = rootID.connections

        val heroDetails = HeroDetails(heroName,
            biography,
            heroImage,
            appearance,
            work,
            connections)


        return  heroDetails

    }

}