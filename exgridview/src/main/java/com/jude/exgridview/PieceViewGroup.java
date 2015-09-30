package com.jude.exgridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Mr.Jude on 2015/9/15.
 */
public class  PieceViewGroup extends ExGridView {
    private OnViewDeleteListener onViewDeleteListener;
    private OnAskViewListener onAskViewListener;

    public void setOnAskViewListener(OnAskViewListener onAskViewListener) {
        this.onAskViewListener = onAskViewListener;
        addTail(new AddView(getContext()));
    }

    public void setOnViewDeleteListener(OnViewDeleteListener listener) {
        this.onViewDeleteListener = listener;
    }

    public PieceViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void beginEdit(){
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof PieceView){
                ((PieceView) getChildAt(i)).applyEditMode(true);
            }
        }
    }

    public void finishEdit(){
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof PieceView){
                ((PieceView) getChildAt(i)).applyEditMode(false);
            }
        }
    }

    void deletePieceView(View view){
        onViewDeleteListener.onViewDelete(indexOfChild(view));
        removeView(view);
    }

    void askView(){
        onAskViewListener.onAddView();
    }

    public interface OnViewDeleteListener{
        void onViewDelete(int index);
    }

    public interface OnAskViewListener {
        void onAddView();
    }

}
