package com.example.ads_demo_project;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;

import java.util.Map;

import io.flutter.plugins.googlemobileads.GoogleMobileAdsPlugin;

public class NativeAdFactoryMedium implements GoogleMobileAdsPlugin.NativeAdFactory {
    private final Context context;

    public NativeAdFactoryMedium(Context context) {
        this.context = context;
    }

    @Override
    public NativeAdView createNativeAd(NativeAd nativeAd, Map customOptions) {
        NativeAdView adView = (NativeAdView) LayoutInflater.from(context)
                .inflate(R.layout.native_ads_medium, null);

        adView.setMediaView((MediaView) adView.findViewById(R.id.native_ad_media));
        adView.setHeadlineView(adView.findViewById(R.id.native_ad_headline));
        adView.setBodyView(adView.findViewById(R.id.native_ad_body));
        adView.setIconView(adView.findViewById(R.id.native_ad_icon));
        adView.setCallToActionView(adView.findViewById(R.id.native_ad_button));

        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        ((TextView) adView.getBodyView()).setText(nativeAd.getBody());

        if (nativeAd.getIcon() != null) {
            ((ImageView) adView.getIconView()).setImageDrawable(nativeAd.getIcon().getDrawable());
        } else {
            adView.getIconView().setVisibility(View.GONE);
        }

        ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        adView.setNativeAd(nativeAd);

        return adView;
    }
}
