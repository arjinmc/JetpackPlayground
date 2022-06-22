package com.arjinmc.jetpackplayground.architecture.room

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewAdapter
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewSingleTypeProcessor
import com.arjinmc.expandrecyclerview.adapter.RecyclerViewViewHolder
import com.arjinmc.expandrecyclerview.style.RecyclerViewStyleHelper
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity
import com.arjinmc.jetpackplayground.databinding.ActRoomForeginBinding
import com.arjinmc.recyclerviewdecoration.RecyclerViewLinearItemDecoration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Eminem Lo on 6/22/22
 * email: arjinmc@hotmail.com
 */
class RoomForeignActivity : BasicActivity() {

    private lateinit var binding: ActRoomForeginBinding

    private lateinit var mDataAdapter: RecyclerViewAdapter<RoomDataBean>
    private lateinit var mDataList: LiveData<List<RoomDataBean>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActRoomForeginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun initView() {


        RecyclerViewStyleHelper.toLinearLayout(binding.rvDataList, RecyclerView.VERTICAL)
        RecyclerViewStyleHelper.toLinearLayout(binding.rvResult, RecyclerView.VERTICAL)

        val rvItemDecorator = RecyclerViewLinearItemDecoration.Builder(getContext())
            .thickness(2)
            .color(Color.GRAY)
            .create()

        binding.rvDataList.addItemDecoration(rvItemDecorator)
        binding.rvResult.addItemDecoration(rvItemDecorator)
    }

    override fun initListener() {

        binding.btnAdd.setOnClickListener {
            val content = binding.etContent.text.toString()
            val dataId = binding.etDataId.text.toString().toLong()
            if (content.isNullOrBlank()) {
                return@setOnClickListener
            }

            val dataBean = RoomDataForeignDataBean()
            dataBean.content = content
            dataBean.dataId = dataId

            CoroutineScope(Dispatchers.IO).launch {
                DataBase.getInstance(getContext()).dataForeignDao().add(dataBean)
                getDataList()
            }

        }
    }

    override fun initData() {

        mDataList = DataBase.getInstance(getContext()).dataDao().getList().asLiveData()
        mDataAdapter = RecyclerViewAdapter(
            getContext(),
            mDataList.value,
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
        binding.rvDataList.adapter = mDataAdapter
        mDataList.observe(this) {
            if (it.isNullOrEmpty()) {
                Log.e("change", "empty")
            }
            mDataAdapter.notifyDataChanged(it)
        }

    }

    private fun getDataList() {

    }
}