# ExGridView
可动态添加高度适应的GridView

![screenshot](https://raw.githubusercontent.com/Jude95/ExGridView/master/screenshot.jpg)  
设计用于图片列表展示。因为RecyclerView与GridView高度均不能设置为wrapcontent。而创建这个。
对于不需要滑动的图片列表。使用RecyclerView与GridView也显然浪费性能。
使用ExGridView高度设置wrapcontent即可。高效简单。   

##依赖
`compile 'com.jude:exgridview:1.1.5'`
##使用
与正常的ViewGroup无异。可直接在xml中添加子view。

    <com.jude.exgridview.ExGridView
        android:id="@+id/grid"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

也可在代码中设置Adapter。完美支持Adapter。

    gridView = (ExGridView) findViewById(R.id.grid);
        ImageAdapter adapter = new ImageAdapter(this);
        gridView.setAdapter(adapter);


##PieceView
在ExGridView的基础上的定制。实现常见的图片列表添加删除功能。长按图片进入删除模式。
![screenshot](https://raw.githubusercontent.com/Jude95/ExGridView/master/pieceview1.jpg).![screenshot](https://raw.githubusercontent.com/Jude95/ExGridView/master/pieceview2.jpg)   

这里必须使用PieceViewGroup与ImagePieceView配合(特殊需求自定义View继承PieceView)  

xml：  

    <com.jude.exgridview.PieceViewGroup
        android:id="@+id/relate"
        android:layout_width="200dp"
        android:layout_height="wrap_content"/>
        

代码：

        relateGridView = (PieceViewGroup) findViewById(R.id.relate);
        relateGridView.setOnAskViewListener(new PieceViewGroup.OnAskViewListener() {
        
            //当点击添加按钮。需要自己生成ImagePieceView添加进去
            @Override
            public void onAddView() {
                ImagePieceView imagePieceView = new ImagePieceView(MainActivity.this);
                imagePieceView.setImageRes(RES[((int) (Math.random() * 10))]);
                relateGridView.addView(imagePieceView);
            }
        });
        
        relateGridView.setOnViewDeleteListener(new PieceViewGroup.OnViewDeleteListener() {
        
          //当删除一个Item时的回调
            @Override
            public void onViewDelete(int index) {
                Toast.makeText(MainActivity.this, index + " Delete", Toast.LENGTH_SHORT).show();
            }
        });
        
        relateGridView.setAddImageRes(R.drawable.pic_add);//允许自定义添加图片与完成删除与删除的图标
        relateGridView.setOKImageRes(R.drawable.pic_ok);
        relateGridView.setDeleteRes(R.drawable.pic_delete);
