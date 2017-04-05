package dwijraj.FriskyEncoding;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by 1405214 on 28-03-2017.
 */

public class FriskyImageBase64Encode {

    protected Bitmap Image;
    protected FriskyImageCompressFormats friskyImageCompressFormats;
    protected int Quality;

    public FriskyImageBase64Encode(Bitmap Image, FriskyImageCompressFormats compressFormat, int quality)
    {
        this.Image=Image;
        this.friskyImageCompressFormats =compressFormat;
        this.Quality=quality;
    }

    public  String BitmapEncodeToBase64()
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        this.Image.compress(this.friskyImageCompressFormats.compressFormat, Quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }
}
