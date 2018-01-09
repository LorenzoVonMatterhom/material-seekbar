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
    // This means the progress is the same
    private int multiplier = 1;
    private String progressText;
    // This means there is no formatting
    private String formatter = "1";
    AnimatedVectorDrawableCompat seekBarThumb;
    AnimatedVectorDrawableCompat seekBarThumbBackwards;
    VectorDrawableCompat seekBarThumbNotAnimated;
    final float scale = getContext().getResources().getDisplayMetrics().density;

    //IS THIS METHOD EVER USED?
    public MaterialSeekBar(Context context) {
        super(context);
        isTextVisible = false;

        mTextPaint = new TextPaint();
        mTextPaint.setColor(getResources().getColor(R.color.pink_800));
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.seekbar_thumb_text));
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        final SeekBar thisSeekbar = this;

        seekBarThumbNotAnimated = VectorDrawableCompat.create(getResources(), R.drawable.seekbar_thumb_vector, null);

        seekBarThumb = AnimatedVectorDrawableCompat.create(context, R.drawable.seekbar_thumb_animation);
        seekBarThumb.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                if (thisSeekbar.getThumb() == seekBarThumb) {
                    isTextVisible = true;
                    Log.i("CATs", "AnimationForwards:TRUE");
                }
            }
        });
        seekBarThumbBackwards = AnimatedVectorDrawableCompat.create(context, R.drawable.seekbar_thumb_animation_backward);
        seekBarThumbBackwards.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
                isTextVisible = false;
                Log.i("CATs", "AnimationBackwards:FALSE");
            }
            @Override
            public void onAnimationEnd (Drawable drawable) {
                super.onAnimationEnd(drawable);
                thisSeekbar.setThumb(seekBarThumbNotAnimated);
                isTextVisible = false;
                Log.i("CATs", "AnimationBackwards:FALSE");
                //set height to 20dp
                thisSeekbar.getLayoutParams().height = (int)(20*scale+0.5f);
                //set padding to n 0 n 0
                thisSeekbar.setPadding(thisSeekbar.getPaddingLeft(), 0, thisSeekbar.getPaddingRight(), 0);
                //set margin top to 0
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) thisSeekbar.getLayoutParams();
                params.topMargin = 0;
                thisSeekbar.requestLayout();
            }
        });

        this.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                //here you can change progress if you want (at your own risk :) )
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                thisSeekbar.setThumb(seekBarThumb);
                seekBarThumb.start();
                //set height to 120dp
                thisSeekbar.getLayoutParams().height = (int)(120*scale+0.5f);
                //set padding to n 100dp n 0
                thisSeekbar.setPadding(thisSeekbar.getPaddingLeft(), (int)(100*scale+0.5f), thisSeekbar.getPaddingRight(), 0);
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) thisSeekbar.getLayoutParams();
                params.topMargin = -(int)(100*scale+0.5f);
                thisSeekbar.requestLayout();
                //set margin top -100dp: do not bother me, i know it's a bad practice, if you have a
                //suggetion just open an issue, but i do not promise i will reply :)git
                Log.i("CATs", "StartTrackingTouch:");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                thisSeekbar.setThumb(seekBarThumbBackwards);
                seekBarThumbBackwards.start();
                isTextVisible = false;
                Log.i("CATs", "StopTrackingTouch:FALSE");
            }
        });
    }
    //IS THIS METHOD EVER USED?
    public MaterialSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        isTextVisible = false;

        mTextPaint = new TextPaint();
        mTextPaint.setColor(getResources().getColor(R.color.pink_800));
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.seekbar_thumb_text));
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        final SeekBar thisSeekbar = this;

        seekBarThumbNotAnimated = VectorDrawableCompat.create(getResources(), R.drawable.seekbar_thumb_vector, null);

        seekBarThumb = AnimatedVectorDrawableCompat.create(context, R.drawable.seekbar_thumb_animation);
        seekBarThumb.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                if (thisSeekbar.getThumb() == seekBarThumb) {
                    isTextVisible = true;
                    Log.i("CATs", "AnimationForwards:TRUE");
                }
            }
        });
        seekBarThumbBackwards = AnimatedVectorDrawableCompat.create(context, R.drawable.seekbar_thumb_animation_backward);
        seekBarThumbBackwards.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
                isTextVisible = false;
                Log.i("CATs", "AnimationBackwards:FALSE");
            }
            @Override
            public void onAnimationEnd (Drawable drawable) {
                super.onAnimationEnd(drawable);
                thisSeekbar.setThumb(seekBarThumbNotAnimated);
                isTextVisible = false;
                Log.i("CATs", "AnimationBackwards:FALSE");
                //set height to 20dp
                thisSeekbar.getLayoutParams().height = (int)(20*scale+0.5f);
                //set padding to n 0 n 0
                thisSeekbar.setPadding(thisSeekbar.getPaddingLeft(), 0, thisSeekbar.getPaddingRight(), 0);
                //set margin top to 0
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) thisSeekbar.getLayoutParams();
                params.topMargin = 0;
                thisSeekbar.requestLayout();
            }
        });

        this.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                //here you can change progress if you want (at your own risk :) )
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                thisSeekbar.setThumb(seekBarThumb);
                seekBarThumb.start();
                //set height to 120dp
                thisSeekbar.getLayoutParams().height = (int)(120*scale+0.5f);
                //set padding to n 100dp n 0
                thisSeekbar.setPadding(thisSeekbar.getPaddingLeft(), (int)(100*scale+0.5f), thisSeekbar.getPaddingRight(), 0);
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) thisSeekbar.getLayoutParams();
                params.topMargin = -(int)(100*scale+0.5f);
                thisSeekbar.requestLayout();
                //set margin top -100dp: do not bother me, i know it's a bad practice, if you have a
                //suggetion just open an issue, but i do not promise i will reply :)git
                Log.i("CATs", "StartTrackingTouch:");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                thisSeekbar.setThumb(seekBarThumbBackwards);
                seekBarThumbBackwards.start();
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

        seekBarThumbNotAnimated = VectorDrawableCompat.create(getResources(), R.drawable.seekbar_thumb_vector, null);

        seekBarThumb = AnimatedVectorDrawableCompat.create(context, R.drawable.seekbar_thumb_animation);
        seekBarThumb.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                if (thisSeekbar.getThumb() == seekBarThumb) {
                    isTextVisible = true;
                    Log.i("CATs", "AnimationForwards:TRUE");
                }
            }
        });
        seekBarThumbBackwards = AnimatedVectorDrawableCompat.create(context, R.drawable.seekbar_thumb_animation_backward);
        seekBarThumbBackwards.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
            @Override
            public void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
                isTextVisible = false;
                Log.i("CATs", "AnimationBackwards:FALSE");
            }
            @Override
            public void onAnimationEnd (Drawable drawable) {
                super.onAnimationEnd(drawable);
                thisSeekbar.setThumb(seekBarThumbNotAnimated);
                isTextVisible = false;
                Log.i("CATs", "AnimationBackwards:FALSE");
                //set height to 20dp
                thisSeekbar.getLayoutParams().height = (int)(20*scale+0.5f);
                //set padding to n 0 n 0
                thisSeekbar.setPadding(thisSeekbar.getPaddingLeft(), 0, thisSeekbar.getPaddingRight(), 0);
                //set margin top to 0
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) thisSeekbar.getLayoutParams();
                params.topMargin = 0;
                thisSeekbar.requestLayout();
            }
        });

        this.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                //here you can change progress if you want (at your own risk :) )
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                thisSeekbar.setThumb(seekBarThumb);
                seekBarThumb.start();
                //set height to 120dp
                thisSeekbar.getLayoutParams().height = (int)(120*scale+0.5f);
                //set padding to n 100dp n 0
                thisSeekbar.setPadding(thisSeekbar.getPaddingLeft(), (int)(100*scale+0.5f), thisSeekbar.getPaddingRight(), 0);
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) thisSeekbar.getLayoutParams();
                params.topMargin = -(int)(100*scale+0.5f);
                thisSeekbar.requestLayout();
                //set margin top -100dp: do not bother me, i know it's a bad practice, if you have a
                //suggetion just open an issue, but i do not promise i will reply :)git
                Log.i("CATs", "StartTrackingTouch:");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                thisSeekbar.setThumb(seekBarThumbBackwards);
                seekBarThumbBackwards.start();
                isTextVisible = false;
                Log.i("CATs", "StopTrackingTouch:FALSE");
            }
        });
    }
    /*
    Sets the number you want to multiply the progress to: this will NOT affect progress itself
    (no use of setProgress() method) but it will just multiply the value getProgress() just before it
    is formatted (see onDraw())
     */
    public MaterialSeekBar setMultiplier(int m) {
        multiplier = m;
        return this;
    }
    /*
    Gets the number you want to multiply your progress to;
     */
    public int getMultiplier() {
        return multiplier;
    }
    /*
    Sets the type of formatter you want, so that you can apply your logic in onDraw() with some
    if-else statements
     */
    public MaterialSeekBar setFormatter(String f) {
        formatter = f;
        return this;
    }
    /*
    Gets the type of formatter you set
     */
    public String getFormatter() {
        return formatter;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
        Write your own logic here, you can change progressText to whatever you want be it $, %,
        h-m-s, etc.

        NOTE: there is no TimeValueFormatter here, these five lines below just show you how I used
        it
        */
        if (formatter == "1") {
            //no formatting needed
            progressText = Integer.toString(getProgress()*multiplier);
        } else if (formatter == "TimeValueFormatter") {
            //timevalueformatting XXh YYm ZZs (not included here)
            progressText = new TimeValueFormatter().getHHMMSS(getProgress()*multiplier);
        }


        Rect bounds = new Rect();
        mTextPaint.getTextBounds(progressText, 0, progressText.length(), bounds);
        int width = getWidth()-getPaddingLeft()-getPaddingRight();
        float normalizedProgress = (float) getProgress()/getMax();

        float thumbX = getPaddingLeft()+width*normalizedProgress;
        //Mmmh...this assumes the drawable is 200x200dp, the seekbar is 20dp and its padding/negative margin is 100dp
        float thumbY = this.getHeight()-(int)(75*scale+0.5f);
        /* Don't really know what size you need: here's what I used, 18dp-(1dp*progressText.lenght())
        so that the text will become smaller if text is longer
        */
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.seekbar_thumb_text)-getResources().getDimensionPixelSize(R.dimen.dp1)*(progressText.length()));
        if (isTextVisible) {
            canvas.drawText(progressText, thumbX, thumbY, mTextPaint);
        }
    }
}
