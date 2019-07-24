package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.HexWorld;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;

    /*random seed.*/
    private static final long SEED = 287313353;
    private static final Random RANDOM = new Random(SEED);
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {

        ter.initialize(WIDTH, HEIGHT);

        TETile[][] finalWorldFrame= new TETile[WIDTH][HEIGHT];
        //fillWithRandomTiles(randomTiles);

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.NOTHING;
            }
        }


        Position start = new Position(5,0);


        drawUp(finalWorldFrame,start);
        drawUp(finalWorldFrame,start);
        for (int  i = 0; i < 5; i += 1) {

            int tileNum = RANDOM.nextInt(4);
            switch (tileNum) {
                case 0: drawUp(finalWorldFrame, start);
                case 1: drawRight(finalWorldFrame, start);
                case 2: drawLeft(finalWorldFrame,start);
                case 3: drawDown(finalWorldFrame,start);

                //default: ;
            }

        }
        ter.renderFrame(finalWorldFrame);
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] finalWorldFrame= new TETile[WIDTH][HEIGHT];
        //fillWithRandomTiles(randomTiles);

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.NOTHING;
            }
        }

        Position start = new Position(5,0);
        drawUp(finalWorldFrame,start);
        //drawUp(finalWorldFrame,start);
        ter.renderFrame(finalWorldFrame);

        return finalWorldFrame;
    }


    public class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public void drawRight(TETile[][] world, Position start) {
        int tileLength = 1 + RANDOM.nextInt(6);
        Position curr = new Position(start.x, start.y);
        for (int i = 0; i < tileLength; i += 1) {
            world[curr.x + i][curr.y] = Tileset.WALL;
            start.x += 1;
        }
    }

    public void drawLeft(TETile[][] world, Position start) {
        int tileLength = 1 + RANDOM.nextInt(6);
        Position curr = new Position(start.x, start.y);
        for (int i = 0; i < tileLength; i += 1) {
            world[curr.x - i][curr.y] = Tileset.WALL;
            start.x -= 1;
        }
    }

    public void drawUp(TETile[][] world, Position start) {
        int tileLength = 1 + RANDOM.nextInt(6);
        Position curr = new Position(start.x, start.y);
        for (int i = 0; i < tileLength; i += 1) {
            world[curr.x][curr.y + i] = Tileset.WALL;
            start.y += 1;
        }
    }
    public void drawDown(TETile[][] world, Position start) {
        int tileLength = 1 + RANDOM.nextInt(6);
        Position curr = new Position(start.x, start.y);
        for (int i = 0; i < tileLength; i += 1) {
            world[curr.x + i][curr.y - i] = Tileset.WALL;
            start.y -= 1;
        }
    }
}
