package com.arjinmc.jetpackplayground.architecture.room

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewAdapter
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewSingleTypeProcessor
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewViewHolder
import com.arjinmc.expandrecyclerview.style.RecyclerViewStyleHelper
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActCommonListBinding
import com.arjinmc.jetpackplayground.util.IntentUtil
import com.arjinmc.recyclerviewdecoration.RecyclerViewLinearItemDecoration

/**
 * Created by Eminem Lo on 12/9/21
 * email: arjinmc@hotmail.com
 */
class RoomActivity : BasicActivity() {

    private val binding by lazy { ActCommonListBinding.inflate(layoutInflater) }
    private val mOptions = mutableListOf(
        R.string.room_default,
        R.string.room_rxjava,
    )

    private lateinit var mAdapter: RecyclerViewAdapter<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    override fun initView() {

        RecyclerViewStyleHelper.toLinearLayout(binding.rvOptions, RecyclerView.VERTICAL)
        binding.rvOptions.addItemDecoration(
            RecyclerViewLinearItemDecoration.Builder(getContext())
                .thickness(2).color(Color.DKGRAY).create()
        )
    }

    override fun initListener() {
    }

    override fun initData() {

        mAdapter = RecyclerViewAdapter(
            getContext(),
            mOptions,
            R.layout.item_main_optons,
            object : RecyclerViewSingleTypeProcessor<Int>() {
                override fun onBindViewHolder(
                    holder: RecyclerViewViewHolder?,
                    position: Int,
                    resId: Int
                ) {
                    val tvTitle = holder?.itemView as TextView
                    tvTitle.setText(resId)
                }
            })

        mAdapter.setOnItemClickListener {
            when (mOptions[it]) {
                R.string.room_default -> IntentUtil.startActivity(
                    getContext(),
                    RoomDefaultActivity::class.java
                )
//                R.string.room_rxjava -> IntentUtil.startActivity(
//                    getContext(),
//                    RoomDefaultActivity::class.java
//                )
            }
        }
        binding.rvOptions.adapter = mAdapter
    }
}