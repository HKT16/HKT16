public abstract class Mappable {
    private int currRow;
    private int currCol;
   
    public Mappable(int r, int c){ //Constructor
        currRow = r;
        currCol = c;
    }

    //Setters and Getters
    public int getCurrRow() {
        return currRow;
    }

    public int getCurrCol() {
        return currCol;
    }

    public void setCurrRow(int currRow) {
        this.currRow = currRow;
    }

    public void setCurrCol(int currCol) {
        this.currCol = currCol;
    }

    public abstract String getType();

    public abstract String getSymbol();

    public abstract boolean isAttackable();

    public abstract boolean isAlive();

    public abstract boolean isInteractable();

    public abstract boolean isParticle();

}
