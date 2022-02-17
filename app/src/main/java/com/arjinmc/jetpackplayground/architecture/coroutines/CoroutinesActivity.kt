package com.arjinmc.jetpackplayground.architecture.coroutines

import android.graphics.Point
import android.os.Bundle
import com.arjinmc.jetpackplayground.R
import com.arjinmc.jetpackplayground.basic.BasicActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine


/**
 * Created by Eminem Lo on 12/15/21
 * email: arjinmc@hotmail.com
 */
class CoroutinesActivity : BasicActivity() {

    private val scope = MainScope()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        kotlin.runCatching { }

        postponeEnterTransition()



        runBlocking {
            var susjob = suspendCancellableCoroutine<Int> {

            }
        }

//        var job1 = newSingleThreadContext("Go1")
//        runBlocking {
//           var scope1 =  CoroutineScope(Dispatchers.Default+scope.launch {
//               repeat(100){
//                   delay(100)
//                   Log.e("go1", "go1")
//               }
//           }).launch {
//                repeat(100){
//                    delay(100)
//                    Log.e("go2", "go2")
//                }
//            }
//
//            delay(1000)
////            scope1.cancel()
//            scope.cancel()
//        }

    }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun initData() {
        setTitle(R.string.arch_coroutine)
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()

    }



}