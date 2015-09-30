package com.jude.exgridview;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Mr.Jude on 2015/9/15.
 */
public class ImagePieceView extends PieceView {
    private ImageView mImageView;
    public ImagePieceView(Context context) {
        super(context);
        init();
    }

    public ImagePieceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImagePieceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mImageView= new ImageView(getContext());
        mImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(mImageView);
    }

    public void setImageUri(Uri uri){
        mImageView.setImageURI(uri);
    }

    public void setImageRes(int res){
        mImageView.setImageResource(res);
    }

    public void setImageBitmap(Bitmap bitmap){
        mImageView.setImageBitmap(bitmap);
    }
}
