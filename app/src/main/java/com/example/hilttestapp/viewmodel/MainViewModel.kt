package com.example.hilttestapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.hilttestapp.model.HeroUI
import com.example.hilttestapp.model.networking.Repository
import com.example.hilttestapp.model.networking.Results
import com.example.hilttestapp.model.networking.idrequest.HeroByID
import com.example.hilttestapp.model.networking.idrequest.RootID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor( private  val repository: Repository):ViewModel(){

    val heroListLiveData:MutableLiveData<List<HeroUI>> = MutableLiveData()
    val apiResultMessage:MutableLiveData<String> = MutableLiveData()
    val heroesListByID:MutableLiveData<List<HeroUI>> = MutableLiveData()

    fun displayHeroList(heroName:String){


        viewModelScope.launch(Dispatchers.IO){

            val rootResponse =repository.retriveList(heroName)
            val response =  rootResponse.body()?.response

            if(rootResponse.isSuccessful && response!="error"){

                val results = rootResponse.body()?.results
                val heroUIList = mapNetworkToUI(results!!)
              heroListLiveData.postValue(heroUIList)
            } else {

                apiResultMessage.postValue("You entered a wrong name")
                Log.i("APIHERO", rootResponse.body()?.response.toString())

            }

        }


    }


    fun mapRootIDtoHeroByID(rootID:RootID):HeroUI{


        val heroName = rootID.name
        val heroGender = rootID.appearance.gender
        val heroID = rootID.id
        val heroImage = rootID.image.url

        val heroByID = HeroUI(heroID.toInt(),heroName,heroGender,heroImage)

        return heroByID

    }
    fun getHeroByID(){


        val heroesArrayList :ArrayList<HeroUI> = arrayListOf()


        viewModelScope.launch(Dispatchers.IO){
        for (i in 0 until 3){



                val heroID =  Random.nextInt(1, 500)
                val rootResponse =repository.retrieveHeroById(heroID)
                val response =  rootResponse.body()?.response

                if(rootResponse.isSuccessful && response!="error"){

                    val rootID = rootResponse.body()
                    val heroUI = mapRootIDtoHeroByID(rootID!!)
                     heroesArrayList.add(heroUI)
                } else {

                    apiResultMessage.postValue("You entered a wrong name")
                    Log.i("APIHERO", rootResponse.body()?.response.toString())

                }

            }


            heroesListByID.postValue(heroesArrayList)

            val test = 0

        }



    }
    fun mapNetworkToUI(resultsList: List<Results>):List<HeroUI>{


        val heroUIArrayList:ArrayList<HeroUI> = arrayListOf()

        for(i in 0 until resultsList.size){

            val heroID = resultsList.get(i).id
            val heroName = resultsList.get(i).name
            val heroGender = resultsList.get(i).appearance.gender
            val heroImage = resultsList.get(i).image.url
            val heroUI = HeroUI(heroID,heroName,heroGender!!,heroImage!!)
            heroUIArrayList.add(heroUI)
        }


        return heroUIArrayList.toList()

    }
}