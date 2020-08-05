package com.ablanco.zoomy;

import android.view.View;

import java.util.ArrayList;

public class ZoomyTransition {

    Zoomy.Builder zoomyBuilder;
    Float treshold;

    public ZoomyTransition(final Zoomy.Builder builder, final Float treshold) {
        this.treshold = treshold;
        zoomyBuilder = builder;
        zoomyBuilder.zoomListener(new com.ablanco.zoomy.ZoomListener() {
            float scaleFactor = 1f;

            @Override
            public void onViewStartedZooming(View view) {
            }

            @Override
            public void onViewEndedZooming(View view) {
                if (scaleFactor > treshold) {
                    builder.setTargetView(targetView);
                }
                for (ZoomListener listener : listeners) {
                    listener.zoomEnded(scaleFactor);
                }
            }

            @Override
            public void onViewScaled(View view, float scaleFactor) {
                this.scaleFactor = scaleFactor;
                for (ZoomListener listener : listeners) {
                    listener.onZoomChanged(scaleFactor);
                }
            }
        });
    }

    ArrayList<ZoomListener> listeners = new ArrayList<>();

    View startingView;
    View targetView;

    public void setStartingView(View view) {
        targetView.setVisibility(View.INVISIBLE);
        startingView = view;
    }

    public void setTargetView(View view) {
        targetView.setVisibility(View.INVISIBLE);
        targetView = view;
    }

    public void addZoomListener(ZoomListener listener) {
        listeners.add(listener);
    }

    public interface ZoomListener {
        void onZoomChanged(float scale);

        void zoomEnded(float scale);
    }
}
