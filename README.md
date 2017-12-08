# material-seekbar
No libraries, no dependencies. Material seekbar for Android. Just like you want it.
![Google material seekbar (from guidelines)](https://storage.googleapis.com/material-design/publish/material_v_12/assets/0Bx4BSt6jniD7dEtQQ2s4VUZxZG8/components-sliders-discrete1.png)

This is just a simple and lightweight extension of Seekbar class (well, AppCompatSeekBar actually) you can simply implement in your project by copying the code. No dependencies, no default values, fully customizable.

It uses AnimatedVectorDrawable (seekbar_thumb_final.xml and seekbar_thumb_backwards_final.xml) to display the thumb over the seekbar and Canvas.drawText to display its value: OnStartTrackingTouch starts the forward drawable and OnStopTrackingTouch calls the backwards animation.

It uses negative layout_marginTop to overlap the layout elements above: I know, I'm sorry.
