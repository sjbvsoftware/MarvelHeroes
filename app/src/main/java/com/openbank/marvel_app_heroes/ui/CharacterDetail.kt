package com.openbank.marvel_app_heroes.ui

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.openbank.marvel_app_heroes.R
import com.openbank.marvel_app_heroes.Utils
import com.openbank.marvel_app_heroes.data.model.Character
import com.openbank.marvel_app_heroes.databinding.FragmentCharacterDetailBinding

class CharacterDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val mDataBinding = FragmentCharacterDetailBinding.inflate(layoutInflater)

        val b = arguments
        if (b != null) {
            val transitionCardName = b.getString("transitionCardName")
            val transitionImageName = b.getString("transitionImageName")
            val character = b.getSerializable("character") as Character?

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mDataBinding.characterCard.transitionName = transitionCardName
                mDataBinding.characterImage.transitionName = transitionImageName
            }

            if (character != null) {
                if (!TextUtils.isEmpty(character.thumbnail?.path)) {

                    Glide.with(requireActivity().application)
                        .load(
                            Utils.getLandscapeXLargeURL(
                                character.thumbnail?.path,
                                character.thumbnail?.extension
                            )
                        )
                        .thumbnail(0.1f)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(mDataBinding.characterImage)

                }
                mDataBinding.characterName.text = character.name

                if(!TextUtils.isEmpty(character.description)){
                    mDataBinding.characterDescription.visibility = View.VISIBLE
                    mDataBinding.characterDescription.text = character.description

                }

                val comics = ArrayList<String>()
                for (i in 0 until character.comics?.items?.size!!) {
                    val comic = character.comics?.items!![i]
                    comic.name?.let { comics.add(it) }
                }

                val adapter = activity?.applicationContext?.let { ArrayAdapter(it, R.layout.comic_item, comics) }
                mDataBinding.characterComics.adapter = adapter

            }
        }

        return mDataBinding.root
    }
}