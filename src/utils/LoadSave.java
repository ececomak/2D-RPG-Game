package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "PlayerCharacters/wind_SpriteSheet_288x128.png";
    public static final String LEVEL_ATLAS = "OutsideSprites/outside_sprites.png";
//  public static final String LEVEL_ONE_DATA = "OutsideSprites/level_one_data.png";
    public static final String LEVEL_ONE_DATA = "OutsideSprites/level_one_data_long.png";
    public static final String MENU_BUTTONS = "OutsideSprites/button_atlas.png";
    public static final String MENU_BACKGROUND = "OutsideSprites/menu_background.png";
    public static final String BACKGROUND_OF_MENU = "OutsideSprites/background_of_menu.png";
    public static final String PAUSE_BACKGROUND = "OutsideSprites/pause_menu.png";
    public static final String SOUND_BUTTONS = "OutsideSprites/sound_button.png";
    public static final String URM_BUTTONS = "OutsideSprites/urm_buttons.png";
    public static final String VOLUME_BUTTONS = "OutsideSprites/volume_buttons.png";



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
        BufferedImage spriteSheet = GetSpriteAtlas(LEVEL_ONE_DATA);
        int[][] lvlData = new int[spriteSheet.getHeight()][spriteSheet.getWidth()];

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
