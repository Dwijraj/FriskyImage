package dwijraj.FriskySpeedo.speedviewlib.components.note;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class ImageNote extends Note<ImageNote> {

    private Bitmap image;
    private int width, height;
    private RectF imageRect = new RectF();
    private Paint notePaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public ImageNote(Context context, int resource) {
        this(context, BitmapFactory.decodeResource(context.getResources(), resource));
    }


    public ImageNote(Context context, int resource, int width, int height) {
        this(context, BitmapFactory.decodeResource(context.getResources(), resource)
                , width, height);
    }

    public ImageNote(Context context, Bitmap image) {
        this(context, image, image.getWidth(), image.getHeight());
    }


    public ImageNote(Context context, Bitmap image, int width, int height) {
        super(context);
        if (image == null)
            throw new IllegalArgumentException("image cannot be null.");
        this.image = image;
        this.width = width;
        this.height = height;
        if (width <= 0)
            throw new IllegalArgumentException("width must be bigger than 0");
        if (height <= 0)
            throw new IllegalArgumentException("height must be bigger than 0");
    }

    @Override
    public void build(int viewWidth) {
        noticeContainsSizeChange(this.width, this.height);
    }

    @Override
    protected void drawContains(Canvas canvas, float leftX, float topY) {
        imageRect.set(leftX, topY, leftX + width, topY + height);
        canvas.drawBitmap(image, null, imageRect, notePaint);
    }
}
