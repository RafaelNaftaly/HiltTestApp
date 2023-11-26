package com.example.hilttestapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.hilttestapp.R
import com.example.hilttestapp.model.HeroUI

 class HeroRecyclerViewAdapter (private var list: List<HeroUI>,
                                private val navigate : (String )-> Unit):
     RecyclerView.Adapter<HeroRecyclerViewAdapter.HeroViewHolder>()
,RecyclerViewDataChanged {

     lateinit var context:Context
         inner class HeroViewHolder(itemView: View, private val navigate : (String )-> Unit):ViewHolder(itemView){

             val textViewHeroName: TextView = itemView.findViewById(R.id.tv_hero_name)
             val textViewHeroStatus: TextView = itemView.findViewById(R.id.tv_biography)
             val imageViewHeroImage: ImageView = itemView.findViewById(R.id.iv_hero)
             lateinit var heroDetails:HeroUI


             fun bind(heroUI: HeroUI){

                 heroDetails= heroUI
                 textViewHeroName.text = heroUI.heroName
                 textViewHeroStatus.text = heroUI.heroGender
                 Glide.with(context).load(heroUI.heroImage).into(imageViewHeroImage)



             }



             init {
                 itemView.setOnClickListener({

                     navigate.invoke(heroDetails.id.toString())


                 })
             }




         }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
         val heroViewHolder = LayoutInflater.from(parent.context).
         inflate(R.layout.hero_recycler_view_item,parent,false)
         context=parent.context

         return HeroViewHolder(heroViewHolder,navigate)
     }

     fun updateHeroesList(heroList:List<HeroUI>){

         list=heroList
         notifyDataSetChanged()

     }
     override fun getItemCount(): Int {
         return  list.size
     }

     override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
         holder.bind(list.get(position))
     }

     override fun dataSetChanged(heroList: List<HeroUI>) {
         list= heroList
         notifyDataSetChanged()
     }
 }