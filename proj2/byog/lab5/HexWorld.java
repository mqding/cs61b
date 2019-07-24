package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.*;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 28731353;
    private static final Random RANDOM = new Random(SEED);

    public static class Position {
         int x;
         int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    /**where Position is a very simple class with two variables p.x and p.y and no methods.
     *p specifies the lower left corner of the hexagon.
     * @param s means the number of first row, also the number of rows to the upper-center.
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        Position xy = new Position(p.x,p.y);

        for (int i = 0; i < s; i += 1) {
            addLine(world, xy, s+2*i, t);
            xy.y += 1;
            xy.x -= 1;
        }
        xy.y += s-1;
        xy.x += s;
        for (int i = 0; i < s; i += 1) {
            addLine(world, xy, s+2*i, t);
            xy.y -= 1;
            xy.x -= 1;
        }

        //p.y -= s;
        //p.x += (s-1);
    }

    public static void addLine(TETile[][]world, Position p, int length, TETile t) {
        for (int i = 0; i < length; i += 1) {
            world[p.x + i][p.y] = t;
        }
    }


    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.NOTHING;
            case 3: return Tileset.FLOOR;
            case 4: return Tileset.GRASS;
            default: return Tileset.NOTHING;
        }
    }

    public static void generateHexWorld(TETile[][]world, Position mid, int size, TETile t) {
        Position leftOne = new Position(mid.x - (2 * size - 1),mid.y + size);
        Position leftTwo = new Position(leftOne.x - (2 * size -1),leftOne.y + size);
        Position rightOne = new Position(mid.x + (2 * size -1),mid.y + size);
        Position rightTwo = new Position(rightOne.x + (2 * size - 1),rightOne.y + size);
        for (int i = 0; i < 5; i += 1) {
            addHexagon(world, mid, size, randomTile());
            mid.y += 2 * size;
        }

        for(int i = 0; i < 4; i += 1) {
            addHexagon(world, leftOne, size, randomTile());
            leftOne.y += 2 * size;
            addHexagon(world, rightOne, size, randomTile());
            rightOne.y += 2 * size;
        }

        for(int i = 0; i < 3; i += 1) {
            addHexagon(world, leftTwo, size, randomTile());
            leftTwo.y += 2 * size;
            addHexagon(world, rightTwo, size, randomTile());
            rightTwo.y += 2 * size;
        }

    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexWorld= new TETile[WIDTH][HEIGHT];
        //fillWithRandomTiles(randomTiles);
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                hexWorld[x][y] = Tileset.NOTHING;
            }
        }

        Position mid = new Position(20, 0);

        //for (int i = 0; i < 5 ; i += 1) {
        //    addHexagon(hexWorld, mid, 3, randomTile());
        //    mid.y += 2*3;
        //}

        generateHexWorld(hexWorld,mid,3,randomTile());
        //addHexagon(hexWorld, mid, 3, randomTile());

        //hexWorld[1][1] = randomTile();
        //hexWorld[2][2] = randomTile();
        //hexWorld[3][3] = Tileset.TREE;
        ter.renderFrame(hexWorld);
    }


}
