
import 'package:flutter/material.dart';
import 'package:google_mobile_ads/google_mobile_ads.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State createState() => _HomeScreenState();
}

class _HomeScreenState extends State {
  NativeAd? nativeAd;
  bool isNativeAdLoaded = false;

  @override
  void initState() {
    super.initState();
    loadNativeAd();
  }

  void loadNativeAd() {
    nativeAd = NativeAd(
      adUnitId: 'ca-app-pub-3940256099942544/2247696110', // Test Native Ad Unit
      factoryId: 'listTileMedium',
      request: const AdRequest(),
      listener: NativeAdListener(
        onAdLoaded: (ad) {
          setState(() => isNativeAdLoaded = true);
        },
        onAdFailedToLoad: (ad, error) {
          ad.dispose();
          debugPrint('Native Ad failed: $error');
        },
      ),
    )..load();
  }

  @override
  void dispose() {
    nativeAd?.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Native Ad Example')),
      body: ListView(
        children: [
          const SizedBox(height: 20),
          Center(child: Text('Google AdMob Native Ad')),
          const SizedBox(height: 20),
          if (isNativeAdLoaded)
            Container(
              height: 300,
              margin: const EdgeInsets.all(15),
              padding: const EdgeInsets.all(5),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(15),
                color: Colors.white,
              ),
              child: AdWidget(ad: nativeAd!),
            )
        ],
      ),
    );
  }
}
