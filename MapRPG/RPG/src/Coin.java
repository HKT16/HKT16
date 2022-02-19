public class Coin extends Mappable {
    private String type = "$";

    public Coin(int r, int c){ //Constructor
        super(r,c);
    }

    //Setters and Getters
    public String getSymbol(){
        return "$";
    }

    public String getType() {
        return type;
    }
    
    public boolean isAttackable(){
        return false;
    }

    public boolean isAlive(){
        return false;
    }

    public boolean isInteractable(){
        return false;
    }

    public boolean isParticle(){
        return true;
    }

}
