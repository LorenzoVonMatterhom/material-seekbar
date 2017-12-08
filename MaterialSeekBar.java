package com.yourname.yourapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.Animatable2Compat;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SeekBar;

/**
 * Created by IlToro on 06/12/2017.
 */

public class MaterialSeekBar extends android.support.v7.widget.AppCompatSeekBar {
    private TextPaint mTextPaint;
    private boolean isTextVisible;
    AnimatedVectorDrawableCompat timeLimitPassedMoreTimeSeekBarThumb;
    AnimatedVectorDrawableCompat timeLimitPassedMoreTimeSeekBarThumbBackwards;

    public MaterialSeekBar(Context context) {
        super(context);
        mTextPaint = new TextPaint();
        mTextPaint.setColor(getResources().getColor(R.color.white));
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.album_cover_height));
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public MaterialSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTextPaint = new TextPaint();
        mTextPaint.setColor(getResources().getColor(R.color.white));
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.album_cover_height));
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public MaterialSeekBar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        isTextVisible = true;
        mTextPaint = new TextPaint();
        mTextPaint.setColor(getResources().getColor(R.color.pink_800));
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.seekbar_thumb_text));
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        final SeekBar thisSeekbar = this;
        timeLimitPassedMoreTimeSeekBarThumb = AnimatedVectorDrawableCompat.create(context, R.drawable.seekbar_thumb_final);
        timeLimitPassedMoreTimeSeekBarThumbBackwards = AnimatedVectorDrawableCompat.create(context, R.drawable.seekbar_thumb_backwards_final);
        timeLimitPassedMoreTimeSeekBarThumbBackwards.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
                isTextVisible = false;
                Log.i("UNUSEFULLOG", "AnimationBackwards:FALSE");
            }
        });
        timeLimitPassedMoreTimeSeekBarThumb.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                if (thisSeekbar.getThumb() == timeLimitPassedMoreTimeSeekBarThumb) {
                    isTextVisible = true;
                    Log.i("UNUSEFULLOG", "AnimationForwards:TRUE");
                }
            }
        });
        this.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                thisSeekbar.setThumb(timeLimitPassedMoreTimeSeekBarThumb);
                timeLimitPassedMoreTimeSeekBarThumb.start();
                Log.i("UNUSEFULLOG", "StartTrackingTouch:");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                thisSeekbar.setThumb(timeLimitPassedMoreTimeSeekBarThumbBackwards);
                timeLimitPassedMoreTimeSeekBarThumbBackwards.start();
                isTextVisible = false;
                Log.i("UNUSEFULLOG", "StopTrackingTouch:FALSE");
            }
        });
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String progressText = (String)/*Write your logic here to change the value format (%, h-m-s, $...)*/getProgress();
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(progressText, 0, progressText.length(), bounds);

        int width = getWidth()-getPaddingLeft()-getPaddingRight();
        float normalizedProgress = (float) getProgress()/getMax();
        float thumbX = getPaddingLeft()+width*normalizedProgress;
        float thumbY = this.getHeight()-5*getResources().getDimensionPixelSize(R.dimen.seekbar_thumb_text);
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.seekbar_thumb_text));
        if (isTextVisible) {
            canvas.drawText(progressText, thumbX, thumbY, mTextPaint);
        }
    }
}
