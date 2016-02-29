package praveen.com.bitmaplayer;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;

public class StartActivity extends ActionBarActivity {

    private final static String TAG = "LayeredImageView";
    private ImageView image_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
image_view=(ImageView)findViewById(R.id.image1);
        LayeredImageView v = new LayeredImageView(this);


        v.setImageResource(R.drawable.background);
        Resources res = getResources();
        v.addLayer(BitmapFactory.decodeResource(res, R.drawable.layer0));
        v.addLayer(BitmapFactory.decodeResource(res, R.drawable.layer1));
        Bitmap b=BitmapFactory.decodeResource(getResources(), R.drawable.images);
        Log.d(TAG,"iBefore resize  :"+b.getWidth()+", "+b.getHeight());
        Bitmap resizeBitmap=getResizedBitmap(b,120,120);
        v.addLayer(resizeBitmap);
       // v.addLayer(BitmapFactory.decodeResource(res, R.id.image1));
        setContentView(v);
        Log.d(TAG,"Layer Size :"+v.getLayersSize()+"image Size :"+resizeBitmap.getWidth()+", "+resizeBitmap.getHeight());
    }
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);
        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        Log.d(TAG,"image Size ### :"+resizedBitmap.getWidth()+", "+resizedBitmap.getHeight());
        return resizedBitmap;
    }

}
