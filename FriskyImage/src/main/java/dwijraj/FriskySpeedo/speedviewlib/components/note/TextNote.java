package dwijraj.FriskySpeedo.speedviewlib.components.note;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

public class TextNote extends Note<TextNote> {

    private CharSequence noteText;
    private TextPaint notePaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private float textSize = notePaint.getTextSize();
    private StaticLayout textLayout;
    public TextNote(Context context, CharSequence noteText) {
        super(context);
        if (noteText == null)
            throw new IllegalArgumentException("noteText cannot be null.");
        this.noteText = noteText;
        notePaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    public void build(int viewWidth) {
        textLayout = new StaticLayout(noteText, notePaint, viewWidth
                , Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        int w = 0;
        for (int i = 0; i< textLayout.getLineCount(); i++)
            w = (int) Math.max(w, textLayout.getLineWidth(i));
        noticeContainsSizeChange(w, textLayout.getHeight());
    }

    @Override
    protected void drawContains(Canvas canvas, float leftX, float topY) {
        canvas.save();
        canvas.translate(leftX, topY);
        textLayout.draw(canvas);
        canvas.restore();
    }

    public float getTextSize() {
        return textSize;
    }

    public TextNote setTextSize(float textSize) {
        this.textSize = textSize;
        notePaint.setTextSize(textSize);
        return this;
    }
    public TextNote setTextTypeFace(Typeface typeface) {
        notePaint.setTypeface(typeface);
        return this;
    }

    public int getTextColor() {
        return notePaint.getColor();
    }
    public TextNote setTextColor(int textColor) {
        notePaint.setColor(textColor);
        return this;
    }
}
