package com.jude.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.jude.exgridview.ExGridView;
import com.jude.exgridview.ImagePieceView;
import com.jude.exgridview.PieceViewGroup;

public class MainActivity extends AppCompatActivity {
    private PieceViewGroup relateGridView;
    private ExGridView gridView;

    private static final int[] RES = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (ExGridView) findViewById(R.id.grid);
        ImageAdapter adapter = new ImageAdapter(this);
        gridView.setAdapter(adapter);
        adapter.clear();
        adapter.addAll(
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5);


        relateGridView = (PieceViewGroup) findViewById(R.id.relate);
        relateGridView.setOnAskViewListener(new PieceViewGroup.OnAskViewListener() {
            @Override
            public void onAddView() {
                ImagePieceView imagePieceView = new ImagePieceView(MainActivity.this);
                imagePieceView.setImageRes(RES[((int) (Math.random() * 10))]);
                relateGridView.addView(imagePieceView);
            }
        });
        relateGridView.setOnViewDeleteListener(new PieceViewGroup.OnViewDeleteListener() {
            @Override
            public void onViewDelete(int index) {
                Toast.makeText(MainActivity.this, index + " Delete", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
