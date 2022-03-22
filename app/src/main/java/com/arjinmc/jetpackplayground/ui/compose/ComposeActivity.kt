package com.arjinmc.jetpackplayground.ui.compose

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
import com.arjinmc.jetpackplayground.ui.compose.basic.*
import com.arjinmc.jetpackplayground.util.IntentUtil
import com.arjinmc.recyclerviewdecoration.RecyclerViewLinearItemDecoration

/**
 * Created by Eminem Lo on 3/7/22
 * email: arjinmc@hotmail.com
 */
class ComposeActivity : BasicActivity() {

    private val binding by lazy { ActCommonListBinding.inflate(layoutInflater) }

    private val mOptions = mutableListOf(
        R.string.compose_basic,
        R.string.compose_login,
        R.string.compose_listview,
        R.string.compose_grid,
        R.string.compose_scaffold,
        R.string.compose_constrain_layout,
        R.string.compose_theme,
        R.string.compose_canvas,
        R.string.compose_animation,
        R.string.compose_use_view,
        R.string.compose_view_use_compose,
        R.string.compose_drawer_menu,
        R.string.compose_tab_layout,
        R.string.compose_pure_compose_architecture,
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

        setTitle(R.string.ui_compose)

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
                R.string.compose_basic -> IntentUtil.startActivity(
                    getContext(),
                    ComposeBasicActivity::class.java
                )
                R.string.compose_login -> IntentUtil.startActivity(
                    getContext(),
                    ComposeLoginActivity::class.java
                )
                R.string.compose_listview -> IntentUtil.startActivity(
                    getContext(),
                    ComposeListViewActivity::class.java
                )

                R.string.compose_grid -> IntentUtil.startActivity(
                    getContext(),
                    ComposeGridViewActivity::class.java
                )

                R.string.compose_scaffold -> IntentUtil.startActivity(
                    getContext(),
                    ComposeScaffoldActivity::class.java
                )

                R.string.compose_drawer_menu -> IntentUtil.startActivity(
                    getContext(),
                    ComposeDrawerMenuActivity::class.java
                )
            }
        }
        binding.rvOptions.adapter = mAdapter
    }
}