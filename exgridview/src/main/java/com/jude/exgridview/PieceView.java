package com.jude.exgridview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by Mr.Jude on 2015/3/13.
 */
public class PieceView extends FrameLayout implements View.OnLongClickListener{

    protected ImageView mDelete;


    public PieceView(Context context) {
        this(context, null);
    }

    public PieceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    protected void initView(){
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        setOnLongClickListener(this);
        mDelete = new ImageView(getContext());

        LayoutParams params = new LayoutParams(Utils.dip2px(getContext(),32), Utils.dip2px(getContext(),32));
        params.gravity = Gravity.RIGHT;
        mDelete.setLayoutParams(params);
        mDelete.setImageResource(R.drawable.ex_delete);
        mDelete.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mDelete.setVisibility(GONE);
        mDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete();
            }
        });
        addView(mDelete);
    }

    public void setDeleteRes(@DrawableRes int res){
        mDelete.setImageResource(res);
    }


    public void onDelete(){
        clearAnimation();
        if (getParent()!=null){
            ((PieceViewGroup)getParent()).deletePieceView(this);
        }
    }


    void applyEditMode(boolean isEditMode){
        if (isEditMode){
            mDelete.bringToFront();
            mDelete.setVisibility(VISIBLE);
            startAnimation(shakeAnimation());
        }else{
            mDelete.setVisibility(GONE);
            clearAnimation();
        }
    }

    public RotateAnimation shakeAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(-1, 1, getWidth() / 2, getHeight() / 2);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        rotateAnimation.setDuration(60);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setRepeatMode(RotateAnimation.REVERSE);
        rotateAnimation.setFillAfter(false);
        return rotateAnimation;
    }

    @Override
    public boolean onLongClick(View v) {
        if (getParent() instanceof PieceViewGroup){
            ((PieceViewGroup) getParent()).beginEdit();
        }
        return false;
    }



}
