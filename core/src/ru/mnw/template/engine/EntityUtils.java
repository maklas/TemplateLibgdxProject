package ru.mnw.template.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import ru.maklas.mengine.Entity;
import ru.mnw.template.engine.rendering.CameraComponent;
import ru.mnw.template.statics.EntityType;
import ru.mnw.template.statics.ID;
import ru.mnw.template.statics.Layers;

public class EntityUtils {

    public static Entity camera(OrthographicCamera cam) {
        return new Entity(ID.camera, EntityType.BACKGROUND, 0, 0, Layers.camera).add(new CameraComponent(cam));
    }
    public static Entity camera(OrthographicCamera cam, int followId) {
        return new Entity(ID.camera, EntityType.BACKGROUND, 0, 0, Layers.camera).add(new CameraComponent(cam).setFollowEntity(followId, true, true));
    }
}
