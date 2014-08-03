package game.controller;

public class BulletState {
    public static final BulletState REACHED_LIMIT = new BulletState(
	    "REACHED_LIMIT");
    public static final BulletState RUNNING = new BulletState("RUNNING");

    private String _state;

    public String toString() {
	return _state;
    }

    private BulletState(String state) {
	_state = state;
    }
}