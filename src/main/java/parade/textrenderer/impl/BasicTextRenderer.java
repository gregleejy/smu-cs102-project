package parade.textrenderer.impl;

import parade.common.Card;
import parade.player.Player;
import parade.textrenderer.TextRenderer;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BasicTextRenderer implements TextRenderer {
    public BasicTextRenderer() {}

    @Override
    public void render(String message) {
        System.out.println(message);
    }

    @Override
    public void renderWelcome() {
        String paradeWelcome = null;
        try {
            // the stream holding the file content
            InputStream inFromFile =
                    getClass().getClassLoader().getResourceAsStream("parade_ascii_art.txt");
            Scanner s = new Scanner(inFromFile).useDelimiter("\\A");
            paradeWelcome = s.hasNext() ? s.next() : "";

        } catch (Exception e) {
        }

        if (paradeWelcome != null) {
            System.out.println(ConsoleColors.PURPLE_BOLD);
            System.out.println(
                    "============================= Welcome to Parade!"
                            + " ==============================");
            System.out.println(ConsoleColors.PURPLE_UNDERLINED + paradeWelcome);
            System.out.println(ConsoleColors.RESET);
            System.out.println(
                    "===================================================================================");
        }
    }

    @Override
    public void renderMenu() {
        System.out.println("1. Start Game");
        System.out.println("2. Exit");
        System.out.print("Please select an option: ");
    }

    @Override
    public void renderPlayersLobby(List<Player> players) {
        System.out.println("Players in lobby: ");
        for (int i = 1; i <= players.size(); i++) {
            System.out.printf("%d. %s\n", i, players.get(i - 1).getName());
        }
        System.out.println();
        System.out.println("1. Add Player" + (players.size() == 6 ? " (Lobby is full)" : ""));
        System.out.println("2. Start Game");
        System.out.print("Please select an option: ");
    }

    @Override
    public void renderPlayerTurn(Player player, Card newlyDrawnCard, List<Card> parade) {
        System.out.println(player.getName() + "'s turn.");
        System.out.println("You drew: " + newlyDrawnCard);
        System.out.println("Parade: " + parade);
        System.out.print("Your hand: " + player.getHand() + "\nSelect a card to play: ");
    }

    @Override
    public void renderEndGame(Map<Player, Integer> playerScores) {
        System.out.println("Game Over!");
        for (Map.Entry<Player, Integer> entry : playerScores.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue());
        }
    }

    @Override
    public void renderSinglePlayerEndGame(Player player, int score) {
        System.out.println("Game Over, " + player.getName() + "!");
        System.out.println("Your score: " + score);
    }

    @Override
    public void renderBye() {
        System.out.println("Bye bye buddy.");
    }
}
