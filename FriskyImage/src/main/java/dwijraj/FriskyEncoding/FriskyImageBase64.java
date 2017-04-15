package dwijraj.FriskyEncoding;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.Callable;

import rx.Observable;

/**
 * Created by 1405214 on 28-03-2017.
 */

public class FriskyImageBase64 {


    public static String Encode(final Bitmap Image, final int Quality, final Bitmap.CompressFormat compressFormat)
    {

        Observable<String> ImageObservale=Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {


                return EncodeFunc(Image,Quality,compressFormat);
            }
        });

        return null;
    }

    public static   String EncodeFunc(Bitmap Image,int Quality,Bitmap.CompressFormat compressFormat)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Image.compress(compressFormat, Quality, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
    }



}
