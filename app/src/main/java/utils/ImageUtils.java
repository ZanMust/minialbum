package utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class ImageUtils {
    public static void setImage(String imageURL, ImageView mImageView){
        Picasso.get().load(imageURL).into(mImageView);
    }
    public static void setImageGallery(String imageURL, ImageView mImageView, Context mContext){
        Display display = ((Activity)mContext).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int scrWidth = size.x;
        int scrHeight = size.y;

        int mViewSize = Math.min(scrWidth, scrHeight) / getNumberOfColumns(mContext);
        Picasso.get().load(imageURL).resize(mViewSize,mViewSize).into(mImageView);
    }
    public static int getNumberOfColumns(Context mContext){
        Display display = ((Activity)mContext).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int scrWidth = size.x;
        int scrHeight = size.y;

        int numOfColumns = (int)(Math.min(scrWidth, scrHeight)/ dpToPix(mContext, 100));
        if(numOfColumns < 2){
            numOfColumns = 2;
        }
        return numOfColumns;
    }
    public static float dpToPix(Context context, float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
