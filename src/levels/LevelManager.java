package levels;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level level;

    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites();
        level = new Level(LoadSave.getLevelData());

    }

    private void importOutsideSprites() {
        BufferedImage spriteSheet = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for(int j = 0; j < 4; j++)
            for(int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = spriteSheet.getSubimage(i * 32, j * 32, 32, 32);
        }
    }

    public void draw(Graphics g, int lvlOffset) {
        for(int j = 0; j < Game.TILES_IN_HEIGHT; j++)
            for(int i = 0; i < level.getLvlData()[0].length; i++) {
                int index = level.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], Game.TILES_SIZE * i - lvlOffset, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return level;
    }
}