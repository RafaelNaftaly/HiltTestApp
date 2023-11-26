package com.example.hilttestapp.model

import com.example.hilttestapp.model.networking.idrequest.Appearance
import com.example.hilttestapp.model.networking.idrequest.Biography
import com.example.hilttestapp.model.networking.idrequest.Connections
import com.example.hilttestapp.model.networking.idrequest.Work

data class HeroDetails (val name:String ,
                        val biography: Biography,
                        val image:String,
                        val appearance: Appearance,
                        val work: Work ,
                        val connections: Connections){
}