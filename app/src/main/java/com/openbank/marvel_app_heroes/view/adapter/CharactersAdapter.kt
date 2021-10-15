package com.openbank.marvel_app_heroes.view.adapter

import android.os.Build
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.openbank.marvel_app_heroes.R
import com.openbank.marvel_app_heroes.Utils
import com.openbank.marvel_app_heroes.data.model.Character
import com.openbank.marvel_app_heroes.databinding.AdapterCharacterBinding
import com.openbank.marvel_app_heroes.ui.CharacterList

class CharactersAdapter(mCtx: CharacterList, characterListFragment: CharacterList) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {
    var mCtx = mCtx
    var characters = mutableListOf<Character>()
    private val characterListFragment: CharacterList? = characterListFragment

    fun setCharacterList(characters: List<Character>) {
        this.characters.addAll(characters)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterCharacterBinding.inflate(inflater, parent, false)
        return CharacterViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character: Character = characters!![position]
        holder.pos = position

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.binding.characterCard.transitionName = "transitionCard$position"
            holder.binding.characterImage.transitionName = "transitionImage$position"
        }

        if (!TextUtils.isEmpty(character.thumbnail?.path)) {
            var imageURL:String? = Utils.getStandardXLargeURL(character.thumbnail?.path, character.thumbnail?.extension)

            Glide.with(mCtx)
                .load(imageURL)
                .error(R.drawable.logo_marvel)
                .centerCrop()
                .into(holder.binding.characterImage)
        } else {
            holder.binding.characterImage.setImageResource(R.drawable.logo_marvel);
        }

        holder.binding.characterName.text = character.name
        holder.itemView.setOnClickListener {

            characterListFragment?.openCharacterDetailFragment(
                position,
                holder.binding.characterCard,
                holder.binding.characterImage
            )
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    class CharacterViewHolder(val binding: AdapterCharacterBinding) :RecyclerView.ViewHolder(binding.root) {
        var pos = -1
    }

}