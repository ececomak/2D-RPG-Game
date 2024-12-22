package ui;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverOverlay {

    private Playing playing;

    public GameOverOverlay(Playing playing) {
        this.playing = playing;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        Font font = new Font("Arial", Font.BOLD, 50);
        g.setFont(font);

        g.setColor(Color.white);
        g.drawString("GAME OVER", Game.GAME_WIDTH / 2 - 140, 300);
        g.drawString("Press esc to enter Main Menu!", Game.GAME_WIDTH / 2 - 320, 450);
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            playing.resetAll();
            Gamestate.state = Gamestate.MENU;
        }
    }

}
