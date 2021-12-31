package com.arjinmc.jetpackplayground.architecture.coroutines

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenResumed
import kotlinx.coroutines.launch

/**
 * Created by Eminem Lo on 12/23/21
 * email: arjinmc@hotmail.com
 */
class CoroutineFragment : Fragment() {

    private val TAG = "CoroutineFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            whenResumed {
                Log.e(TAG, "OnResume1")
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            //must be in coroutine
            viewLifecycleOwner.whenResumed {
                Log.e(TAG, "OnResume2")
            }
        }


    }
}