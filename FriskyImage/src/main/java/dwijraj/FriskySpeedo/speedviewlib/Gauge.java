package dwijraj.FriskySpeedo.speedviewlib;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.example.crazyimage.R;

import java.util.Locale;
import java.util.Random;

import dwijraj.FriskySpeedo.speedviewlib.util.OnSectionChangeListener;
import dwijraj.FriskySpeedo.speedviewlib.util.OnSpeedChangeListener;

@SuppressWarnings("unused")
public abstract class Gauge extends View {

    private Paint speedUnitTextBitmapPaint =  new Paint(Paint.ANTI_ALIAS_FLAG);
    protected TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private TextPaint speedTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG),
            unitTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private String unit = "Km/h";
    private boolean withTremble = true;

   private float maxSpeed = 100f;
    private float minSpeed = 0f;

    private float speed = minSpeed;
    private int currentIntSpeed = 0;
    private float currentSpeed = minSpeed;
    private boolean isSpeedIncrease = false;
    private float trembleDegree = 4f;
    private int trembleDuration = 1000;

    private ValueAnimator speedAnimator, trembleAnimator, realSpeedAnimator;
    private boolean canceled = false;
    private OnSpeedChangeListener onSpeedChangeListener;
    private OnSectionChangeListener onSectionChangeListener;
    private Animator.AnimatorListener animatorListener;

    protected Bitmap backgroundBitmap;
    private Paint backgroundBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int padding = 0;
    private int widthPa = 0;
    private int heightPa = 0;

    private int lowSpeedPercent = 60;
    private int mediumSpeedPercent = 87;

    public static final byte LOW_SECTION = 1;
    public static final byte MEDIUM_SECTION = 2;
    public static final byte HIGH_SECTION = 3;
    private byte section = LOW_SECTION;

    private boolean speedometerTextRightToLeft = false;

    private boolean attachedToWindow = false;

    protected float translatedDx = 0;
    protected float translatedDy = 0;

    private Locale locale = Locale.getDefault();

    private float accelerate = .1f;
    private float decelerate = .1f;

    private Position speedTextPosition = Position.BOTTOM_CENTER;
    private float unitSpeedInterval = dpTOpx(1);
    private float speedTextPadding = dpTOpx(20f);
    private boolean unitUnderSpeedText = false;
    private Bitmap speedUnitTextBitmap;
    private Canvas speedUnitTextCanvas;

    public static final byte INTEGER_FORMAT = 0;
    public static final byte FLOAT_FORMAT = 1;
    private byte speedTextFormat = FLOAT_FORMAT;
    private byte tickTextFormat = INTEGER_FORMAT;

    public Gauge(Context context) {
        this(context, null);
    }

    public Gauge(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Gauge(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initAttributeSet(context, attrs);
        initAttributeValue();
    }

    private void init() {
        textPaint.setColor(0xFF000000);
        textPaint.setTextSize(dpTOpx(10f));
        textPaint.setTextAlign(Paint.Align.CENTER);
        speedTextPaint.setColor(0xFF000000);
        speedTextPaint.setTextSize(dpTOpx(18f));
        unitTextPaint.setColor(0xFF000000);
        unitTextPaint.setTextSize(dpTOpx(15f));

        if (Build.VERSION.SDK_INT >= 11) {
            speedAnimator = ValueAnimator.ofFloat(0f, 1f);
            trembleAnimator = ValueAnimator.ofFloat(0f, 1f);
            realSpeedAnimator = ValueAnimator.ofFloat(0f, 1f);
            animatorListener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (!canceled)
                        tremble();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }
            };
        }
        defaultGaugeValues();
    }

    private void initAttributeSet(Context context, AttributeSet attrs) {
        if (attrs == null)
            return;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Gauge, 0, 0);

        maxSpeed = a.getFloat(R.styleable.Gauge_sv_maxSpeed, maxSpeed);
        minSpeed = a.getFloat(R.styleable.Gauge_sv_minSpeed, minSpeed);
        speed = minSpeed;
        currentSpeed = minSpeed;
        withTremble = a.getBoolean(R.styleable.Gauge_sv_withTremble, withTremble);
        textPaint.setColor(a.getColor(R.styleable.Gauge_sv_textColor, textPaint.getColor()));
        textPaint.setTextSize(a.getDimension(R.styleable.Gauge_sv_textSize, textPaint.getTextSize()));
        speedTextPaint.setColor(a.getColor(R.styleable.Gauge_sv_speedTextColor, speedTextPaint.getColor()));
        speedTextPaint.setTextSize(a.getDimension(R.styleable.Gauge_sv_speedTextSize, speedTextPaint.getTextSize()));
        unitTextPaint.setColor(a.getColor(R.styleable.Gauge_sv_unitTextColor, unitTextPaint.getColor()));
        unitTextPaint.setTextSize(a.getDimension(R.styleable.Gauge_sv_unitTextSize, unitTextPaint.getTextSize()));
        String unit = a.getString(R.styleable.Gauge_sv_unit);
        this.unit =  (unit != null) ? unit : this.unit;
        trembleDegree = a.getFloat(R.styleable.Gauge_sv_trembleDegree, trembleDegree);
        trembleDuration = a.getInt(R.styleable.Gauge_sv_trembleDuration, trembleDuration);
        lowSpeedPercent = a.getInt(R.styleable.Gauge_sv_lowSpeedPercent, lowSpeedPercent);
        mediumSpeedPercent = a.getInt(R.styleable.Gauge_sv_mediumSpeedPercent, mediumSpeedPercent);
        speedometerTextRightToLeft = a.getBoolean(R.styleable.Gauge_sv_textRightToLeft, speedometerTextRightToLeft);
        accelerate = a.getFloat(R.styleable.Gauge_sv_accelerate, accelerate);
        decelerate = a.getFloat(R.styleable.Gauge_sv_decelerate, decelerate);
        unitUnderSpeedText = a.getBoolean(R.styleable.Gauge_sv_unitUnderSpeedText, unitUnderSpeedText);
        unitSpeedInterval = a.getDimension(R.styleable.Gauge_sv_unitSpeedInterval, unitSpeedInterval);
        speedTextPadding = a.getDimension(R.styleable.Gauge_sv_speedTextPadding, speedTextPadding);
        String speedTypefacePath = a.getString(R.styleable.Gauge_sv_speedTextTypeface);
        if (speedTypefacePath != null)
            setSpeedTextTypeface(Typeface.createFromAsset(getContext().getAssets(), speedTypefacePath));
        String typefacePath = a.getString(R.styleable.Gauge_sv_textTypeface);
        if (typefacePath != null)
            setTextTypeface(Typeface.createFromAsset(getContext().getAssets(), typefacePath));
        int position = a.getInt(R.styleable.Gauge_sv_speedTextPosition, -1);
        if (position != -1)
            setSpeedTextPosition(Position.values()[position]);
        byte speedFormat = (byte) a.getInt(R.styleable.Gauge_sv_speedTextFormat, -1);
        if (speedFormat != -1)
            setSpeedTextFormat(speedFormat);
        byte tickFormat = (byte) a.getInt(R.styleable.Gauge_sv_tickTextFormat, -1);
        if (tickFormat != -1)
            setTickTextFormat(tickFormat);
        a.recycle();
        checkSpeedometerPercent();
        checkAccelerate();
        checkDecelerate();
        checkTrembleData();
    }

    private void initAttributeValue() {
        if (unitUnderSpeedText) {
            speedTextPaint.setTextAlign(Paint.Align.CENTER);
            unitTextPaint.setTextAlign(Paint.Align.CENTER);
        }
        else {
            speedTextPaint.setTextAlign(Paint.Align.LEFT);
            unitTextPaint.setTextAlign(Paint.Align.LEFT);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        if (widthPa > 0 && heightPa > 0)
            speedUnitTextBitmap = Bitmap.createBitmap(widthPa
                    , heightPa, Bitmap.Config.ARGB_8888);
        speedUnitTextCanvas = new Canvas(speedUnitTextBitmap);
    }

    private void checkSpeedometerPercent() {
        if (lowSpeedPercent > mediumSpeedPercent)
            throw new IllegalArgumentException("lowSpeedPercent must be smaller than mediumSpeedPercent");
        if (lowSpeedPercent > 100 || lowSpeedPercent < 0)
            throw new IllegalArgumentException("lowSpeedPercent must be between [0, 100]");
        if (mediumSpeedPercent > 100 || mediumSpeedPercent < 0)
            throw new IllegalArgumentException("mediumSpeedPercent must be between [0, 100]");
    }

    private void checkAccelerate() {
        if (accelerate > 1f || accelerate <= 0)
            throw new IllegalArgumentException("accelerate must be between (0, 1]");
    }

    private void checkDecelerate() {
        if (decelerate > 1f || decelerate <= 0)
            throw new IllegalArgumentException("decelerate must be between (0, 1]");
    }

    private void checkTrembleData() {
        if (trembleDegree < 0)
            throw new IllegalArgumentException("trembleDegree  can't be Negative");
        if (trembleDuration < 0)
            throw new IllegalArgumentException("trembleDuration  can't be Negative");
    }


    public float dpTOpx(float dp) {
        return dp * getContext().getResources().getDisplayMetrics().density;
    }


    public float pxTOdp(float px) {
        return px / getContext().getResources().getDisplayMetrics().density;
    }


    abstract protected void defaultGaugeValues();

    abstract protected void updateBackgroundBitmap();


    private void updatePadding(int left, int top, int right, int bottom) {
        padding = Math.max(Math.max(left, right), Math.max(top, bottom));
        widthPa  = getWidth() - padding*2;
        heightPa = getHeight() - padding*2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(translatedDx, translatedDy);

        if (backgroundBitmap != null)
            canvas.drawBitmap(backgroundBitmap, 0f, 0f, backgroundBitmapPaint);

        // check onSpeedChangeEvent.
        int newSpeed = (int) currentSpeed;
        if (newSpeed != currentIntSpeed && onSpeedChangeListener != null) {
            boolean byTremble = Build.VERSION.SDK_INT >= 11 && trembleAnimator.isRunning();
            boolean isSpeedUp = newSpeed > currentIntSpeed;
            int update = isSpeedUp ? 1 : -1;
            // this loop to pass on all speed values,
            // to safe handle by call gauge.getCorrectIntSpeed().
            while (currentIntSpeed != newSpeed) {
                currentIntSpeed += update;
                onSpeedChangeListener.onSpeedChange(this, isSpeedUp, byTremble);
            }
        }
        currentIntSpeed = newSpeed;

        // check onSectionChangeEvent.
        byte newSection = getSection();
        if (section != newSection)
            onSectionChangeEvent(section, newSection);
        section = newSection;
    }


    protected void drawSpeedUnitText(Canvas canvas) {
        RectF r = getSpeedUnitTextBounds();
        updateSpeedUnitTextBitmap(getSpeedText());
        canvas.drawBitmap(speedUnitTextBitmap, r.left - speedUnitTextBitmap.getWidth() *.5f + r.width() *.5f
                , r.top - speedUnitTextBitmap.getHeight() *.5f + r.height() *.5f, speedUnitTextBitmapPaint);
    }


    private void updateSpeedUnitTextBitmap(String speedText) {
        speedUnitTextBitmap.eraseColor(0);

        if (unitUnderSpeedText) {
            speedUnitTextCanvas.drawText(speedText, speedUnitTextBitmap.getWidth() *.5f
                    , speedUnitTextBitmap.getHeight() *.5f  - unitSpeedInterval *.5f, speedTextPaint);
            speedUnitTextCanvas.drawText(unit, speedUnitTextBitmap.getWidth() *.5f
                    , speedUnitTextBitmap.getHeight() *.5f + unitTextPaint.getTextSize() + unitSpeedInterval *.5f, unitTextPaint);
        }
        else {
            float speedX;
            float unitX;
            if (isSpeedometerTextRightToLeft()) {
                unitX = speedUnitTextBitmap.getWidth() *.5f - getSpeedUnitTextWidth() *.5f;
                speedX = unitX + unitTextPaint.measureText(unit) + unitSpeedInterval;
            }
            else {
                speedX = speedUnitTextBitmap.getWidth() *.5f - getSpeedUnitTextWidth() *.5f;
                unitX = speedX + speedTextPaint.measureText(speedText) + unitSpeedInterval;
            }
            float h = speedUnitTextBitmap.getHeight() *.5f + getSpeedUnitTextHeight() *.5f;
            speedUnitTextCanvas.drawText(speedText, speedX, h, speedTextPaint);
            speedUnitTextCanvas.drawText(unit, unitX, h, unitTextPaint);
        }
    }

    protected RectF getSpeedUnitTextBounds() {
        float left = getWidthPa()*speedTextPosition.x -translatedDx + padding
                - getSpeedUnitTextWidth()*speedTextPosition.width
                + speedTextPadding*speedTextPosition.paddingH;
        float top = getHeightPa()*speedTextPosition.y -translatedDy + padding
                - getSpeedUnitTextHeight()*speedTextPosition.height
                + speedTextPadding*speedTextPosition.paddingV;
        return new RectF(left, top, left + getSpeedUnitTextWidth(), top + getSpeedUnitTextHeight());
    }

    private float getSpeedUnitTextWidth() {
        return unitUnderSpeedText ?
                Math.max(speedTextPaint.measureText(getSpeedText()), unitTextPaint.measureText(getUnit()))
                : speedTextPaint.measureText(getSpeedText()) + unitTextPaint.measureText(getUnit()) + unitSpeedInterval;
    }


    private float getSpeedUnitTextHeight() {
        return unitUnderSpeedText ?
                speedTextPaint.getTextSize() + unitTextPaint.getTextSize() + unitSpeedInterval
                : Math.max(speedTextPaint.getTextSize(), unitTextPaint.getTextSize());
    }


    protected Canvas createBackgroundBitmapCanvas() {
        if (getWidth() == 0 || getHeight() == 0)
            return new Canvas();
        backgroundBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        return new Canvas(backgroundBitmap);
    }

    protected void onSectionChangeEvent(byte oldSection, byte newSection) {
        if (onSectionChangeListener != null)
            onSectionChangeListener.onSectionChangeListener(oldSection, newSection);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void stop() {
        if (Build.VERSION.SDK_INT < 11)
            return;
        if (!speedAnimator.isRunning() && !realSpeedAnimator.isRunning())
            return;
        speed = currentSpeed;
        cancelSpeedAnimator();
        tremble();
    }


    protected void cancelSpeedAnimator() {
        cancelSpeedMove();
        cancelTremble();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void cancelTremble() {
        if (Build.VERSION.SDK_INT < 11)
            return;
        canceled = true;
        trembleAnimator.cancel();
        canceled = false;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void cancelSpeedMove() {
        if (Build.VERSION.SDK_INT < 11)
            return;
        canceled = true;
        speedAnimator.cancel();
        realSpeedAnimator.cancel();
        canceled = false;
    }


    public void setSpeedAt(float speed) {
        speed = (speed > maxSpeed) ? maxSpeed : (speed < minSpeed) ? minSpeed : speed;
        isSpeedIncrease = speed > currentSpeed;
        this.speed = speed;
        this.currentSpeed = speed;
        cancelSpeedAnimator();
        invalidate();
        tremble();
    }


    public void speedPercentTo(int percent) {
        speedPercentTo(percent, 2000);
    }


    public void speedPercentTo(int percent, long moveDuration) {
        speedTo(getSpeedValue(percent), moveDuration);
    }


    public void speedTo(float speed) {
        speedTo(speed, 2000);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void speedTo(float speed, long moveDuration) {
        speed = (speed > maxSpeed) ? maxSpeed : (speed < minSpeed) ? minSpeed : speed;
        if (speed == this.speed)
            return;
        this.speed = speed;

        if (Build.VERSION.SDK_INT < 11){
            setSpeedAt(speed);
            return;
        }

        isSpeedIncrease = speed > currentSpeed;

        cancelSpeedAnimator();
        speedAnimator = ValueAnimator.ofFloat(currentSpeed, speed);
        speedAnimator.setInterpolator(new DecelerateInterpolator());
        speedAnimator.setDuration(moveDuration);
        speedAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentSpeed = (float) speedAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        speedAnimator.addListener(animatorListener);
        speedAnimator.start();
    }


    public void speedUp() {
        realSpeedTo(getMaxSpeed());
    }


    public void slowDown() {
        realSpeedTo(0);
    }


    public void realSpeedPercentTo(float percent) {
        realSpeedTo(getSpeedValue(percent));
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void realSpeedTo(float speed) {
        boolean oldIsSpeedUp = this.speed > currentSpeed;
        speed = (speed > maxSpeed) ? maxSpeed : (speed < minSpeed) ? minSpeed : speed;
        if (speed == this.speed)
            return;
        this.speed = speed;

        if (Build.VERSION.SDK_INT < 11) {
            setSpeedAt(speed);
            return;
        }
        isSpeedIncrease = speed > currentSpeed;
        if (realSpeedAnimator.isRunning() && oldIsSpeedUp == isSpeedIncrease)
            return;

        cancelSpeedAnimator();
        realSpeedAnimator = ValueAnimator.ofInt((int) currentSpeed, (int)speed);
        realSpeedAnimator.setRepeatCount(ValueAnimator.INFINITE);
        realSpeedAnimator.setInterpolator(new LinearInterpolator());
        realSpeedAnimator.setDuration(Math.abs((long) ((speed - currentSpeed) * 10) ));
        final float finalSpeed = speed;
        realSpeedAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (isSpeedIncrease) {
                    float per = 100.005f-getPercentSpeed();
                    currentSpeed += (accelerate * 10f) * per *.01f;
                    if (currentSpeed > finalSpeed)
                        currentSpeed = finalSpeed;
                }
                else {
                    float per = getPercentSpeed()+.005f;
                    currentSpeed -= (decelerate * 10f) * per *.01f +.1f;
                    if (currentSpeed < finalSpeed)
                        currentSpeed = finalSpeed;
                }
                postInvalidate();
                if (finalSpeed == currentSpeed)
                    stop();
            }
        });
        realSpeedAnimator.addListener(animatorListener);
        realSpeedAnimator.start();
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void tremble() {
        cancelTremble();
        if (!isWithTremble() || Build.VERSION.SDK_INT < 11)
            return;
        Random random = new Random();
        float mad = trembleDegree * random.nextFloat() * ((random.nextBoolean()) ? -1 :1);
        mad = (speed + mad > maxSpeed) ? maxSpeed - speed
                : (speed + mad < minSpeed) ? minSpeed - speed : mad;
        trembleAnimator = ValueAnimator.ofFloat(currentSpeed, speed + mad);
        trembleAnimator.setInterpolator(new DecelerateInterpolator());
        trembleAnimator.setDuration(trembleDuration);
        trembleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                isSpeedIncrease = (float) trembleAnimator.getAnimatedValue() > currentSpeed;
                currentSpeed = (float) trembleAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        trembleAnimator.addListener(animatorListener);
        trembleAnimator.start();
    }


    private float getSpeedValue(float percentSpeed) {
        percentSpeed = (percentSpeed > 100) ? 100 : (percentSpeed < 0) ? 0 : percentSpeed;
        return percentSpeed * (maxSpeed - minSpeed) *.01f + minSpeed;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        attachedToWindow = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelSpeedAnimator();
        attachedToWindow = false;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putFloat("speed", speed);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        speed = bundle.getFloat("speed");
        state = bundle.getParcelable("superState");
        super.onRestoreInstanceState(state);
        setSpeedAt(speed);
    }



    public void setTrembleDegree (float trembleDegree) {
        setTrembleData(trembleDegree, trembleDuration);
    }


    public void setTrembleDuration (int trembleDuration) {
        setTrembleData(trembleDegree, trembleDuration);
    }


    public void setTrembleData (float trembleDegree, int trembleDuration) {
        this.trembleDegree = trembleDegree;
        this.trembleDuration = trembleDuration;
        checkTrembleData();
    }


    public byte getSpeedTextFormat() {
        return speedTextFormat;
    }


    public void setSpeedTextFormat(byte speedTextFormat) {
        this.speedTextFormat = speedTextFormat;
        if (!attachedToWindow)
            return;
        updateBackgroundBitmap();
        invalidate();
    }


    public byte getTickTextFormat() {
        return tickTextFormat;
    }


    public void setTickTextFormat(byte tickTextFormat) {
        this.tickTextFormat = tickTextFormat;
        if (!attachedToWindow)
            return;
        updateBackgroundBitmap();
        invalidate();
    }

    protected String getSpeedText() {
        return speedTextFormat == FLOAT_FORMAT ? String.format(locale, "%.1f", currentSpeed)
                : String.format(locale, "%d", currentIntSpeed);
    }


    protected String getMaxSpeedText() {
        return tickTextFormat == FLOAT_FORMAT ? String.format(locale, "%.1f", maxSpeed)
                : String.format(locale, "%d", (int) maxSpeed);
    }


    protected String getMinSpeedText() {
        return tickTextFormat == FLOAT_FORMAT ? String.format(locale, "%.1f", minSpeed)
                : String.format(locale, "%d", (int) minSpeed);
    }


    public void setWithTremble(boolean withTremble) {
        this.withTremble = withTremble;
        tremble();
    }


    public boolean isWithTremble() {
        return withTremble;
    }


    public float getSpeed() {
        return speed;
    }


    public float getCurrentSpeed() {
        return currentSpeed;
    }


    public boolean isSpeedIncrease() {
        return isSpeedIncrease;
    }


    public int getCurrentIntSpeed() {
        return currentIntSpeed;
    }


    public float getMaxSpeed() {
        return maxSpeed;
    }


    public void setMaxSpeed(float maxSpeed) {
        setMinMaxSpeed(minSpeed, maxSpeed);
    }


    public float getMinSpeed() {
        return minSpeed;
    }


    public void setMinSpeed(float minSpeed) {
        setMinMaxSpeed(minSpeed, maxSpeed);
    }


    public void setMinMaxSpeed(float minSpeed, float maxSpeed) {
        if (minSpeed >= maxSpeed)
            throw new IllegalArgumentException("minSpeed must be smaller than maxSpeed !!");
        cancelSpeedAnimator();
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        if (!attachedToWindow)
            return;
        updateBackgroundBitmap();
        setSpeedAt(speed);
    }


    public float getPercentSpeed() {
        return (currentSpeed - minSpeed) * 100f / (maxSpeed - minSpeed);
    }


    public float getOffsetSpeed() {
        return (currentSpeed - minSpeed) / (maxSpeed - minSpeed);
    }


    public int getTextColor() {
        return textPaint.getColor();
    }


    public void setTextColor(int textColor) {
        textPaint.setColor(textColor);
        if (!attachedToWindow)
            return;
        updateBackgroundBitmap();
        invalidate();
    }


    public int getSpeedTextColor() {
        return speedTextPaint.getColor();
    }


    public void setSpeedTextColor(int speedTextColor) {
        speedTextPaint.setColor(speedTextColor);
        if (!attachedToWindow)
            return;
        invalidate();
    }


    public int getUnitTextColor() {
        return unitTextPaint.getColor();
    }


    public void setUnitTextColor(int unitTextColor) {
        unitTextPaint.setColor(unitTextColor);
        if (!attachedToWindow)
            return;
        invalidate();
    }


    public float getTextSize() {
        return textPaint.getTextSize();
    }


    public void setTextSize(float textSize) {
        textPaint.setTextSize(textSize);
        if (!attachedToWindow)
            return;
        invalidate();
    }


    public float getSpeedTextSize() {
        return speedTextPaint.getTextSize();
    }


    public void setSpeedTextSize(float speedTextSize) {
        speedTextPaint.setTextSize(speedTextSize);
        if (!attachedToWindow)
            return;
        invalidate();
    }


    public float getUnitTextSize() {
        return unitTextPaint.getTextSize();
    }


    public void setUnitTextSize(float unitTextSize) {
        unitTextPaint.setTextSize(unitTextSize);
        if (!attachedToWindow)
            return;
        updateBackgroundBitmap();
        invalidate();
    }


    public String getUnit() {
        return unit;
    }


    public void setUnit(String unit) {
        this.unit = unit;
        if (!attachedToWindow)
            return;
        invalidate();
    }


    public void setOnSpeedChangeListener(OnSpeedChangeListener onSpeedChangeListener) {
        this.onSpeedChangeListener = onSpeedChangeListener;
    }


    public void setOnSectionChangeListener(OnSectionChangeListener onSectionChangeListener) {
        this.onSectionChangeListener = onSectionChangeListener;
    }


    public int getLowSpeedPercent() {
        return lowSpeedPercent;
    }


    public float getLowSpeedOffset() {
        return lowSpeedPercent *.01f;
    }


    public void setLowSpeedPercent(int lowSpeedPercent) {
        this.lowSpeedPercent = lowSpeedPercent;
        checkSpeedometerPercent();
        if (!attachedToWindow)
            return;
        updateBackgroundBitmap();
        invalidate();
    }


    public int getMediumSpeedPercent() {
        return mediumSpeedPercent;
    }

    public float getMediumSpeedOffset() {
        return mediumSpeedPercent *.01f;
    }


    public void setMediumSpeedPercent(int mediumSpeedPercent) {
        this.mediumSpeedPercent = mediumSpeedPercent;
        checkSpeedometerPercent();
        if (!attachedToWindow)
            return;
        updateBackgroundBitmap();
        invalidate();
    }


    public boolean isSpeedometerTextRightToLeft() {
        return speedometerTextRightToLeft;
    }


    public void setSpeedometerTextRightToLeft(boolean speedometerTextRightToLeft) {
        this.speedometerTextRightToLeft = speedometerTextRightToLeft;
        if (!attachedToWindow)
            return;
        updateBackgroundBitmap();
        invalidate();
    }


    public int getWidthPa() {
        return widthPa;
    }


    public int getHeightPa() {
        return heightPa;
    }

    public int getViewSize() {
        return Math.max(getWidth(), getHeight());
    }

    public int getViewSizePa() {
        return Math.max(widthPa, heightPa);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        updatePadding(left, top, right, bottom);
        super.setPadding(padding, padding, padding, padding);
    }

    @Override
    public void setPaddingRelative(int start, int top, int end, int bottom) {
        updatePadding(start, top, end, bottom);
        super.setPaddingRelative(padding, padding, padding, padding);
    }


    public Locale getLocale() {
        return locale;
    }


    public void setLocale(Locale locale) {
        this.locale = locale;
        if (!attachedToWindow)
            return;
        invalidate();
    }


    public boolean isInLowSection() {
        return (maxSpeed - minSpeed)*getLowSpeedOffset() + minSpeed >= currentSpeed;
    }


    public boolean isInMediumSection() {
        return (maxSpeed - minSpeed)*getMediumSpeedOffset() + minSpeed >= currentSpeed && !isInLowSection();
    }


    public boolean isInHighSection() {
        return currentSpeed > (maxSpeed - minSpeed)*getMediumSpeedOffset() + minSpeed;
    }


    public byte getSection() {
        if (isInLowSection())
            return LOW_SECTION;
        else if (isInMediumSection())
            return MEDIUM_SECTION;
        else
            return HIGH_SECTION;
    }

    public int getPadding() {
        return padding;
    }


    public boolean isAttachedToWindow() {
        return attachedToWindow;
    }


    public Typeface getSpeedTextTypeface() {
        return speedTextPaint.getTypeface();
    }


    public void setSpeedTextTypeface(Typeface typeface) {
        speedTextPaint.setTypeface(typeface);
        unitTextPaint.setTypeface(typeface);
        if (!attachedToWindow)
            return;
        updateBackgroundBitmap();
        invalidate();
    }


    public Typeface getTextTypeface() {
        return textPaint.getTypeface();
    }


    public void setTextTypeface(Typeface typeface) {
        textPaint.setTypeface(typeface);
        if (!attachedToWindow)
            return;
        updateBackgroundBitmap();
        invalidate();
    }


    public float getAccelerate() {
        return accelerate;
    }


    public void setAccelerate(float accelerate) {
        this.accelerate = accelerate;
        checkAccelerate();
    }


    public float getDecelerate() {
        return decelerate;
    }


    public void setDecelerate(float decelerate) {
        this.decelerate = decelerate;
    }


    protected final float getTranslatedDx() {
        return translatedDx;
    }


    protected final float getTranslatedDy() {
        return translatedDy;
    }


    public float getUnitSpeedInterval() {
        return unitSpeedInterval;
    }


    public void setUnitSpeedInterval(float unitSpeedInterval) {
        this.unitSpeedInterval = unitSpeedInterval;
        if (!attachedToWindow)
            return;
        updateBackgroundBitmap();
        invalidate();
    }


    public float getSpeedTextPadding() {
        return speedTextPadding;
    }


    public void setSpeedTextPadding(float speedTextPadding) {
        this.speedTextPadding = speedTextPadding;
        if (!attachedToWindow)
            return;
        invalidate();
    }


    public boolean isUnitUnderSpeedText() {
        return unitUnderSpeedText;
    }


    public void setUnitUnderSpeedText(boolean unitUnderSpeedText) {
        this.unitUnderSpeedText = unitUnderSpeedText;
        if (unitUnderSpeedText) {
            speedTextPaint.setTextAlign(Paint.Align.CENTER);
            unitTextPaint.setTextAlign(Paint.Align.CENTER);
        }
        else {
            speedTextPaint.setTextAlign(Paint.Align.LEFT);
            unitTextPaint.setTextAlign(Paint.Align.LEFT);
        }
        if (!attachedToWindow)
            return;
        updateBackgroundBitmap();
        invalidate();
    }


    public void setSpeedTextPosition (Position position) {
        this.speedTextPosition = position;
        if(!attachedToWindow)
            return;
        updateBackgroundBitmap();
        invalidate();
    }

    public enum Position {
        TOP_LEFT        (0f  , 0f  , 0f  , 0f  , 1  , 1 )
        , TOP_CENTER    (.5f , 0f  , .5f , 0f  , 0  , 1 )
        , TOP_RIGHT     (1f  , 0f  , 1f  , 0f  , -1 , 1 )
        , LEFT          (0f  , .5f , 0f  , .5f , 1  , 0 )
        , CENTER        (.5f , .5f , .5f , .5f , 0  , 0 )
        , RIGHT         (1f  , .5f , 1f  , .5f , -1 , 0 )
        , BOTTOM_LEFT   (0f  , 1f  , 0f  , 1f  , 1  , -1)
        , BOTTOM_CENTER (.5f , 1f  , .5f , 1f  , 0  , -1)
        , BOTTOM_RIGHT  (1f  , 1f  , 1f  , 1f  , -1 , -1);

        final float x;
        final float y;
        final float width;
        final float height;
        final int paddingH; // horizontal padding
        final int paddingV; // vertical padding

        Position(float x, float y, float width, float height, int paddingH, int paddingV) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.paddingH = paddingH;
            this.paddingV = paddingV;
        }
    }
}
