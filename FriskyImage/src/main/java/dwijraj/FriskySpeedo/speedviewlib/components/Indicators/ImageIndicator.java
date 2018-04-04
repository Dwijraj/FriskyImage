package dwijraj.FriskySpeedo.speedviewlib.components.Indicators;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

@SuppressWarnings("unused,WeakerAccess")
public class ImageIndicator extends Indicator<ImageIndicator> {

    private Bitmap bitmapIndicator;
    private int width, height;
    private RectF bitmapRect = new RectF();


    public ImageIndicator(Context context, int resource) {
        this(context, BitmapFactory.decodeResource(context.getResources(), resource));
    }


    public ImageIndicator(Context context, int resource, int width, int height) {
        this(context, BitmapFactory.decodeResource(context.getResources(), resource)
                , width, height);
    }

    public ImageIndicator(Context context, Bitmap bitmapIndicator) {
        this(context, bitmapIndicator, bitmapIndicator.getWidth(), bitmapIndicator.getHeight());
    }


    public ImageIndicator(Context context, Bitmap bitmapIndicator, int width, int height) {
        super(context);
        this.bitmapIndicator = bitmapIndicator;
        this.width = width;
        this.height = height;
        if (width <= 0)
            throw new IllegalArgumentException("width must be bigger than 0");
        if (height <= 0)
            throw new IllegalArgumentException("height must be bigger than 0");
    }

    @Override
    protected float getDefaultIndicatorWidth() {
        return 0f;
    }

    @Override
    public void draw(Canvas canvas, float degree) {
        canvas.save();
        canvas.rotate(90f + degree, getCenterX(), getCenterY());
        bitmapRect.set(getCenterX() - (width/2f), getCenterY() - (height/2f)
                , getCenterX() + (width/2f), getCenterY() + (height/2f));
        canvas.drawBitmap(bitmapIndicator, null, bitmapRect, indicatorPaint);
        canvas.restore();
    }

    @Override
    protected void updateIndicator() {
    }

    @Override
    protected void setWithEffects(boolean withEffects) {
    }
}
