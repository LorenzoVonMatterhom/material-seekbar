package com.yourname.yourapp;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.Animatable2Compat;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.cats.timemanager.utilities.TimeValueFormatter;

/**
 * Created by IlToro on 06/12/2017.
 */

public class MaterialSeekBar extends android.support.v7.widget.AppCompatSeekBar {
    private TextPaint mTextPaint;
    private boolean isTextVisible;
    private int multiplier = 1;
    private String progressText;
    private String formatter = "1";
    AnimatedVectorDrawableCompat timeLimitPassedMoreTimeSeekBarThumb;
    AnimatedVectorDrawableCompat timeLimitPassedMoreTimeSeekBarThumbBackwards;
    VectorDrawableCompat timeLimitPassedMoreTimeSeekBarThumbNotAnimated;
    final float scale = getContext().getResources().getDisplayMetrics().density;

    public MaterialSeekBar(Context context) {
        super(context);
        isTextVisible = false;
        mTextPaint = new TextPaint();
        mTextPaint.setColor(getResources().getColor(R.color.pink_800));
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.seekbar_thumb_text));
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        final SeekBar thisSeekbar = this;
        timeLimitPassedMoreTimeSeekBarThumbNotAnimated = VectorDrawableCompat.create(getResources(), R.drawable.seekbar_thumb_vector, null);
        timeLimitPassedMoreTimeSeekBarThumb = AnimatedVectorDrawableCompat.create(context, R.drawable.seekbar_thumb_animation);
        timeLimitPassedMoreTimeSeekBarThumb.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                if (thisSeekbar.getThumb() == timeLimitPassedMoreTimeSeekBarThumb) {
                    isTextVisible = true;
                    Log.i("CATs", "AnimationForwards:TRUE");
                }
            }
        });
        timeLimitPassedMoreTimeSeekBarThumbBackwards = AnimatedVectorDrawableCompat.create(context, R.drawable.seekbar_thumb_backwards_animation);
        timeLimitPassedMoreTimeSeekBarThumbBackwards.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
                isTextVisible = false;
                Log.i("CATs", "AnimationBackwards:FALSE");
            }
            @Override
            public void onAnimationEnd (Drawable drawable) {
                super.onAnimationEnd(drawable);
                thisSeekbar.setThumb(timeLimitPassedMoreTimeSeekBarThumbNotAnimated);
                isTextVisible = false;
                Log.i("CATs", "AnimationBackwards:FALSE");
                thisSeekbar.getLayoutParams().height = (int)(20*scale+0.5f);
                thisSeekbar.setPadding(thisSeekbar.getPaddingLeft(), 0, thisSeekbar.getPaddingRight(), 0);
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) thisSeekbar.getLayoutParams();
                params.topMargin = 0;
                thisSeekbar.requestLayout();
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
                thisSeekbar.getLayoutParams().height = (int)(120*scale+0.5f);
                thisSeekbar.setPadding(thisSeekbar.getPaddingLeft(), (int)(100*scale+0.5f), thisSeekbar.getPaddingRight(), 0);
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) thisSeekbar.getLayoutParams();
                params.topMargin = -(int)(100*scale+0.5f);
                thisSeekbar.requestLayout();
                Log.i("CATs", "StartTrackingTouch:");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                thisSeekbar.setThumb(timeLimitPassedMoreTimeSeekBarThumbBackwards);
                timeLimitPassedMoreTimeSeekBarThumbBackwards.start();
                isTextVisible = false;
                Log.i("CATs", "StopTrackingTouch:FALSE");
            }
        });
    }

    public MaterialSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        isTextVisible = false;
        mTextPaint = new TextPaint();
        mTextPaint.setColor(getResources().getColor(R.color.pink_800));
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.seekbar_thumb_text));
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        final SeekBar thisSeekbar = this;
        timeLimitPassedMoreTimeSeekBarThumbNotAnimated = VectorDrawableCompat.create(getResources(), R.drawable.seekbar_thumb_vector, null);
        timeLimitPassedMoreTimeSeekBarThumb = AnimatedVectorDrawableCompat.create(context, R.drawable.seekbar_thumb_animation);
        timeLimitPassedMoreTimeSeekBarThumb.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                if (thisSeekbar.getThumb() == timeLimitPassedMoreTimeSeekBarThumb) {
                    isTextVisible = true;
                    Log.i("CATs", "AnimationForwards:TRUE");
                }
            }
        });
        timeLimitPassedMoreTimeSeekBarThumbBackwards = AnimatedVectorDrawableCompat.create(context, R.drawable.seekbar_thumb_backwards_animation);
        timeLimitPassedMoreTimeSeekBarThumbBackwards.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
                isTextVisible = false;
                Log.i("CATs", "AnimationBackwards:FALSE");
            }
            @Override
            public void onAnimationEnd (Drawable drawable) {
                super.onAnimationEnd(drawable);
                thisSeekbar.setThumb(timeLimitPassedMoreTimeSeekBarThumbNotAnimated);
                isTextVisible = false;
                Log.i("CATs", "AnimationBackwards:FALSE");
                thisSeekbar.getLayoutParams().height = (int)(20*scale+0.5f);
                thisSeekbar.setPadding(thisSeekbar.getPaddingLeft(), 0, thisSeekbar.getPaddingRight(), 0);
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) thisSeekbar.getLayoutParams();
                params.topMargin = 0;
                thisSeekbar.requestLayout();
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
                thisSeekbar.getLayoutParams().height = (int)(120*scale+0.5f);
                thisSeekbar.setPadding(thisSeekbar.getPaddingLeft(), (int)(100*scale+0.5f), thisSeekbar.getPaddingRight(), 0);
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) thisSeekbar.getLayoutParams();
                params.topMargin = -(int)(100*scale+0.5f);
                thisSeekbar.requestLayout();
                Log.i("CATs", "StartTrackingTouch:");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                thisSeekbar.setThumb(timeLimitPassedMoreTimeSeekBarThumbBackwards);
                timeLimitPassedMoreTimeSeekBarThumbBackwards.start();
                isTextVisible = false;
                Log.i("CATs", "StopTrackingTouch:FALSE");
            }
        });
    }

    public MaterialSeekBar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        isTextVisible = false;
        mTextPaint = new TextPaint();
        mTextPaint.setColor(getResources().getColor(R.color.pink_800));
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.seekbar_thumb_text));
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        final SeekBar thisSeekbar = this;
        timeLimitPassedMoreTimeSeekBarThumbNotAnimated = VectorDrawableCompat.create(getResources(), R.drawable.seekbar_thumb_vector, null);
        timeLimitPassedMoreTimeSeekBarThumb = AnimatedVectorDrawableCompat.create(context, R.drawable.seekbar_thumb_animation);
        timeLimitPassedMoreTimeSeekBarThumb.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                if (thisSeekbar.getThumb() == timeLimitPassedMoreTimeSeekBarThumb) {
                    isTextVisible = true;
                    Log.i("CATs", "AnimationForwards:TRUE");
                }
            }
        });
        timeLimitPassedMoreTimeSeekBarThumbBackwards = AnimatedVectorDrawableCompat.create(context, R.drawable.seekbar_thumb_backwards_animation);
        timeLimitPassedMoreTimeSeekBarThumbBackwards.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
                isTextVisible = false;
                Log.i("CATs", "AnimationBackwards:FALSE");
            }
            @Override
            public void onAnimationEnd (Drawable drawable) {
                super.onAnimationEnd(drawable);
                thisSeekbar.setThumb(timeLimitPassedMoreTimeSeekBarThumbNotAnimated);
                isTextVisible = false;
                Log.i("CATs", "AnimationBackwards:FALSE");
                thisSeekbar.getLayoutParams().height = (int)(20*scale+0.5f);
                thisSeekbar.setPadding(thisSeekbar.getPaddingLeft(), 0, thisSeekbar.getPaddingRight(), 0);
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) thisSeekbar.getLayoutParams();
                params.topMargin = 0;
                thisSeekbar.requestLayout();
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
                thisSeekbar.getLayoutParams().height = (int)(120*scale+0.5f);
                thisSeekbar.setPadding(thisSeekbar.getPaddingLeft(), (int)(100*scale+0.5f), thisSeekbar.getPaddingRight(), 0);
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) thisSeekbar.getLayoutParams();
                params.topMargin = -(int)(100*scale+0.5f);
                thisSeekbar.requestLayout();
                Log.i("CATs", "StartTrackingTouch:");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                thisSeekbar.setThumb(timeLimitPassedMoreTimeSeekBarThumbBackwards);
                timeLimitPassedMoreTimeSeekBarThumbBackwards.start();
                isTextVisible = false;
                Log.i("CATs", "StopTrackingTouch:FALSE");
            }
        });
    }

    public MaterialSeekBar setMultiplier(int m) {
        multiplier = m;
        return this;
    }
    public int getMultiplier() {
        return multiplier;
    }
    public MaterialSeekBar setFormatter(String f) {
        formatter = f;
        return this;
    }
    public String getFormatter() {
        return formatter;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (formatter == "1") {
            progressText = Integer.toString(getProgress()*multiplier);
        } else if (formatter == "TimeValueFormatter") {
            progressText = new TimeValueFormatter().getHHMMSS(getProgress()*multiplier);
        }
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(progressText, 0, progressText.length(), bounds);
        int width = getWidth()-getPaddingLeft()-getPaddingRight();
        float normalizedProgress = (float) getProgress()/getMax();
        float thumbX = getPaddingLeft()+width*normalizedProgress;
        float thumbY = this.getHeight()-(int)(75*scale+0.5f);
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.seekbar_thumb_text)-getResources().getDimensionPixelSize(R.dimen.dp1)*(progressText.length()));
        if (isTextVisible) {
            canvas.drawText(progressText, thumbX, thumbY, mTextPaint);
        }
    }
}
