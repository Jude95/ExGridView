package com.jude.exgridview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Mr.Jude on 2015/9/15.
 */
public class AddView extends ImagePieceView implements View.OnClickListener{
    private boolean isEditMode = false;
    private int res_add = R.drawable.ex_add;
    private int res_ok = R.drawable.ex_ok;

    public AddView(Context context) {
        super(context);
        init();
    }

    public AddView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AddView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setAddImageRes(@DrawableRes int res_add){
        this.res_add = res_add;
        if (!isEditMode)setImageRes(res_add);
    }

    public void setOKImageRes(@DrawableRes int res_ok){
        this.res_ok = res_ok;
        if (isEditMode)setImageRes(res_ok);
    }

    private void init(){
        setImageRes(R.drawable.ex_add);
        setOnClickListener(this);
    }

    @Override
    void applyEditMode(boolean isEditMode) {
        this.isEditMode = isEditMode;
        setImageRes(isEditMode?res_ok:res_add);
    }

    @Override
    public void onClick(View v) {
        if (getParent() instanceof PieceViewGroup){
            if (isEditMode) {
                ((PieceViewGroup) getParent()).finishEdit();
            }else{
                ((PieceViewGroup) getParent()).askView();
            }
        }
    }
}
