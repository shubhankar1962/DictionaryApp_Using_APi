package com.example.dictionaryappkt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryappkt.databinding.DefinationRowItemsBinding
import com.example.dictionaryappkt.model.Meaning

class MeaningAdapter(private var meaningList:List<Meaning>) : RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder>() {

    class MeaningViewHolder(private val binding:DefinationRowItemsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(meaning: Meaning)
        {
            //bind all the meanings
            binding.partOfSpeech.text = meaning.partOfSpeech
            binding.definationId.text = meaning.definitions.joinToString("\n\n") {
                var currentIndex  = meaning.definitions.indexOf(it)
                (currentIndex+1).toString()+"."+it.definition.toString()
            }

            if(meaning.synonyms.isEmpty())
            {
                binding.synonyms.visibility = View.GONE
                binding.synonymswords.visibility = View.GONE
            }else{
                binding.synonyms.visibility = View.VISIBLE
                binding.synonymswords.visibility = View.VISIBLE
                binding.synonymswords.text = meaning.synonyms.joinToString (" , ")
            }

            if(meaning.antonyms.isEmpty())
            {
                binding.antonyms.visibility = View.GONE
                binding.antonymswords.visibility = View.GONE
            }else{
                binding.antonyms.visibility = View.VISIBLE
                binding.antonymswords.visibility = View.VISIBLE
                binding.antonymswords.text = meaning.synonyms.joinToString (" , ")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        val binding = DefinationRowItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MeaningViewHolder(binding)
    }
    fun updateNewMeaning(newMeaningList:List<Meaning>)
    {
        meaningList = newMeaningList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return meaningList.size
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        holder.bind(meaningList[position])
    }
}