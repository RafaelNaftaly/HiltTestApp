package com.example.hilttestapp.model.reopsitory

import androidx.lifecycle.MutableLiveData
import com.example.hilttestapp.model.HeroDetails
import com.example.hilttestapp.model.networking.HeroApi
import com.example.hilttestapp.model.networking.idrequest.RootID
import retrofit2.Response
import javax.inject.Inject

class DetailsRepository @Inject constructor( private  val heroApi: HeroApi) {



    suspend fun getHeroDetailsFromAPI(id:String):Response<RootID>{



       return  heroApi.searchHeroByID(id)


    }

}