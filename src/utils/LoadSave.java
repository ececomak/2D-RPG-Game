package utils;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "PlayerCharacters/wind_SpriteSheet_288x128.png";
    public static final String LEVEL_ATLAS = "OutsideSprites/outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "OutsideSprites/level_one_data.png";

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage spriteSheet = null;
        InputStream inputStream = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            spriteSheet = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return spriteSheet;
    }

    public static int[][] GetLevelData() {
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage spriteSheet = GetSpriteAtlas(LEVEL_ONE_DATA);

        for(int j = 0; j < spriteSheet.getHeight(); j++)
            for(int i = 0; i < spriteSheet.getWidth(); i++){
                Color color = new Color(spriteSheet.getRGB(i, j));
                int value = color.getRed();
                if(value >= 48)
                    value = 0;
                lvlData[j][i] = color.getRed();
            }
        return lvlData;
    }
}
