package dwijraj.FriskySpeedo.speedviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.example.crazyimage.R;

public abstract class LinearGauge extends Gauge {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Rect rect = new Rect();
    private Bitmap foregroundBitmap;

    private Orientation orientation = Orientation.HORIZONTAL;

    public LinearGauge(Context context) {
        this(context, null);
    }

    public LinearGauge(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearGauge(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributeSet(context, attrs);
    }


    protected abstract void updateFrontAndBackBitmaps();

    private void initAttributeSet(Context context, AttributeSet attrs) {
        if (attrs == null)
            return;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LinearGauge, 0, 0);

        int orientation = a.getInt(R.styleable.LinearGauge_sv_orientation, -1);
        if (orientation != -1)
            setOrientation(Orientation.values()[orientation]);
        a.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);

        updateBackgroundBitmap();
    }

    @Override
    protected void updateBackgroundBitmap() {
        updateFrontAndBackBitmaps();
    }

    protected final Canvas createForegroundBitmapCanvas() {
        if (getWidthPa() == 0 || getHeightPa() == 0)
            return new Canvas();
        foregroundBitmap = Bitmap.createBitmap(getWidthPa(), getHeightPa(), Bitmap.Config.ARGB_8888);
        return new Canvas(foregroundBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (orientation == Orientation.HORIZONTAL)
            rect.set(0, 0
                    , (int)(getWidthPa() * getOffsetSpeed()), getHeightPa());
        else
            rect.set(0, getHeightPa() - (int)(getHeightPa() * getOffsetSpeed())
                    , getWidthPa(), getHeightPa());

        canvas.translate(getPadding(), getPadding());
        canvas.drawBitmap(foregroundBitmap, rect, rect, paint);
        canvas.translate(-getPadding(), -getPadding());

        drawSpeedUnitText(canvas);
    }

    public Orientation getOrientation() {
        return orientation;
    }


    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
        if (!isAttachedToWindow())
            return;
        requestLayout();
        updateBackgroundBitmap();
        invalidate();
    }

    public enum Orientation {
        HORIZONTAL, VERTICAL
    }
}
