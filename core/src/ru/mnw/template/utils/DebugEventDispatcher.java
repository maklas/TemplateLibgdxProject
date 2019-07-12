package ru.mnw.template.utils;

import ru.maklas.mengine.utils.EventDispatcher;
import ru.mnw.template.engine.other.Dispatchable;

public class DebugEventDispatcher extends EventDispatcher {

    @Override
    public void dispatch(Object event) {
        if (!(event instanceof Dispatchable)) {
            Log.error(event.getClass().getSimpleName() + ".class does not implement Dispatchable interface");
        }
        super.dispatch(event);
    }
}
