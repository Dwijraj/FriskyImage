package dwijraj.FriskyEncoding;

import android.graphics.Bitmap;

/**
 * Created by 1405214 on 28-03-2017.
 */

public class FriskyImageCompressFormats {

    public    Bitmap.CompressFormat compressFormat;
    public    static String JPEG="JPEG";
    public    static String PNG="PNG";
    public    static String WEBP="WEBP";
    public FriskyImageCompressFormats(String Str)
    {
        if(Str.equals(JPEG)||Str.equals("jpeg"))
        {
            compressFormat= Bitmap.CompressFormat.JPEG;
        }
        else if(Str.equals(WEBP)||Str.equals("webp"))
        {
            compressFormat= Bitmap.CompressFormat.WEBP;
        }
        else
        {
            compressFormat= Bitmap.CompressFormat.PNG;
        }
    }
}
