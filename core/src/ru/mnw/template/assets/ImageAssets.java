package ru.mnw.template.assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Consumer;

public class ImageAssets extends Asset{

    public TextureRegion empty;
    public TextureRegion cell;
    public Texture wang;

    @Override
    protected void loadImpl() {
        empty = new TextureRegion(new Texture("default.png"));
        cell = new TextureRegion(new Texture("cell.png"));
        wang = new Texture("wang.png");
    }

    @Override
    protected void disposeImpl() {
        empty.getTexture().dispose();
    }

    private static TextureRegion[] split(String path, int horizontal, int vertical) {
        return split(path, horizontal, vertical, horizontal * vertical);
    }

    private static TextureRegion[] split(String path, int horizontal, int vertical, int total){
        Texture texture = new Texture(path);
        TextureRegion[] regions = new TextureRegion[total];
        int width = texture.getWidth() / vertical;
        int height = texture.getHeight() / horizontal;

        int id = 0;
        for (int i = 0; i < horizontal; i++) {
            for (int j = 0; j < vertical; j++) {
                int x = width * j;
                int y = height * i;
                regions[id++] = new TextureRegion(texture, x, y, width, height);
                if (id >= total) return regions;
            }
        }
        return regions;
    }


    public static TextureRegion createCircleImage(int radius, Color color){
        int size = radius * 2 + 2;
        Pixmap pixmap = new Pixmap(size, size, Pixmap.Format.RGBA8888);
        pixmap.fill();
        pixmap.setColor(color);
        pixmap.fillCircle(radius + 1, radius + 1, radius);
        return new TextureRegion(new Texture(pixmap));
    }

    public static TextureRegion createCircleImageNoFill(int radius, Color color){
        int size = radius * 2 + 2;
        Pixmap pixmap = new Pixmap(size, size, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.drawCircle(radius + 1, radius + 1, radius);
        return new TextureRegion(new Texture(pixmap));
    }

    public static TextureRegion createImage(int width, int height, Color color, Consumer<Pixmap> pixmapConsumer){
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmapConsumer.accept(pixmap);
        return new TextureRegion(new Texture(pixmap));
    }

    public static TextureRegion createRectangleImage(int width, int height, Color color){
        Pixmap pixmap = new Pixmap(width + 2, height + 2, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(1, 1, width, height);
        return new TextureRegion(new Texture(pixmap));
    }


    public static TextureRegion createRectangleImage(int width, int height, Color color, Color borderColor){
        Pixmap pixmap = new Pixmap(width + 2, height + 2, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(1, 1, width, height);
        pixmap.setColor(borderColor);
        pixmap.drawRectangle(1, 1, width,  height);
        return new TextureRegion(new Texture(pixmap));
    }
}
