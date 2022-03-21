package com.arjinmc.jetpackplayground.ui.compose.basic

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.GrayscaleTransformation
import com.arjinmc.jetpackplayground.ui.compose.model.ComposeGridListData
import com.arjinmc.jetpackplayground.ui.compose.widget.ComposeBaseHeader
import com.arjinmc.jetpackplayground.util.ToastUtil

/**
 * Created by Eminem Lo on 3/21/22
 * email: arjinmc@hotmail.com
 */
class ComposeGridViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridViewPage(this, { finish() }, initData())
        }
    }

    fun initData(): List<ComposeGridListData> {
        val list = mutableListOf<ComposeGridListData>()
        list.add(
            ComposeGridListData(
                "test one",
                "https://img2.baidu.com/it/u=2372481164,996390177&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            )
        )
        list.add(
            ComposeGridListData(
                "test two",
                "https://img0.baidu.com/it/u=991224229,226183047&fm=253&fmt=auto&app=138&f=JPEG?w=278&h=288"
            )
        )
        list.add(
            ComposeGridListData(
                "test three",
                "https://img2.baidu.com/it/u=645168117,2866819619&fm=253&fmt=auto&app=138&f=JPG?w=278&h=290"
            )
        )
        list.add(
            ComposeGridListData(
                "test four",
                "https://img1.baidu.com/it/u=706347449,556775562&fm=253&fmt=auto&app=138&f=JPEG?w=254&h=255"
            )
        )
        list.add(
            ComposeGridListData(
                "test five",
                "https://img2.baidu.com/it/u=1336292633,2561406447&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            )
        )
        list.add(
            ComposeGridListData(
                "test six",
                "https://img2.baidu.com/it/u=1987245160,3743194843&fm=253&fmt=auto&app=120&f=JPEG?w=690&h=660"
            )
        )
        list.add(
            ComposeGridListData(
                "test seven",
                "https://img0.baidu.com/it/u=2940084161,82712363&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            )
        )
        list.add(
            ComposeGridListData(
                "test eight",
                "https://img2.baidu.com/it/u=1277771644,4236080752&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            )
        )
        list.add(
            ComposeGridListData(
                "test nine",
                "https://img0.baidu.com/it/u=1259252205,914727185&fm=253&fmt=auto&app=138&f=JPG?w=500&h=500"
            )
        )
        list.add(
            ComposeGridListData(
                "test ten",
                "https://img2.baidu.com/it/u=2978608128,97714912&fm=26&fmt=auto"
            )
        )
        list.add(
            ComposeGridListData(
                "test eleven",
                "https://img1.baidu.com/it/u=3406663890,2314134217&fm=26&fmt=auto"
            )
        )

        list.add(
            ComposeGridListData(
                "test twelve",
                "https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E8%A1%A8%E6%83%85&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=3624864230,7624267&os=1099522568,2085288164&simid=47882735,727672874&pn=16&rn=1&di=7060663421280190465&ln=1845&fr=&fmq=1647849878583_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fc-ssl.duitang.com%252Fuploads%252Fitem%252F202004%252F15%252F20200415024818_PGr5L.thumb.1000_0.jpg%26refer%3Dhttp%253A%252F%252Fc-ssl.duitang.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Dauto%3Fsec%3D1650441878%26t%3D451e482b0ed73708e256d5a772789978&rpstart=0&rpnum=0&adpicid=0&nojc=undefined&dyTabStr=MCwzLDYsMSw0LDIsNSw3LDgsOQ%3D%3D"
            )
        )
        list.add(
            ComposeGridListData(
                "test thirteen",
                "https://img0.baidu.com/it/u=991224229,226183047&fm=253&fmt=auto&app=138&f=JPEG?w=278&h=288"
            )
        )
        list.add(
            ComposeGridListData(
                "test fourteen",
                "https://img2.baidu.com/it/u=645168117,2866819619&fm=253&fmt=auto&app=138&f=JPG?w=278&h=290"
            )
        )
        list.add(
            ComposeGridListData(
                "test fifteen",
                "https://img1.baidu.com/it/u=706347449,556775562&fm=253&fmt=auto&app=138&f=JPEG?w=254&h=255"
            )
        )
        list.add(
            ComposeGridListData(
                "test sixteen",
                "https://img2.baidu.com/it/u=1336292633,2561406447&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            )
        )
        list.add(
            ComposeGridListData(
                "test seventeen",
                "https://img2.baidu.com/it/u=1987245160,3743194843&fm=253&fmt=auto&app=120&f=JPEG?w=690&h=660"
            )
        )
        list.add(
            ComposeGridListData(
                "test eighteen",
                "https://img0.baidu.com/it/u=2940084161,82712363&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            )
        )
        list.add(
            ComposeGridListData(
                "test nineteen",
                "https://img2.baidu.com/it/u=1277771644,4236080752&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            )
        )
        list.add(
            ComposeGridListData(
                "test twenty",
                "https://img0.baidu.com/it/u=1259252205,914727185&fm=253&fmt=auto&app=138&f=JPG?w=500&h=500"
            )
        )
        list.add(
            ComposeGridListData(
                "test twenty one",
                "https://img2.baidu.com/it/u=2978608128,97714912&fm=26&fmt=auto"
            )
        )
        list.add(
            ComposeGridListData(
                "test twenty two",
                "https://img1.baidu.com/it/u=3406663890,2314134217&fm=26&fmt=auto"
            )
        )

        return list
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridViewPage(context: Context, onLeftClick: () -> Unit?, listData: List<ComposeGridListData>) {
    Column {
        ComposeBaseHeader(
            context = context,
            titleStr = "Compose List",
            onLeftClick = onLeftClick
        )

        LazyVerticalGrid(cells = GridCells.Fixed(3)) {
            items(listData) { data ->
                Image(painter = rememberImagePainter(data = data.url, builder = {
                    //change to gray color
                    transformations(GrayscaleTransformation())
                }),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .border(width = 0.5.dp, color = Color.Blue)
                        .clickable {
                            ToastUtil.showShort(
                                context = context,
                                msg = "Click: ${data.name}"
                            )
                        })

            }
        }

    }
}