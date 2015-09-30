package com.jude.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.jude.exgridview.ExGridView;
import com.jude.exgridview.ImagePieceView;
import com.jude.exgridview.PieceViewGroup;

public class MainActivity extends AppCompatActivity {
    private PieceViewGroup relateGridView;
    private ExGridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (ExGridView) findViewById(R.id.grid);
        ImageAdapter adapter = new ImageAdapter(this);
        gridView.setAdapter(adapter);
        adapter.clear();
        adapter.addAll(new Integer[]{
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
        });

        relateGridView = (PieceViewGroup) findViewById(R.id.relate);
        relateGridView.setOnAskViewListener(new PieceViewGroup.OnAskViewListener() {
            @Override
            public void onAddView() {
                Log.i("Grid","Ask View");
            }
        });
        relateGridView.setAdapter(new ArrayAdapter<Integer>(this,0,new Integer[]{
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
                R.drawable.image6,
                R.drawable.image7,
                R.drawable.image10,
                R.drawable.image10,
                R.drawable.image10,
        }){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImagePieceView imagePieceView = new ImagePieceView(getContext());
                imagePieceView.setImageRes(getItem(position));
                return imagePieceView;
            }
        });
    }

}
