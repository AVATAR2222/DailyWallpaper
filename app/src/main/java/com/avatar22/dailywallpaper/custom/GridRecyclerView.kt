package com.avatar22.dailywallpaper.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.GridLayoutAnimationController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    override fun setLayoutManager(layout: LayoutManager?) {
        if (layout is GridLayoutManager) {
            super.setLayoutManager(layout)
        } else {
            throw Exception("You must use GridlayoutManager an GridRecyclerView")
        }
    }

    override fun attachLayoutAnimationParameters(child: View?, params: ViewGroup.LayoutParams, index: Int, count: Int) {
        if (adapter != null && layoutManager is GridLayoutManager){
            var animParams = params.layoutAnimationParameters as GridLayoutAnimationController.AnimationParameters?
            if (animParams == null){
                animParams = GridLayoutAnimationController.AnimationParameters()
                params.layoutAnimationParameters = animParams
            }

            val column = (layoutManager as GridLayoutManager).spanCount

            animParams.count = count
            animParams.index = index
            animParams.columnsCount = column
            animParams.rowsCount = count/column

            val mIndex = count - 1 - index
            animParams.column = column - 1 - (mIndex % column)
            animParams.row = animParams.rowsCount - 1 - mIndex/column
        }else{
            super.attachLayoutAnimationParameters(child, params, index, count)
        }
    }
}