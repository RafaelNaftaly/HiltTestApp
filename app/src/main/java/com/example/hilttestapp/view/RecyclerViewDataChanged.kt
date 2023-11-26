package com.example.hilttestapp.view

import com.example.hilttestapp.model.HeroUI

interface RecyclerViewDataChanged {

       fun dataSetChanged(heroList: List<HeroUI>)

}