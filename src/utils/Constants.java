package utils;

public class Constants {

    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 3;
        public static final int FALLING = 3;
        public static final int GROUND = 5;
        public static final int ATTACK1 = 4;
        public static final int ATTACK2 = 6;

        public static int GetSpriteAmount(int player_action){
            switch(player_action){
                case RUNNING:
                    return 8;
                case IDLE:
                    return 8;
                case JUMP:
                    return 3;
                case ATTACK1:
                    return 7;
                case ATTACK2:
                    return 7;
                default:
                    return 1;
            }
        }
    }

}
