package ru.mnw.template.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.World;
import ru.maklas.libs.Counter;
import ru.maklas.mengine.Bundler;
import ru.maklas.mengine.BundlerKey;
import ru.mnw.template.statics.ID;
import ru.mnw.template.utils.Profiler;
import ru.mnw.template.utils.TimeSlower;
import ru.mnw.template.utils.gsm_lib.State;
import ru.mnw.template.utils.physics.Builders;

/** Для Engine.bundler **/
public class B {

    public static final BundlerKey<Batch> batch = BundlerKey.of("batch");
    public static final BundlerKey<OrthographicCamera> cam = BundlerKey.of("cam");
    public static final BundlerKey<World> world = BundlerKey.of("world");
    public static final BundlerKey<Builders> builders = BundlerKey.of("builders");
    public static final BundlerKey<Float> dt = BundlerKey.of("dt");
    public static final BundlerKey<TimeSlower> timeSlower = BundlerKey.of("timeSlower");
    public static final BundlerKey<Profiler> profiler = BundlerKey.of("profiler");
    public static final BundlerKey<State> gsmState = BundlerKey.of("state");
    public static final BundlerKey<Counter> idPlayers = BundlerKey.of("idPlayers");
    public static final BundlerKey<Counter> idItems = BundlerKey.of("idItems");
    public static final BundlerKey<Counter> idEnemies = BundlerKey.of("idEnemies");
    public static final BundlerKey<Counter> idEnvironment = BundlerKey.of("idEnvironment");
    public static final BundlerKey<Boolean> isClient = BundlerKey.of("isClient"); //Is this client or server engine.


    public static void fillIds(Bundler bundler){
        bundler.set(idPlayers, ID.counterForPlayers());
        bundler.set(idItems, ID.counterForItems());
        bundler.set(idEnemies, ID.counterForEnemies());
        bundler.set(idEnvironment, ID.counterForEnvironment());
    }
}
