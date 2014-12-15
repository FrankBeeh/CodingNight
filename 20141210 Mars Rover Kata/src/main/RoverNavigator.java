import org.apache.commons.lang3.StringUtils;

public class RoverNavigator {

    private Direction direction;
    private int x;
    private int y;
    private final Rover roverMock;

    public RoverNavigator(Rover roverMock) {
        this.roverMock = roverMock;
    }

    public void init(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void moveTo(int targetX, int targetY) {
        final int deltaY = targetY - this.y;
        final int deltaX = targetX - this.x;

        final String commandString = computeCommand(deltaY, deltaX);
        final String executedCommand = roverMock.move(commandString);
        if (executedCommand != null) {
            computeNewPosition(executedCommand);
            evadeObstacleByTurningRight();
            moveTo(targetX, targetY);
        }
    }

    public void computeNewPosition(String executedCommand) {
        for (final char c : executedCommand.toCharArray()) {
            switch (c) {
                case 'f':
                    x += direction.getDeltaX();
                    y += direction.getDeltaY();
                    break;
                case 'b':
                    x -= direction.getDeltaX();
                    y -= direction.getDeltaY();
                    break;
                case 'r':
                    direction = direction.turnToRight();
                    break;
                case 'l':
                    direction = direction.turnToLeft();
                    break;
            }
        }
    }

    private void evadeObstacleByTurningRight() {
        final String evasionCommand = new String("rflffrb");
        roverMock.move(evasionCommand);
        computeNewPosition(evasionCommand);
    }

    private String computeCommand(int deltaY, final int deltaX) {
        final StringBuilder command = new StringBuilder();

        command.append(direction.getTurnToNorthCommand());

        computeMovesInDirection(deltaY, command);

        if (deltaX != 0) {
            command.append("r");
        }

        computeMovesInDirection(deltaX, command);

        return command.toString();
    }

    private void computeMovesInDirection(int delta, final StringBuilder command) {
        if (delta < 0) {
            command.append(StringUtils.repeat("b", -delta));
        } else {
            command.append(StringUtils.repeat("f", delta));
        }
    }
}
