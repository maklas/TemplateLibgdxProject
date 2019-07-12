package ru.mnw.template.assets;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.mnw.template.mnw.MNW;
import ru.mnw.template.utils.Config;
import ru.mnw.template.utils.Log;
import ru.mnw.template.utils.StringUtils;

/**
 * Автоматически Загружаемый/Выгружаемый ассет.
 */
public abstract class Asset {

    private boolean loaded = false;

    public final void load(){
        if (loaded) return;
        try {
            startLoading();
            loaded = true;
        } catch (Exception e) {
            MNW.crash.report(e);
        }
    }

    public final void reload(){
        if (loaded){
            dispose();
        }
        try {
            startLoading();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        loaded = true;
    }

    public final boolean isLoaded(){
        return loaded;
    }

    public final void dispose(){
        if (!loaded) return;

        try {
            long start = System.nanoTime();
            disposeImpl();
            float micro = ((float)(System.nanoTime() - start)) / 1000;
            if (Config.LOG_ASSETS) Log.trace("Asset","\"" + getClass().getSimpleName() + "\" disposed in " + StringUtils.ff(micro) + " us");
        } catch (Exception e) {
            loaded = false;
            throw new RuntimeException(e);
        }
        loaded = false;
    }

    private void startLoading() throws Exception {
        long start = System.nanoTime();
        loadImpl();
        float micro = ((float)(System.nanoTime() - start)) / 1000;
        if (Config.LOG_ASSETS) Log.trace("Asset","\"" + getClass().getSimpleName() + "\" loaded in " + StringUtils.ff(micro) + " us");
    }


    protected abstract void loadImpl() throws Exception;
    protected abstract void disposeImpl() throws Exception;


    protected final void disposeTexturesOfRegions(TextureRegion... regions){
        for (TextureRegion region : regions) {
            region.getTexture().dispose();
        }

    }
}
