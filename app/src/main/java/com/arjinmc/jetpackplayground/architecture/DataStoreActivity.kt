package com.arjinmc.jetpackplayground.architecture

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * Created by Eminem Lo on 7/1/21.
 * email: arjinmc@hotmail.com
 */
private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreActivity : BasicActivity() {

    private lateinit var mEtName: EditText
    private lateinit var mEtAge: EditText
    private lateinit var mBtnSave: Button
    private lateinit var mTvName: TextView
    private lateinit var mTvAge: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_data_store)
        init()
    }

    override fun initView() {

        mEtName = findViewById(R.id.et_name)
        mEtAge = findViewById(R.id.et_age)
        mBtnSave = findViewById(R.id.btn_save)
        mTvName = findViewById(R.id.tv_name)
        mTvAge = findViewById(R.id.tv_age)
    }

    override fun initListener() {
        mBtnSave.setOnClickListener {
            lifecycleScope.launch {
                saveData("name", mEtName.text.toString())
                saveData("age", mEtAge.text.toString())
                showStoreData()
            }
        }
    }

    override fun initData() {
        setTitle(R.string.arch_data_store)

        showStoreData()
    }

    private fun showStoreData() {
        lifecycleScope.launch {
            mTvName.text = getData("name")
            mTvAge.text = getData("age")
        }
    }

    private suspend fun saveData(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { it[dataStoreKey] = value }
    }

    private suspend fun getData(key: String): String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
}