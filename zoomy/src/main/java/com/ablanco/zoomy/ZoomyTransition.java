package com.ablanco.zoomy;

import android.view.View;

import java.util.ArrayList;

public class ZoomyTransition {

    Zoomy.Builder zoomyBuilder;
    public Float treshold;
    public boolean isZooming;
    public float scaleFactor;

    public ZoomyTransition(final Zoomy.Builder builder, final Float treshold) {
        this.treshold = treshold;
        zoomyBuilder = builder;
        zoomyBuilder.zoomListener(new com.ablanco.zoomy.ZoomListener() {

            @Override
            public void onViewStartedZooming(View view) {
                isZooming = true;
                for (ZoomListener listener : listeners) {
                    if (listener != null)
                        listener.onZoomStarted();
                }
            }

            @Override
            public void onViewEndedZooming(View view) {
                isZooming = false;
                for (ZoomListener listener : listeners) {
                    if (listener != null)
                        listener.zoomEnded(scaleFactor);
                }
            }

            @Override
            public void onViewScaled(View view, float scale) {
                scaleFactor = scale;
                if (scaleFactor > treshold) {
                    if (targetView != null)
                        builder.setTargetView(targetView);
                } else {
                    if (startingView != null)
                        builder.setTargetView(startingView);
                }
                for (ZoomListener listener : listeners) {
                    if (listener != null)
                        listener.onZoomChanged(scaleFactor);
                }
            }
        });
    }

    ArrayList<ZoomListener> listeners = new ArrayList<>();

    public View startingView;
    public View targetView;

    public void setStartingView(View view) {
        //startingView.setVisibility(View.INVISIBLE);
        startingView = view;
    }

    public void setTargetView(View view) {
        targetView = view;
        targetView.setVisibility(View.INVISIBLE);
    }

    public void addZoomListener(ZoomListener listener) {
        listeners.add(listener);
    }

    public void removeZoomListener(ZoomListener listener) {
        listeners.remove(listener);
    }

    public interface ZoomListener {
        void onZoomStarted();

        void onZoomChanged(float scale);

        void zoomEnded(float scale);
    }
}
