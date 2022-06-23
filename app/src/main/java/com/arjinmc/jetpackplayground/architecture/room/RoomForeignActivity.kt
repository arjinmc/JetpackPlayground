package com.arjinmc.jetpackplayground.architecture.room

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
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

    private lateinit var mDataForeignAdapter: RecyclerViewAdapter<RoomDataForeignDataBean>
    private lateinit var mDataForeignList: LiveData<MutableList<RoomDataForeignDataBean>>

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
            val dataId = binding.etDataId.text.toString()
            if (content.isNullOrBlank() || dataId.isNullOrBlank()) {
                return@setOnClickListener
            }

            val dataBean = RoomDataForeignDataBean()
            dataBean.content = content
            dataBean.dataId = dataId.toLong()

            lifecycleScope.launch(Dispatchers.IO) {
                DataBase.getInstance(getContext()).dataForeignDao().add(dataBean)
                getForeignDataList()
            }

        }
    }

    override fun initData() {

        initDataAdapter()
        initDataForeignAdapter()

    }

    private fun initDataAdapter() {
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
            mDataAdapter.notifyDataChanged(it)
        }
    }

    private fun initDataForeignAdapter() {
        getForeignDataList()

        mDataForeignAdapter = RecyclerViewAdapter(
            getContext(),
            mDataForeignList.value,
            R.layout.item_room_data_foreign,
            object : RecyclerViewSingleTypeProcessor<RoomDataForeignDataBean>() {
                override fun onBindViewHolder(
                    holder: RecyclerViewViewHolder?,
                    position: Int,
                    item: RoomDataForeignDataBean
                ) {
                    val tvId = holder?.getView<TextView>(R.id.tv_id)
                    val tvContent = holder?.getView<TextView>(R.id.tv_content)
                    val tvForeignContent = holder?.getView<TextView>(R.id.tv_foreign_content)

                    val btnDel = holder?.getView<Button>(R.id.btn_delete)

                    tvId?.text = "ID:${item.id}"
                    tvContent?.text = "Content:${item.content}"
                    tvForeignContent?.text = "ForeignContent:${item.foreignContent}"

                    btnDel?.setOnClickListener {
                        deleteForeignData(item)
                    }
                }
            })

        binding.rvResult.adapter = mDataForeignAdapter
        mDataForeignList.observe(this) {
            mDataForeignAdapter.notifyDataChanged(it)
        }

    }

    private fun getForeignDataList() {
        mDataForeignList =
            DataBase.getInstance(getContext()).dataForeignDao().getList().asLiveData()
    }

    private fun deleteForeignData(dataForeignDataBean: RoomDataForeignDataBean) {
        lifecycleScope.launch(Dispatchers.IO) {
            DataBase.getInstance(getContext()).dataForeignDao().delete(dataForeignDataBean.id!!)
        }
    }
}