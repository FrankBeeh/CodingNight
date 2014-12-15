import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.Before;
import org.junit.Test;

public class RoverNavigatorTest {

    private static final String EVADE_COMMAND = "rflffrb";
    private Rover roverMock;
    private RoverNavigator roverNavigator;

    @Before
    public void setUp() {
        roverMock = createMock(Rover.class);
        roverNavigator = new RoverNavigator(roverMock);
    }

    @Test
    public void moveForwardWithoutObstacle() throws Exception {
        roverNavigator.init(1, 3, Direction.NORTH);

        expect(roverMock.move("ffff")).andReturn(null);
        replay(roverMock);
        roverNavigator.moveTo(1, 7);
        verify(roverMock);
    }

    @Test
    public void moveXYWithoutObstacle() throws Exception {
        roverNavigator.init(1, 3, Direction.NORTH);

        expect(roverMock.move("ffffrff")).andReturn(null);
        replay(roverMock);
        roverNavigator.moveTo(3, 7);
        verify(roverMock);
    }

    @Test
    public void moveXYBackwards() throws Exception {
        roverNavigator.init(2, 3, Direction.NORTH);
        expect(roverMock.move("bbbrbb")).andReturn(null);
        replay(roverMock);
        roverNavigator.moveTo(0, 0);
        verify(roverMock);
    }

    @Test
    public void lookToEastBeforeMoving() throws Exception {
        roverNavigator.init(1, 3, Direction.EAST);

        expect(roverMock.move("lf")).andReturn(null);
        replay(roverMock);
        roverNavigator.moveTo(1, 4);
        verify(roverMock);
    }

    @Test
    public void lookToWestBeforeMoving() throws Exception {
        roverNavigator.init(1, 3, Direction.WEST);

        expect(roverMock.move("rf")).andReturn(null);
        replay(roverMock);
        roverNavigator.moveTo(1, 4);
        verify(roverMock);
    }

    @Test
    public void lookToSouthBeforeMoving() throws Exception {
        roverNavigator.init(1, 3, Direction.SOUTH);

        expect(roverMock.move("rrf")).andReturn(null);
        replay(roverMock);
        roverNavigator.moveTo(1, 4);
        verify(roverMock);
    }

    @Test
    public void moveForwardHitObstacle() throws Exception {
        roverNavigator.init(1, 3, Direction.NORTH);

        expect(roverMock.move("ffff")).andReturn("f");
        expect(roverMock.move(EVADE_COMMAND)).andReturn(null);
        expect(roverMock.move("lf")).andReturn(null);
        replay(roverMock);
        roverNavigator.moveTo(1, 7);
        verify(roverMock);
    }

    @Test
    public void moveRightHitObstacle() throws Exception {
        roverNavigator.init(1, 3, Direction.NORTH);

        expect(roverMock.move("rfff")).andReturn("r");
        expect(roverMock.move(EVADE_COMMAND)).andReturn(null);
        expect(roverMock.move("rrrf")).andReturn(null);
        replay(roverMock);
        roverNavigator.moveTo(4, 3);
        verify(roverMock);
    }
}
