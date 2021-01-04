package com.example.channelproject.animation

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.channelproject.R

class OffsetDecoration:RecyclerView.ItemDecoration() {
    @SuppressLint("ResourceAsColor")
    override fun getItemOffsets(outRect: Rect,
                                view: View,
                                parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val itemPosition = parent.getChildAdapterPosition(view)
        val itemWidth = view.layoutParams.width
        val offset = (parent.width - itemWidth) / 2
        if(itemPosition == 0){
            outRect.left = offset
        }else if(itemPosition == state.itemCount -1){
            outRect.right = offset
        }
    }
}