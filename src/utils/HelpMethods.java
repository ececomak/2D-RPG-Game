package utils;

import entities.Crabby;
import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utils.Constants.EnemyConstants.CRABBY;

public class HelpMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        if (IsSolid(x, y, lvlData)) {
            return false;
        }

        if (IsSolid(x + width, y + height, lvlData)) {
            return false;
        }

        if (IsSolid(x + width, y, lvlData)) {
            return false;
        }

        return !IsSolid(x, y + height, lvlData);
    }


    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        int maxWidth = lvlData[0].length * Game.TILES_SIZE;

        if (x < 0 || x >= maxWidth) {
            return true;
        }
        if (y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        return IsTileSolid((int) xIndex, (int) yIndex, lvlData);

    }

    public static boolean IsTileSolid(int xTile, int yTile, int[][] lvlData) {
        int value = lvlData[yTile][xTile];

        return value >= 48 || value < 0 || value != 11;
    }

    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
        if(xSpeed > 0) {
            // Right
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            return tileXPos +  xOffset -1;
        } else {
            // Left
            return currentTile * Game.TILES_SIZE;
        }

    }

    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
        if(airSpeed > 0) {
            // Falling - Touching Floor
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset -1;
        }else  {
            // Jumping
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        // Check the pixel below bottom left and bottom right
        if(!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
            return IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData);

        return true;
    }

    public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
        if(xSpeed > 0)
            return IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData);
        else
            return IsSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
    }

    public static boolean IsAllTilesWalkable(int xStart, int xEnd, int y, int[][] lvlData) {
        for(int i = 0; i < xEnd - xStart; i++) {
            if (IsTileSolid(xStart + 1, y, lvlData))
                return false;
            if (!IsTileSolid(xStart + 1, y + 1, lvlData))
                return false;
        }
        return true;
    }

    public static boolean IsSightClear(int[][] lvlData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox, int yTile) {
       int firstXTile = (int) (firstHitbox.x / Game.TILES_SIZE);
       int secondXTile = (int) (firstHitbox.x / Game.TILES_SIZE);

       if(firstXTile > secondXTile)
           return IsAllTilesWalkable(secondXTile, firstXTile, yTile, lvlData);
       else
           return IsAllTilesWalkable(firstXTile, secondXTile, yTile, lvlData);
    }

    public static int[][] GetLevelData(BufferedImage spriteSheet) {
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

    public static ArrayList<Crabby> GetCrabs(BufferedImage spriteSheet) {
        ArrayList<Crabby> list = new ArrayList<>();
        for(int j = 0; j < spriteSheet.getHeight(); j++)
            for(int i = 0; i < spriteSheet.getWidth(); i++){
                Color color = new Color(spriteSheet.getRGB(i, j));
                int value = color.getGreen();
                if(value == CRABBY)
                    list.add(new Crabby(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
            }
        return list;
    }

    public static Point GetPlayerSpawn(BufferedImage spriteSheet) {
        for(int j = 0; j < spriteSheet.getHeight(); j++)
            for(int i = 0; i < spriteSheet.getWidth(); i++){
                Color color = new Color(spriteSheet.getRGB(i, j));
                int value = color.getGreen();
                if(value == 100)
                    return new Point(i * Game.TILES_SIZE, j * Game.TILES_SIZE);
            }
        return new Point(Game.TILES_SIZE, Game.TILES_SIZE);
    }

}
