package ru.mnw.template.mnw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Consumer;
import ru.mnw.template.utils.Log;

public class NoAds implements Ads {

    @Override
    public void loadNextInterstitial() {
        Log.trace("No Ads", "Loading next interstitial");
    }

    @Override
    public boolean canShowInterstitial() {
        return true;
    }

    @Override
    public void canShowInterstitial(Consumer<Boolean> responseConsumer) {
        Gdx.app.postRunnable(() -> responseConsumer.accept(canShowInterstitial()));
    }

    @Override
    public boolean interstitialIsLoading() {
        return false;
    }

    @Override
    public boolean showInterstitial() {
        Log.trace("No Ads", "Can't show interstitial");
        return false;
    }

    @Override
    public void startLoadingIfNotLoaded() {
        Log.trace("No Ads", "startLoadingIfNotLoaded()");
    }
}
