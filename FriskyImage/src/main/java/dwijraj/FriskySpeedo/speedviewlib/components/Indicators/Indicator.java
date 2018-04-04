package dwijraj.FriskySpeedo.speedviewlib.components.Indicators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import dwijraj.FriskySpeedo.speedviewlib.Speedometer;

import static dwijraj.FriskySpeedo.speedviewlib.components.Indicators.Indicator.Indicators.NormalIndicator;

@SuppressWarnings("unchecked,unused,WeakerAccess")
public abstract class Indicator<I extends Indicator> {

    protected Paint indicatorPaint =  new Paint(Paint.ANTI_ALIAS_FLAG);
    private float density;
    private float indicatorWidth;
    private float viewSize;
    private float speedometerWidth;
    private int indicatorColor = 0xff2196F3;
    private int padding;
    private boolean inEditMode;

    protected Indicator (Context context) {
        this.density = context.getResources().getDisplayMetrics().density;
        init();
    }

    private void init() {
        indicatorPaint.setColor(indicatorColor);
        indicatorWidth = getDefaultIndicatorWidth();
    }

    public abstract void draw(Canvas canvas, float degree);
    protected abstract void updateIndicator();
    protected abstract void setWithEffects(boolean withEffects);
    protected abstract float getDefaultIndicatorWidth();

    public float getTop(){
        return getPadding();
    }
    public float getBottom(){
        return getCenterY();
    }
    public float getLightBottom() {
        return getCenterY() > getBottom() ? getBottom() : getCenterY();
    }

    public void onSizeChange(Speedometer speedometer) {
        setTargetSpeedometer(speedometer);
    }

    public void setTargetSpeedometer(Speedometer speedometer) {
        updateData(speedometer);
        updateIndicator();
    }

    private void updateData(Speedometer speedometer) {
        this.viewSize = speedometer.getSize();
        this.speedometerWidth = speedometer.getSpeedometerWidth();
        this.padding = speedometer.getPadding();
        this.inEditMode = speedometer.isInEditMode();
    }

    public float dpTOpx(float dp) {
        return dp * density;
    }

    public float getIndicatorWidth() {
        return indicatorWidth;
    }

    public I setIndicatorWidth(float indicatorWidth) {
        this.indicatorWidth = indicatorWidth;
        return (I) this;
    }


    public float getViewSize() {
        return viewSize - (padding*2f);
    }

    public int getIndicatorColor() {
        return indicatorColor;
    }

    public I setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
        return (I) this;
    }


    public float getCenterX() {
        return viewSize /2f;
    }


    public float getCenterY() {
        return viewSize /2f;
    }

    public int getPadding() {
        return padding;
    }

    public float getSpeedometerWidth() {
        return speedometerWidth;
    }

    public void noticeIndicatorWidthChange(float indicatorWidth) {
        this.indicatorWidth = indicatorWidth;
        updateIndicator();
    }

    public void noticeIndicatorColorChange(int indicatorColor) {
        this.indicatorColor = indicatorColor;
        updateIndicator();
    }

    public void noticeSpeedometerWidthChange(float speedometerWidth) {
        this.speedometerWidth = speedometerWidth;
        updateIndicator();
    }

    public void noticePaddingChange(int newPadding) {
        this.padding = newPadding;
        updateIndicator();
    }

    public void withEffects(boolean withEffects) {
        setWithEffects(withEffects);
        updateIndicator();
    }

    public boolean isInEditMode() {
        return inEditMode;
    }


    public enum Indicators {
        NoIndicator, NormalIndicator, NormalSmallIndicator, TriangleIndicator
        , SpindleIndicator, LineIndicator, HalfLineIndicator, QuarterLineIndicator
        , KiteIndicator, NeedleIndicator
    }


    public static Indicator createIndicator (Context context, Indicators indicator) {

                return new NormalIndicator(context);

    }
}
