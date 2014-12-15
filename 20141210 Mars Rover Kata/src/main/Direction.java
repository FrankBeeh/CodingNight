public enum Direction {
    NORTH("", 0, 1), EAST("l", 1, 0), SOUTH("rr", 0, -1), WEST("r", -1, 0);
    private final String turnToNorthCommand;
    private final int deltaY;
    private final int deltaX;

    private Direction(String turnToNorthCommand, int deltaX, int deltaY) {
        this.turnToNorthCommand = turnToNorthCommand;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public String getTurnToNorthCommand() {
        return turnToNorthCommand;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public Direction turnToRight() {
        return Direction.values()[(ordinal() + 1) % 4];
    }

    public Direction turnToLeft() {
        return Direction.values()[(ordinal() + 3) % 4];
    }
}
