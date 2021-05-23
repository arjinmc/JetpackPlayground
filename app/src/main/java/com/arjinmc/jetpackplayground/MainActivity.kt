package com.arjinmc.jetpackplayground

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewAdapter
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewMultipleTypeProcessor
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewSingleTypeProcessor
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewViewHolder
import com.arjinmc.expandrecyclerview.style.RecyclerViewStyleHelper
import com.arjinmc.jetpackplayground.architecture.RoomActivity
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.recyclerviewdecoration.RecyclerViewLinearItemDecoration
import com.arjinmc.recyclerviewdecoration.RecyclerViewStickyHeadItemDecoration
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.KClass

class MainActivity : BasicActivity() {

    private lateinit var mRvOptions: RecyclerView
    private lateinit var mOptionsAdapter: RecyclerViewAdapter<String>
    private lateinit var mOptionList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun initView() {
        mRvOptions = findViewById(R.id.rv_options)
        RecyclerViewStyleHelper.toLinearLayout(mRvOptions, RecyclerView.VERTICAL)
        mRvOptions.addItemDecoration(
            RecyclerViewLinearItemDecoration.Builder(getContext())
                .thickness(2).color(Color.DKGRAY).create()
        )
        mRvOptions.addItemDecoration(RecyclerViewStickyHeadItemDecoration.Builder().create())
    }

    override fun initListener() {

    }

    override fun initData() {
        mOptionList =
            getContext().resources.getStringArray(R.array.options).asList()
        mOptionsAdapter = RecyclerViewAdapter(
            getContext(),
            mOptionList,
            intArrayOf(R.layout.item_main_optons_group, R.layout.item_main_optons),
            object : RecyclerViewMultipleTypeProcessor<String>() {
                override fun onBindViewHolder(
                    holder: RecyclerViewViewHolder?,
                    position: Int,
                    optionName: String?
                ) {
                    val tvOption = holder!!.getView<TextView>(R.id.tv_option_name)
                    tvOption.text = optionName

                    holder.itemView.setOnClickListener {
                        if (optionName != null) {
                            goActivity(optionName)
                        }
                    }
                }

                override fun getItemViewType(position: Int): Int {
                    when (mOptionList[position]) {
                        getString(R.string.architecture) -> return 0
                        getString(R.string.ui) -> return 0
                        getString(R.string.behavior) -> return 0
                    }
                    return 1
                }
            })

        mRvOptions.adapter = mOptionsAdapter
    }

    private fun goActivity(optionName: String) {
        when (optionName) {
            getString(R.string.arch_room) ->
                startActivity(RoomActivity::class.java)
        }
    }

    private fun startActivity(clz: Class<*>) {
        startActivity(
            Intent(getContext(), clz)
        )
    }
}