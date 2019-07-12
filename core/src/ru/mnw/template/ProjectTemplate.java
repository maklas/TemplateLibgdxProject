package ru.mnw.template;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.maklas.libs.SimpleProfiler;
import ru.mnw.template.assets.A;
import ru.mnw.template.assets.Asset;
import ru.mnw.template.engine.M;
import ru.mnw.template.mnw.*;
import ru.mnw.template.statics.Game;
import ru.mnw.template.utils.Log;
import ru.mnw.template.utils.Utils;
import ru.mnw.template.utils.gsm_lib.EmptyStateManager;
import ru.mnw.template.utils.gsm_lib.MultilayerStateManager;
import ru.mnw.template.utils.gsm_lib.State;

public class ProjectTemplate extends ApplicationAdapter {

    private State launchState;
    private Batch batch;

    public ProjectTemplate(State state) {
        this.launchState = state;
    }

    @Override
    public void create () {
        float scale = (float) Gdx.graphics.getWidth() / Game.width;
        Game.height = Math.round((float) Gdx.graphics.getHeight() / scale);
        Game.hHeight = Game.height / 2;

        State launchState = this.launchState;
        this.launchState = null;
        try {
            initialize();
            MNW.gsm.launch(launchState, batch);
        } catch (Exception e) {
            if (MNW.crash != null) MNW.crash.report(e);
            e.printStackTrace();
            Gdx.app.exit();
            MNW.gsm = new EmptyStateManager();
        }
    }

    private void initialize(){
        SimpleProfiler.start();
        batch = new SpriteBatch();
        MNW.gsm = new MultilayerStateManager();
        MNW.save = new GameSave();
        MNW.save.loadFromFile();
        if (MNW.device == null) MNW.device = new PCDevice();
        if (MNW.analytics == null) MNW.analytics = new NoAnalytics();
        if (MNW.crash == null) MNW.crash = new NoCrashReport();
        if (MNW.statistics == null) MNW.statistics = new NoStatistics();
        if (MNW.ads == null) MNW.ads = new NoAds();

        if (!MNW.save.firstLanguageSet){
            MNW.save.language = Strings.identifyDeviceLocale(Language.ENGLISH);
            MNW.save.firstLanguageSet = true;
        }
        MNW.strings = new Strings(MNW.save.language);
        M.initialize();

        A.images.load();
        A.skins.load();

        Log.trace("Initialized in " + SimpleProfiler.getTimeAsString());
    }

    @Override
    public void resize(int width, int height) {
        MNW.gsm.resize(width, height);
    }

    @Override
    public void render () {
        Color bg = MNW.backgroundColor;
        Gdx.gl.glClearColor(bg.r, bg.g, bg.b, bg.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        MNW.gsm.update(Utils.getDeltaTime());
    }

    @Override
    public void pause() {
        MNW.gsm.toBackground();
    }

    @Override
    public void resume() {
        MNW.gsm.toForeground();
    }

    @Override
    public void dispose () {
        try {
            MNW.save.onExit();

            MNW.gsm.dispose();
            batch.dispose();
            A.all().foreach(Asset::dispose);
            Log.logger.dispose();
            Utils.executor.shutdown();
        } catch (Exception e) {
            if (MNW.crash != null) MNW.crash.report(e);
        }
    }


}
