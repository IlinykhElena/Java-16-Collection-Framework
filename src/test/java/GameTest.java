import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    ArrayList<Player> expected = new ArrayList<>();
    ArrayList<Player> actual = new ArrayList<>();
    Game game = new Game();

    Player player1 = new Player(1, "Игрок1", 11);
    Player player2 = new Player(2, "Игрок2", 22);
    Player player3 = new Player(3, "Игрок3", 11);
    Player player4 = new Player(4, "Игрок1", 44);
    Player player11 = new Player(11, "Игрок11", 111);


    @Test
    public void shouldHaveNoPlayer() {

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldRegisterOnlyOnePlayer() {
        actual = game.register(player1);
        expected.add(player1);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldRegisterTwoPlayers() {
        actual = game.register(player1);
        actual = game.register(player2);
        expected.add(player1);
        expected.add(player2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldThrewAlreadyExistsException() {
        game.register(player1);

        Assertions.assertThrows(AlreadyExistsException.class, () -> game.register(player4));
    }

    @Test
    public void shouldWinPlayer2() {
        game.register(player1);
        game.register(player2);
        int expected = 2;
        int actual = game.round("Игрок1", "Игрок2");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldWinPlayer11() {
        game.register(player11);
        game.register(player2);
        int expected = 1;
        int actual = game.round("Игрок11", "Игрок2");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldWinNobody() {
        game.register(player1);
        game.register(player3);
        int expected = 0;
        int actual = game.round("Игрок1", "Игрок3");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldThrewExceptionWhenPlayer1NotRegistered() {
        game.register(player2);

        Assertions.assertThrows(NotRegisteredException.class, () -> game.round("Игрок1", "Игрок2"));
    }

    @Test
    public void shouldThrewExceptionWhenPlayer2NotRegistered() {
        game.register(player1);

        Assertions.assertThrows(NotRegisteredException.class, () -> game.round("Игрок1", "Игрок2"));
    }

    @Test
    public void shouldThrewExceptionWhenBothPlayersNotRegistered() {
        game.register(player3);
        game.register(player11);

        Assertions.assertThrows(NotRegisteredException.class, () -> game.round("Игрок1", "Игрок2"));
    }

}
