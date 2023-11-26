package com.example.hilttestapp.model.networking

import com.example.hilttestapp.model.networking.idrequest.RootID
import retrofit2.Response
import javax.inject.Inject

class Repository  @Inject constructor(private  val heroApi: HeroApi ) {


    suspend  fun retriveList(heroName:String):Response<Root>{

        return heroApi.searchHeroByName(heroName)
    }

    suspend fun retrieveHeroById(id :Int):Response<RootID>{


        return heroApi.searchHeroByID(id.toString())

    }

}