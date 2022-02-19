public class Entrance extends Mappable {
    private String type = "E";

    public Entrance(int r, int c) { //Constructor
        super(r, c);
    }

    //Setters and Getters
    public String getSymbol() {
        return "O";
    }

    public String getType() {
        return type;
    }

    public boolean isAttackable() {
        return false;
    }

    public boolean isAlive() {
        return false;
    }

    public boolean isInteractable(){
        return true;
    }

    public boolean isParticle(){
        return false;
    }
}
