package com.jude.exgridview;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;


/**
 * Created by Mr.Jude on 2015/3/13.
 */
public class ExGridView extends ViewGroup{
    private int mColumnCount;

    private int mTailCount;

    private float mDividerVertical;
    private float mDividerHorizontal;

    public ExGridView(Context context) {
        this(context, null);
    }

    public ExGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.GridView);
        try {
            mColumnCount = a.getInteger(R.styleable.GridView_grid_column, 4);
            mDividerVertical = a.getDimension(R.styleable.GridView_grid_divider_vertical, Utils.dip2px(getContext(), 8));
            mDividerHorizontal = a.getDimension(R.styleable.GridView_grid_divider_horizontal,Utils.dip2px(getContext(),8));
        }finally {
            a.recycle();
        }
    }

    @Override
    public void addView(View child) {
        addView(child,getChildCount()-mTailCount);
    }

    public void addTail(final View view){
        addView(view);
        mTailCount++;
    }

    public void removeTail(final View view){
        removeView(view);
        mTailCount--;
    }


    public void setAdapter(final Adapter adapter){
        for (int i = 0; i < adapter.getCount(); i++) {
            addView(adapter.getView(i, null, this));
        }
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                for (int i = 0; i < (getChildCount() - mTailCount); i++) {
                    removeViewAt(i);
                }
                for (int i = 0; i < adapter.getCount(); i++) {
                    addView(adapter.getView(i, null, ExGridView.this));
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);

        int childSize = (int) ((measuredWidth-getPaddingLeft()-getPaddingRight() - mDividerHorizontal *(mColumnCount -1))/ mColumnCount);
        int sizeSpec = MeasureSpec.makeMeasureSpec(childSize,MeasureSpec.EXACTLY);
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).measure(sizeSpec, sizeSpec);
        }

        //根据每一行的最高高度，确定总高度
        int totalHeight = 0,maxHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            maxHeight = Math.max(maxHeight,getChildAt(i).getMeasuredHeight());
            if ((i+1)%mColumnCount==0){
                totalHeight += maxHeight+mDividerVertical;
                maxHeight=0;
            }
        }
        totalHeight += maxHeight;
        int contentHeight = (getChildCount() == 0?0:totalHeight)+getPaddingTop()+getPaddingBottom();
        switch (MeasureSpec.getMode(heightMeasureSpec)){
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.UNSPECIFIED:
                measuredHeight = contentHeight;
                break;
            case MeasureSpec.AT_MOST:
                measuredHeight = Math.min(measuredHeight,contentHeight);
                break;
        }
        setMeasuredDimension(measuredWidth,measuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int wightTotal = right - left;
        int width = (int) ((wightTotal-getPaddingLeft()-getPaddingRight() - mDividerHorizontal *(mColumnCount -1))/ mColumnCount);

        int totalHeight = getPaddingTop(),maxHeight = 0;
        for (int i = 0 ; i < getChildCount() ; i++){
            maxHeight = Math.max(maxHeight,getChildAt(i).getMeasuredHeight());
            if ((i+1)%mColumnCount==0||i==getChildCount()-1){
                int lineCount = (i+1)%mColumnCount==0?mColumnCount:(i-i/mColumnCount*mColumnCount+1);
                for (int c = 0; c < lineCount; c++) {
                    int index = i+1-lineCount+c;
                    int x = (int) ((index % mColumnCount) * (width + mDividerHorizontal) + getPaddingLeft());
                    View child = getChildAt(index);
                    child.layout(x, totalHeight, x + child.getMeasuredWidth(), totalHeight + child.getMeasuredHeight());
                }
                totalHeight += maxHeight+mDividerVertical;
                maxHeight=0;
            }
        }
    }
}
