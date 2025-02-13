package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class LoadSave {

    public static final String PLAYER_ATLAS = "PlayerCharacters/wind_SpriteSheet_288x128.png";
    public static final String LEVEL_ATLAS = "OutsideSprites/outside_sprites.png";
    public static final String MENU_BUTTONS = "OutsideSprites/button_atlas.png";
    public static final String MENU_BACKGROUND = "OutsideSprites/menu_background.png";
    public static final String BACKGROUND_OF_MENU = "OutsideSprites/background_of_menu.png";
    public static final String PAUSE_BACKGROUND = "OutsideSprites/pause_menu.png";
    public static final String SOUND_BUTTONS = "OutsideSprites/sound_button.png";
    public static final String URM_BUTTONS = "OutsideSprites/urm_buttons.png";
    public static final String VOLUME_BUTTONS = "OutsideSprites/volume_buttons.png";
    public static final String PLAYING_BG_IMG = "OutsideSprites/playing_bg_img.png";
    public static final String BIG_CLOUDS = "OutsideSprites/big_clouds.png";
    public static final String SMALL_CLOUDS = "OutsideSprites/small_clouds.png";
    public static final String CRABBY_SPRITE = "EnemyCharacters/crabby_sprite.png";
    public static final String STATUS_BAR = "OutsideSprites/health_power_bar.png";
    public static final String COMPLETED_IMG = "OutsideSprites/completed_sprite.png";
    public static final String DEATH_SCREEN = "OutsideSprites/death_screen.png";

    public static BufferedImage getSpriteAtlas(String fileName) {
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

    public static BufferedImage[] getAllLevels() {
        URL url = LoadSave.class.getResource("/lvls");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File[] files = file.listFiles();
        File[] filesSorted = new File[files.length];

        for(int i = 0; i < filesSorted.length; i++)
            for(int j = 0; j < files.length; j++) {
                if(files[j].getName().equals((i + 1) + ".png"))
                    filesSorted[i] = files[j];
            }

        BufferedImage[] imgs = new BufferedImage[filesSorted.length];

        for(int i = 0; i < imgs.length; i++) {
            try {
                imgs[i] = ImageIO.read(filesSorted[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imgs;
    }
}
