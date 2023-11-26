package com.example.hilttestapp.model.networking



import com.example.hilttestapp.model.networking.idrequest.RootID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroApi {

    @GET("api/10158505242635943/search/{name}")
    suspend fun searchHeroByName(@Path("name") name :String):Response<Root>


    @GET("api/10158505242635943/{id}")
    suspend fun searchHeroByID(@Path("id") id :String):Response<RootID>

}