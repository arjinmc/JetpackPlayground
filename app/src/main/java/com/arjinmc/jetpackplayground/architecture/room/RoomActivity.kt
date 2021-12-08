package com.arjinmc.jetpackplayground.architecture.room

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewAdapter
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewSingleTypeProcessor
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewViewHolder
import com.arjinmc.expandrecyclerview.style.RecyclerViewStyleHelper
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActRoomBinding
import com.arjinmc.recyclerviewdecoration.RecyclerViewLinearItemDecoration

/**
 *
 * Created by Eminem Lo on 21/5/2021.
 * email: arjinmc@hotmail.com
 */
class RoomActivity : BasicActivity() {

    private val binding by lazy { ActRoomBinding.inflate(layoutInflater) }

    private var mOptionType: OptionType? = null
    private lateinit var mDataAdapter: RecyclerViewAdapter<RoomDataBean>
    private lateinit var mDataList: MutableList<RoomDataBean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    override fun initView() {

        RecyclerViewStyleHelper.toLinearLayout(binding.rvData, RecyclerView.VERTICAL)
        binding.rvData.addItemDecoration(
            RecyclerViewLinearItemDecoration.Builder(getContext())
                .thickness(2)
                .color(Color.GRAY)
                .create()
        )
    }

    override fun initListener() {
        binding.rgOptions.setOnCheckedChangeListener { radioGroup, id ->
            when (id) {
                R.id.rb_add -> {
                    switchOption(OptionType.ADD)
                }
                R.id.rb_edit -> {
                    switchOption(OptionType.EDIT)
                }
                R.id.rb_delete -> {
                    switchOption(OptionType.DELETE)
                }
            }
        }

        binding.btnOption.setOnClickListener {
            Thread {
                run {
                    when (mOptionType) {
                        OptionType.ADD -> add()
                        OptionType.EDIT -> edit()
                        OptionType.DELETE -> delete()
                    }
                    getDataList()
                }
            }.start()
        }
    }

    override fun initData() {
        mDataList = mutableListOf()
        mDataAdapter = RecyclerViewAdapter(
            getContext(),
            mDataList,
            R.layout.item_room_list,
            object : RecyclerViewSingleTypeProcessor<RoomDataBean>() {
                override fun onBindViewHolder(
                    holder: RecyclerViewViewHolder?,
                    position: Int,
                    roomDataBean: RoomDataBean?
                ) {
                    val tvId = holder?.getView<TextView>(R.id.tv_id)
                    val tvContent = holder?.getView<TextView>(R.id.tv_content)
                    val tvLastUpdate = holder?.getView<TextView>(R.id.tv_last_update)

                    tvId?.text = roomDataBean?.id.toString()
                    tvContent?.text = roomDataBean?.content
                    tvLastUpdate?.text = roomDataBean?.last_update.toString()

                }
            })
        binding.rvData.adapter = mDataAdapter
        getDataList()
    }

    private fun add() {
        if (binding.etContent.text.toString().isNullOrEmpty()) {
            return
        }
        DataBase.getInstance(getContext()).dataDao().add(
            RoomDataBean().apply {
                content = binding.etContent.text.toString()
                //db version 2
                last_update = System.currentTimeMillis()
            })
    }

    private fun edit() {
        val id = binding.etId.text.toString()
        val content = binding.etContent.text.toString()
        if (id.isNullOrEmpty() || content.isNullOrEmpty()) {
            return
        }
        DataBase.getInstance(getContext()).dataDao().update(
            RoomDataBean().apply {
                this.id = id.toLong()
                this.content = content
            })
    }

    private fun delete() {
        val id = binding.etId.text.toString()
        if (id.isNullOrEmpty()) {
            return
        }
        DataBase.getInstance(getContext()).dataDao().delete(id.toLong())
    }

    private fun getDataList() {
        Thread {
            run {
                mDataList.clear()
                mDataList.addAll(DataBase.getInstance(getContext()).dataDao().getList())
                runOnUiThread{
                    mDataAdapter.notifyDataChanged(mDataList)
                }
            }
        }.start()
    }

    private fun switchOption(optionType: OptionType) {

        if (mOptionType == optionType) {
            return
        }

        mOptionType = optionType

        binding.etId.visibility = View.GONE
        binding.etContent.visibility = View.GONE

        when (optionType) {
            OptionType.ADD -> {
                binding.etContent.visibility = View.VISIBLE
                binding.btnOption.text = "Add"
            }

            OptionType.EDIT -> {
                binding.etId.visibility = View.VISIBLE
                binding.etContent.visibility = View.VISIBLE
                binding.btnOption.text = "Edit"
            }

            OptionType.DELETE -> {
                binding.etId.visibility = View.VISIBLE
                binding.btnOption.text = "Delete"
            }
        }
    }

    private enum class OptionType {
        ADD, EDIT, DELETE
    }
}