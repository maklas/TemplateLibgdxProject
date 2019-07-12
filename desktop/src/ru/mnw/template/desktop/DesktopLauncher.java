package ru.mnw.template.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.mnw.template.ProjectTemplate;
import ru.mnw.template.mnw.MNW;
import ru.mnw.template.states.MainMenuState;
import ru.mnw.template.utils.Log;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = 360;
        config.width = 640;
        config.resizable = true;
        config.title = MNW.GAME_NAME;
        Log.logger = new FileLogger();
        new LwjglApplication(new ProjectTemplate(new MainMenuState()), config);
    }
}
