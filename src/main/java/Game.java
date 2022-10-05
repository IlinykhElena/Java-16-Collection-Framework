import java.util.ArrayList;

public class Game {

    protected ArrayList<Player> playersList = new ArrayList<>();

    public Player findPlayer(String name) {
        for (Player player : playersList) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public ArrayList<Player> register(Player player) {

        //boolean has = playersList.contains(player.getName());
        if (findPlayer(player.getName()) != null) {
            throw new AlreadyExistsException("Игрок с именем " + player.getName() + " уже существует.");
        } else {
            playersList.add(player);
        }
        return playersList;
    }

    public int round(String playerName1, String playerName2) {
        int roundResult = 0;
        Player player1 = findPlayer(playerName1);
        Player player2 = findPlayer(playerName2);
        if (player1 == null) {
            throw new NotRegisteredException("Игрок с именем " + playerName1 + " не зарегистрирован.");
        }
        if (player2 == null) {
            throw new NotRegisteredException("Игрок с именем " + playerName2 + " не зарегистрирован.");
        }

        if (player1.getStrength() > player2.getStrength()) {
            roundResult = 1;
        }
        if (player1.getStrength() < player2.getStrength()) {
            roundResult = 2;
        }
        return roundResult;
    }
}
