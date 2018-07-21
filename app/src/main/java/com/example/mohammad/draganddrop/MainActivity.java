package com.example.mohammad.draganddrop;

import android.content.ClipData;
import android.content.ClipDescription;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    ImageView im;
    String str;
    private android.widget.RelativeLayout.LayoutParams layoutParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im=findViewById(R.id.imageView);
        im.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item= new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes={ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData dragData= new ClipData(v.getTag().toString(),mimeTypes,item);
                View.DragShadowBuilder shadowBuilder= new View.DragShadowBuilder(im);
                v.startDragAndDrop(dragData,shadowBuilder,null,0);
                return true;
            }
        });
        im.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {

                switch (event.getAction()){
                    case DragEvent.ACTION_DRAG_STARTED:
                        layoutParams=(RelativeLayout.LayoutParams) v.getLayoutParams();
                        Log.d(str,"Action drag started");
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(str,"acton drag enter");
                        int x_cord=(int) event.getX();
                        int y_cord=(int) event.getY();
                        break;
                    case DragEvent.ACTION_DRAG_EXITED :
                        Log.d(str, "Action is DragEvent.ACTION_DRAG_EXITED");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        v.setLayoutParams(layoutParams);
                        break;

                    case DragEvent.ACTION_DRAG_LOCATION  :
                        Log.d(str, "Action is DragEvent.ACTION_DRAG_LOCATION");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_ENDED   :
                        Log.d(str, "Action is DragEvent.ACTION_DRAG_ENDED");

                        // Do nothing
                        break;

                    case DragEvent.ACTION_DROP:
                        Log.d(str, "ACTION_DROP event");
                        int x=(int )event.getX();
                        int y=(int) event.getY();
                        

                        // Do nothing
                        break;
                    default: break;
                }

                return true;
            };

        });

        im.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(im);

                    im.startDragAndDrop(data, shadowBuilder, im, 0);
                    im.setVisibility(View.VISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
