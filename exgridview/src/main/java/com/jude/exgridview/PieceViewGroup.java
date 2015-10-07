package com.jude.exgridview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Mr.Jude on 2015/9/15.
 */
public class  PieceViewGroup extends ExGridView {
    private OnViewDeleteListener onViewDeleteListener;
    private OnAskViewListener onAskViewListener;
    private AddView addView;

    int resDelete;

    public PieceViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(AttributeSet attrs) {
        super.init(attrs);
        addView = new AddView(getContext());
        addTail(addView);
    }

    public AddView getAddView(){
        return addView;
    }

    public void setOnAskViewListener(OnAskViewListener onAskViewListener) {
        this.onAskViewListener = onAskViewListener;
    }

    public void setOnViewDeleteListener(OnViewDeleteListener listener) {
        this.onViewDeleteListener = listener;
    }

    public void setAddImageRes(@DrawableRes int res_add){
        this.addView.setAddImageRes(res_add);
    }

    public void setOKImageRes(@DrawableRes int res_ok){
        this.addView.setOKImageRes(res_ok);
    }

    public void setDeleteRes(@DrawableRes int res_delete){
        this.resDelete = res_delete;
    }

    public void beginEdit(){
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof PieceView){
                if (resDelete!=0)((PieceView) getChildAt(i)).setDeleteRes(resDelete);
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
