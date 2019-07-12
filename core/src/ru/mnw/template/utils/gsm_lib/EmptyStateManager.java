package ru.mnw.template.utils.gsm_lib;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.MapFunction;

public class EmptyStateManager implements GameStateManager {

    @Override
    public void launch(State firstState, Batch batch) {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void toBackground() {

    }

    @Override
    public void toForeground() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public State getCurrentState() {
        return null;
    }

    @Override
    public void printStackTrace() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void setCommand(GSMCommand command) {

    }

    @Override
    public GSMCommand getPendingCommand() {
        return null;
    }

    @Override
    public State getState(int number) {
        return null;
    }

    @Override
    public void print(Object msg) {

    }

    @Override
    public void print(Object msg, float seconds) {

    }

    @Override
    public void print(Object msg, float seconds, Color color) {

    }

    @Override
    public void printAsync(Object msg, float seconds) {

    }

    @Override
    public void clearPrints() {

    }

    @Override
    public void setFallbackStateProvider(MapFunction<Exception, State> fallbackStateProvider) {

    }

    @Override
    public int stackSize() {
        return 0;
    }

    @Override
    public Batch getBatch() {
        return null;
    }
}
