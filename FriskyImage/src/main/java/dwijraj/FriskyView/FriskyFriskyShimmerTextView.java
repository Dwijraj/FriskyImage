package dwijraj.FriskyView;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class FriskyFriskyShimmerTextView extends TextView implements FriskyShimmerViewBase {

    private FriskyShimmerViewHelper friskyShimmerViewHelper;

    public FriskyFriskyShimmerTextView(Context context) {
        super(context);
        friskyShimmerViewHelper = new FriskyShimmerViewHelper(this, getPaint(), null);
        friskyShimmerViewHelper.setPrimaryColor(getCurrentTextColor());
    }

    public FriskyFriskyShimmerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        friskyShimmerViewHelper = new FriskyShimmerViewHelper(this, getPaint(), attrs);
        friskyShimmerViewHelper.setPrimaryColor(getCurrentTextColor());
    }

    public FriskyFriskyShimmerTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        friskyShimmerViewHelper = new FriskyShimmerViewHelper(this, getPaint(), attrs);
        friskyShimmerViewHelper.setPrimaryColor(getCurrentTextColor());
    }

    @Override
    public float getGradientX() {
        return friskyShimmerViewHelper.getGradientX();
    }

    @Override
    public void setGradientX(float gradientX) {
        friskyShimmerViewHelper.setGradientX(gradientX);
    }

    @Override
    public boolean isShimmering() {
        return friskyShimmerViewHelper.isShimmering();
    }

    @Override
    public void setShimmering(boolean isShimmering) {
        friskyShimmerViewHelper.setShimmering(isShimmering);
    }

    @Override
    public boolean isSetUp() {
        return friskyShimmerViewHelper.isSetUp();
    }

    @Override
    public void setAnimationSetupCallback(FriskyShimmerViewHelper.AnimationSetupCallback callback) {
        friskyShimmerViewHelper.setAnimationSetupCallback(callback);
    }

    @Override
    public int getPrimaryColor() {
        return friskyShimmerViewHelper.getPrimaryColor();
    }

    @Override
    public void setPrimaryColor(int primaryColor) {
        friskyShimmerViewHelper.setPrimaryColor(primaryColor);
    }

    @Override
    public int getReflectionColor() {
        return friskyShimmerViewHelper.getReflectionColor();
    }

    @Override
    public void setReflectionColor(int reflectionColor) {
        friskyShimmerViewHelper.setReflectionColor(reflectionColor);
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
        if (friskyShimmerViewHelper != null) {
            friskyShimmerViewHelper.setPrimaryColor(getCurrentTextColor());
        }
    }

    @Override
    public void setTextColor(ColorStateList colors) {
        super.setTextColor(colors);
        if (friskyShimmerViewHelper != null) {
            friskyShimmerViewHelper.setPrimaryColor(getCurrentTextColor());
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (friskyShimmerViewHelper != null) {
            friskyShimmerViewHelper.onSizeChanged();
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (friskyShimmerViewHelper != null) {
            friskyShimmerViewHelper.onDraw();
        }
        super.onDraw(canvas);
    }
}
