package pathfinder;



public class Horse implements Figure {

    private int x;
    private int y;

    @Override
    public void setPosition(int[] position) {
        y = position[0];
        x = position[1];
    }

    //    0 1 0 2 0
    //    3 0 0 0 4
    //    0 0 x 0 0
    //    5 0 0 0 6
    //    0 7 0 8 0

    /**
     *
     * @return first x, then y
     */
    @Override
    public int[][] moves() {
        return new int [][]{{y - 2, x - 1},
                            {y - 2, x + 1},
                            {y - 1, x - 2},
                            {y - 1, x + 2},
                            {y + 1, x - 2},
                            {y + 1, x + 2},
                            {y + 2, x - 1},
                            {y + 2, x + 1}};
    }


}
