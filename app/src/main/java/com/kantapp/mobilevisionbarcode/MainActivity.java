package com.kantapp.mobilevisionbarcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.txtContent);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView=findViewById(R.id.imgview);
                Bitmap bitmap= BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.puppy);

                imageView.setImageBitmap(bitmap);

                BarcodeDetector detector=new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(Barcode.ALL_FORMATS)
                        .build();
                if(!detector.isOperational())
                {

                    textView.setText("Could not set up the detector!");
                    return;
                }

                Frame frame=new Frame.Builder().setBitmap(bitmap).build();
                SparseArray<Barcode> barcodes=detector.detect(frame);

                Barcode thisCode=barcodes.valueAt(0);
                textView.setText(thisCode.rawValue);


            }
        });
    }
}
