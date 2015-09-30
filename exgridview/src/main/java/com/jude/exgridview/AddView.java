package com.jude.exgridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Mr.Jude on 2015/9/15.
 */
public class AddView extends ImagePieceView implements View.OnClickListener{
    private boolean isEditMode = false;

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

    private void init(){
        setImageRes(R.drawable.ex_add);
        setOnClickListener(this);
    }

    @Override
    void applyEditMode(boolean isEditMode) {
        this.isEditMode = isEditMode;
        setImageRes(isEditMode?R.drawable.ex_ok :R.drawable.ex_add);
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
