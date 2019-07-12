package ru.mnw.template.states;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mnw.template.assets.A;
import ru.mnw.template.utils.gsm_lib.State;

public class MainMenuState extends State {

    @Override
    protected void onCreate() {
        A.skins.load();
    }

    @Override
    protected void update(float dt) {

    }

    @Override
    protected void render(Batch batch) {

    }

    @Override
    protected void dispose() {

    }
}
