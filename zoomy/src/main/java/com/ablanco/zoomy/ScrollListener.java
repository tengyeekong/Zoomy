package com.ablanco.zoomy;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Álvaro Blanco Cabrero on 13/02/2017.
 * Zoomy.
 */

public interface ScrollListener {
    void onScroll(View v, MotionEvent e1, MotionEvent e2, float distanceX, float distanceY);
}
