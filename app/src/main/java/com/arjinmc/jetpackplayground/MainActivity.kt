package com.arjinmc.jetpackplayground

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewAdapter
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewMultipleTypeProcessor
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewViewHolder
import com.arjinmc.expandrecyclerview.style.RecyclerViewStyleHelper
import com.arjinmc.jetpackplayground.architecture.DataStoreActivity
import com.arjinmc.jetpackplayground.architecture.ViewBindingActivity
import com.arjinmc.jetpackplayground.architecture.coroutines.CoroutinesActivity
import com.arjinmc.jetpackplayground.architecture.databinding.DataBindingActivity
import com.arjinmc.jetpackplayground.architecture.lifecycle.LifecycleActivity
import com.arjinmc.jetpackplayground.architecture.livedata.LiveDataActivity
import com.arjinmc.jetpackplayground.architecture.room.RoomActivity
import com.arjinmc.jetpackplayground.architecture.viewmodel.ViewModelActivity
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.material.BottomAppBarActivity
import com.arjinmc.jetpackplayground.material.RangeSliderActivity
import com.arjinmc.jetpackplayground.material.drawable.DrawableActivity
import com.arjinmc.jetpackplayground.material.textinputlayout.TextInputLayoutActivity
import com.arjinmc.jetpackplayground.ui.SlidingPaneLayoutActivity
import com.arjinmc.jetpackplayground.ui.compose.ComposeActivity
import com.arjinmc.jetpackplayground.ui.constrainlayout.ConstrainLayoutActivity
import com.arjinmc.jetpackplayground.ui.motionlayout.MotionLayoutActivity
import com.arjinmc.jetpackplayground.util.IntentUtil
import com.arjinmc.recyclerviewdecoration.RecyclerViewLinearItemDecoration
import com.arjinmc.recyclerviewdecoration.RecyclerViewStickyHeadItemDecoration

/**
 * Created by Eminem Lo on 21/5/2021.
 * email: arjinmc@hotmail.com
 */
class MainActivity : BasicActivity() {

    private lateinit var mRvOptions: RecyclerView
    private lateinit var mOptionsAdapter: RecyclerViewAdapter<String>
    private lateinit var mOptionList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
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
                        getString(R.string.material) -> return 0
                    }
                    return 1
                }
            })

        mRvOptions.adapter = mOptionsAdapter
    }

    private fun goActivity(optionName: String) {
        when (optionName) {
            getString(R.string.arch_room) -> startActivity(RoomActivity::class.java)
            getString(R.string.arch_view_binding) -> startActivity(ViewBindingActivity::class.java)
            getString(R.string.arch_data_store) -> startActivity(DataStoreActivity::class.java)
            getString(R.string.ui_constrain_layout) -> startActivity(ConstrainLayoutActivity::class.java)
            getString(R.string.ui_motion_layout) -> startActivity(MotionLayoutActivity::class.java)
            getString(R.string.material_range_slider) -> startActivity(RangeSliderActivity::class.java)
            getString(R.string.material_text_input_layout) -> startActivity(TextInputLayoutActivity::class.java)
            getString(R.string.arch_view_model) -> startActivity(ViewModelActivity::class.java)
            getString(R.string.arch_live_data) -> startActivity(LiveDataActivity::class.java)
            getString(R.string.arch_lifecycle) -> startActivity(LifecycleActivity::class.java)
            getString(R.string.arch_data_binding) -> startActivity(DataBindingActivity::class.java)
            getString(R.string.arch_coroutine) -> startActivity(CoroutinesActivity::class.java)
            getString(R.string.material_bottom_app_bar) -> startActivity(BottomAppBarActivity::class.java)
            getString(R.string.ui_sliding_pane_layout) -> startActivity(SlidingPaneLayoutActivity::class.java)
            getString(R.string.ui_compose) -> startActivity(ComposeActivity::class.java)
            getString(R.string.material_drawable) -> startActivity(DrawableActivity::class.java)
        }
    }

    private fun startActivity(clz: Class<*>) {
        IntentUtil.startActivity(getContext(), clz)
    }
}