package praveen.com.bitmaplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Praveen on 29/2/16.
 */
public class LayeredImageView extends ImageView {
    private final static String TAG = "LayeredImageView";
    float j=1;

    ArrayList<Bitmap> mLayers;

    public LayeredImageView(Context context) {
        super(context);
        init();
    }

    public LayeredImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mLayers = new ArrayList<Bitmap>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Matrix matrix = getImageMatrix();
        if (matrix != null) {
            int numLayers = mLayers.size();
            for (int i = 0; i < numLayers; i++,j++) {
                Bitmap b = mLayers.get(i);
                if(j==2 && i==1){
                    j=1.45f;
                }
                if (i==2){
                    j=0.8f;
                }
                canvas.drawBitmap(b,j*130,j*550,null);
               // canvas.drawBitmap(b, matrix, null);
            }
        }
    }

    public void addLayer(Bitmap b) {
        mLayers.add(b);
        invalidate();
    }

    public void addLayer(int idx, Bitmap b) {
        mLayers.add(idx, b);
        invalidate();
    }

    public void removeLayer(int idx) {
        mLayers.remove(idx);
    }

    public void removeAllLayers() {
        mLayers.clear();
        invalidate();
    }

    public int getLayersSize() {
        return mLayers.size();
    }
}