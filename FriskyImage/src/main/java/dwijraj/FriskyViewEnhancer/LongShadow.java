package dwijraj.FriskyViewEnhancer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.example.crazyimage.R;

public class LongShadow extends FrameLayout {

    private final static int DEFAULT_SHADOW_COLOR = Color.parseColor("#739440");
    private final static int DEFAULT_SHADOW_ANGLE = 45;

    private final Paint mPaint = new Paint();
    private int shadowColor = -1;
    private float shadowAlpha = 1;
    private float shadowAngle = -1;

    private Bitmap bitmap;
    private Canvas canvas;

    public LongShadow(Context context) {
        this(context, null);
    }

    public LongShadow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LongShadow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mPaint.setAntiAlias(true);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LongShadow);
            setShadowColor(a.getColor(R.styleable.LongShadow_shadow_color, DEFAULT_SHADOW_COLOR));
            setShadowAngle(a.getInt(R.styleable.LongShadow_shadow_angle, DEFAULT_SHADOW_ANGLE));
            a.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                postInvalidate();
                getViewTreeObserver().removeOnPreDrawListener(this);
                return false;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(w != 0 && h != 0) {
            createBitmap();
        }
    }

    private void createBitmap() {
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    public int getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(int value) {
        if (shadowColor != value) {
            shadowColor = value;
            shadowAlpha = Color.alpha(shadowColor);
            //mPaint.setAlpha((int) (shadowAlpha * 255));
            mPaint.setColor(shadowColor);
            mPaint.setColorFilter(new PorterDuffColorFilter(shadowColor, PorterDuff.Mode.SRC_IN));
            postInvalidate();
        }
    }

    public float getShadowAngle() {
        return shadowAngle;
    }

    public void setShadowAngle(float value) {
        if (shadowAngle != value) {
            this.shadowAngle = value % 360;
            postInvalidate();
        }
    }

    public boolean drawChildrenOnBitmap() {
        if (bitmap != null) {
            final int childCount = getChildCount();
            for (int i = 0; i < childCount; ++i) {
                final View v = getChildAt(i);
                canvas.save();
                canvas.translate(v.getLeft(), v.getTop());
                v.draw(canvas);
                canvas.restore();
            }
            return true;
        }
        return false;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (drawChildrenOnBitmap()) {
            double completeX;
            if (shadowAngle != 0) {
                completeX = getHeight() / Math.tan(Math.toRadians(shadowAngle));
            } else {
                completeX = 0;
            }

            float pas = 1;
            if (shadowAngle < 5 || (shadowAngle > 175 && shadowAngle < 185) || (shadowAngle > 355 && shadowAngle < 360)) {
                pas = 0.1f;
            }

            if (shadowAngle == 0) {
                for (int x = 0; x < getWidth(); ++x) {
                    canvas.drawBitmap(bitmap, x, 0, mPaint);
                }
            } else if (shadowAngle == 180) {
                for (int x = getWidth(); x > 0; --x) {
                    canvas.drawBitmap(bitmap, -x, 0, mPaint);
                }
            } else if (shadowAngle > 180) {
                for (float y = getHeight(); y >= 0; y-= pas) {
                    double percent = y * 1f / getHeight();
                    float x = -1 * (float) (completeX * percent);
                    canvas.drawBitmap(bitmap, x, -y, mPaint);
                }
            } else {
                for (float y = 0; y < getHeight(); y+= pas) {
                    double percent = y * 1f / getHeight();
                    float x = (float) (completeX * percent);
                    canvas.drawBitmap(bitmap, x, y, mPaint);
                }
            }
        }
        super.onDraw(canvas);
    }
}
