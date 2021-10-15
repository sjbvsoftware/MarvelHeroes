package com.openbank.marvel_app_heroes.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.openbank.marvel_app_heroes.*
import com.openbank.marvel_app_heroes.api.RetrofitService
import com.openbank.marvel_app_heroes.data.model.Character
import com.openbank.marvel_app_heroes.databinding.FragmentCharacterListBinding
import com.openbank.marvel_app_heroes.view.adapter.CharactersAdapter
import com.openbank.marvel_app_heroes.view.viewmodel.MainViewModel
import com.openbank.marvel_app_heroes.view.viewmodel.MyViewModelFactory

class CharacterList : Fragment() {
    private var mCharacterList: List<Character>? = null
    private var adapter: CharactersAdapter? = null
    private var mCtx: Context? = null
    private val retrofitService = RetrofitService.getInstance()
    private var viewModel: MainViewModel? = null
    private var mDataBinding: FragmentCharacterListBinding? = null

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {

        if (mDataBinding == null)
            mDataBinding = FragmentCharacterListBinding.inflate(layoutInflater)

        // ViewModel
        if (viewModel == null) {
            viewModel =
                ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                    MainViewModel::class.java
                )

            adapter = CharactersAdapter(this, this)
            mDataBinding!!.characterList.adapter = adapter

            initObservers()
            initListeners()

            viewModel!!.getCharacters(0, 100)
        }

        return mDataBinding!!.root
    }

    private fun initObservers() {
        viewModel!!.characterList.observe(viewLifecycleOwner, {
                mCharacterList = it?.toMutableList()

                if(mCharacterList?.size!! > adapter?.itemCount!!){
                    adapter!!.setCharacterList(it)
                }

                mDataBinding!!.loadingLayout.visibility = View.GONE
                mDataBinding!!.retryLayout.visibility = View.GONE
        })

        viewModel!!.errorMessage.observe(viewLifecycleOwner, Observer {
            mDataBinding!!.loadingLayout.visibility = View.GONE
            mDataBinding!!.retryLayout.visibility = View.VISIBLE
        })
    }

    private fun initListeners() {
        mDataBinding!!.retryButton.setOnClickListener {
            mDataBinding!!.retryLayout.visibility = View.GONE
            mDataBinding!!.loadingLayout.visibility = View.VISIBLE

            viewModel!!.getCharacters(adapter!!.itemCount, 100)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCtx = context
    }

    /**
     * function to open the character detail fragment
     *
     * @param position Character list position
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun openCharacterDetailFragment(
        position: Int,
        sharedCard: View?,
        sharedImage: View?
    ) {
        if (mCtx is MainActivity) {
            viewModel?.getCharacters()

            val character = adapter?.characters?.get(position)
            val characterDetail = CharacterDetail()
            val bundle = Bundle()
            bundle.putString("transitionCardName", "transitionCard$position")
            bundle.putString("transitionImageName", "transitionImage$position")

            bundle.putSerializable("character", character)
            characterDetail.arguments = bundle
            (context as MainActivity).showFragmentWithTransition(
                this,
                characterDetail,
                "characterDetail",
                sharedCard,
                sharedImage
            )
        }
    }

}