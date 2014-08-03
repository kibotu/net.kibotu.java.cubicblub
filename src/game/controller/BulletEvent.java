package game.controller;

import java.util.EventObject;

public class BulletEvent extends EventObject {

    private static final long serialVersionUID = -5559370074331330011L;
    private BulletState _state;

    public BulletEvent(Object source, BulletState state) {
	super(source);
	_state = state;
    }

    public BulletState getEventName() {
	return _state;
    }
}