package com.arjinmc.jetpackplayground.ui.constrainlayout

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
 * Created by Eminem Lo on 10/28/21
 * email: arjinmc@hotmail.com
 */
class ConstrainLayoutActivity : BasicActivity() {

    private val binding by viewBinding(ActCommonListBinding::inflate)
    private val mOptions = mutableListOf<Int>(
        R.string.constrain_layout_basic,
        R.string.constrain_layout_always_at_the_right,
        R.string.constrain_layout_chain_style,
        R.string.constrain_layout_constrain_width,
        R.string.constrain_layout_bias,
        R.string.constrain_layout_ratios,
        R.string.constrain_layout_guideline,
        R.string.constrain_layout_barrier,
        R.string.constrain_layout_baseline,
        R.string.constrain_layout_group,
        R.string.constrain_layout_circular_position,
        R.string.constrain_layout_flow,
        R.string.constrain_layout_layer,
        R.string.constrain_layout_constrain_set
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

        setTitle(R.string.ui_constrain_layout)

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
                R.string.constrain_layout_basic -> IntentUtil.startActivity(
                    getContext(),
                    ConstrainLayoutBasicActivity::class.java
                )
                R.string.constrain_layout_always_at_the_right -> IntentUtil.startActivity(
                    getContext(),
                    ConstrainLayoutAutoRightActivity::class.java
                )

                R.string.constrain_layout_chain_style -> IntentUtil.startActivity(
                    getContext(),
                    ConstrainLayoutChainStyleActivity::class.java
                )

                R.string.constrain_layout_constrain_width -> IntentUtil.startActivity(
                    getContext(),
                    ConstrainLayoutConstrainWidthActivity::class.java
                )

                R.string.constrain_layout_bias -> IntentUtil.startActivity(
                    getContext(),
                    ConstrainLayoutBiasActivity::class.java
                )
                R.string.constrain_layout_ratios -> IntentUtil.startActivity(
                    getContext(),
                    ConstrainLayoutRatiosActivity::class.java
                )
                R.string.constrain_layout_guideline -> IntentUtil.startActivity(
                    getContext(),
                    ConstrainLayoutGuidelineActivity::class.java
                )
                R.string.constrain_layout_barrier -> IntentUtil.startActivity(
                    getContext(),
                    ConstrainLayoutBarrierActivity::class.java
                )
                R.string.constrain_layout_baseline -> IntentUtil.startActivity(
                    getContext(),
                    ConstrainLayoutBaselineActivity::class.java
                )
                R.string.constrain_layout_group -> IntentUtil.startActivity(
                    getContext(),
                    ConstrainLayoutGroupActivity::class.java
                )
                R.string.constrain_layout_circular_position -> IntentUtil.startActivity(
                    getContext(),
                    ConstrainLayoutCircularPositionActivity::class.java
                )
                R.string.constrain_layout_flow -> IntentUtil.startActivity(
                    getContext(),
                    ConstrainLayoutFlowActivity::class.java
                )

                R.string.constrain_layout_layer -> IntentUtil.startActivity(
                    getContext(),
                    ConstrainLayoutLayerActivity::class.java
                )

                R.string.constrain_layout_constrain_set -> IntentUtil.startActivity(
                    getContext(),
                    ConstrainLayoutConstrainSetActivity::class.java
                )
            }
        }
        binding.rvOptions.adapter = mAdapter
    }

}
