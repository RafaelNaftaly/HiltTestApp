package com.example.hilttestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


class SharedViewModel :ViewModel(){

     val heroNameLD:MutableLiveData<String> = MutableLiveData()
    fun shearchHeroName(heroName:String){

        heroNameLD.postValue(heroName)

    }

    fun getAndObserveHeroNameLD():LiveData<String>{

        return heroNameLD
    }
}