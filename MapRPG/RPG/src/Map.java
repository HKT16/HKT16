import javax.swing.*;

public class Map {
    private Mappable[][] grid;

    public Map(String file) {
        MediaFile.setInputFile(file); //selects file from parameter
        // MediaFile.readString();
        String info = MediaFile.readString(); //reads the first line of a file
        String[] lineSegment = info.split(" "); //ignores " " / empty spaces
        int numRow = Integer.valueOf(lineSegment[0]); //contains all ints between spaces
        int colRow = Integer.valueOf(lineSegment[1]);
        grid = new Mappable[numRow][colRow]; // 2d array
        for (int r = 0; r < grid.length; r++) { //loops for how many rows there are in a file
            info = MediaFile.readString(); // reads next line of file
            String[] line = info.split(" ");
            for (int c = 0; c < grid[0].length; c++) { //goes through every int in a file
                if ((line[c]).equals("1")) { // all 1's become a wall
                    grid[r][c] = new Wall(r, c);
                } else if (line[c].equals("2")){ // all 2's become a vase
                    grid[r][c] = new Vase(r,c);
                } else if (line[c].equals("3")){ // all 3's become a locked entrance
                    grid[r][c] = new lockedEntrance(r,c);
                }              
            }
        }
    }

    public void placeObject(Mappable toPlace) { //places a mappable at a location and prints map
        grid[toPlace.getCurrRow()][toPlace.getCurrCol()] = toPlace;
        printMap();
    }

    public void placeObjectS(Mappable toPlace) { //places a mappable but does not print map
        grid[toPlace.getCurrRow()][toPlace.getCurrCol()] = toPlace;
    }


    public void removeObject(Mappable toRemove) { //removes the current mappable
        grid[toRemove.getCurrRow()][toRemove.getCurrCol()] = null;
    }

    public boolean enemyInRange(Player c) { //checks in all 4 directions if there is a mappable or character that is alive and attackable.
        for (int i = c.getRange(); i > 0; i--) {
            if (c.boundsCheck(c.getCurrRow() + i, c.getCurrCol(), this, 2)) {
                if (grid[c.getCurrRow() + i][c.getCurrCol()].isAttackable() && grid[c.getCurrRow() + i][c.getCurrCol()].isAlive()) {
                    return true;
                }
            }
        }
        for (int i = c.getRange(); i > 0; i--) {
            if (c.boundsCheck(c.getCurrRow() - i, c.getCurrCol(), this, 2)) {
                if (grid[c.getCurrRow() - i][c.getCurrCol()].isAttackable() && grid[c.getCurrRow() - i][c.getCurrCol()].isAlive()) {
                    return true;
                }
            }
        }
        for (int i = c.getRange(); i > 0; i--) {
            if (c.boundsCheck(c.getCurrRow(), c.getCurrCol() + i, this, 2)) {
                if (grid[c.getCurrRow()][c.getCurrCol() + i].isAttackable() && grid[c.getCurrRow()][c.getCurrCol() + i].isAlive()) {
                    return true;
                }
            }
        }
        for (int i = c.getRange(); i > 0; i--) {
            if (c.boundsCheck(c.getCurrRow(), c.getCurrCol() - i, this, 2)) {
                if (grid[c.getCurrRow()][c.getCurrCol() - i].isAttackable() && grid[c.getCurrRow()][c.getCurrCol() - i].isAlive()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printMap() { //prints all elements in a 2d array of mappables, representing them as their symbols
        System.out.println("");
        for (int i = 0; i < grid.length; i++) {
            for (int u = 0; u < grid[0].length; u++) {
                if (grid[i][u] != null) {
                    System.out.print(" " + grid[i][u].getSymbol());
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    //Setters and Getters
    public Mappable[][] getGrid() { //allows access to the 2d array of mappables by other classes
        return grid;
    }
}
