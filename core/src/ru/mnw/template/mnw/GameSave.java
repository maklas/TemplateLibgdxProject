package ru.mnw.template.mnw;

import ru.mnw.template.utils.Config;
import ru.mnw.template.utils.persistance.PersistenceManager;

public class GameSave extends PersistenceManager {

    private static final String version = "1.0.0";
    public String name = "PlayerName";
    public boolean firstLanguageSet = false;
    public Language language = Language.ENGLISH;

    public GameSave() {
        super(Config.gameSaveFile);
    }

    ///////////////////////////////////////////////////////////////////////////
    // ACTIONS
    ///////////////////////////////////////////////////////////////////////////

    public void onExit(){
        persist();
    }

    public GameSave toDefaults() {
        name = "PlayerName";
        return this;
    }
}
