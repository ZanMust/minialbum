package utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageUtils {
    public static void setImage(String imageURL, ImageView mImageView){
        Picasso.get().load(imageURL).into(mImageView);
    }
}
