public class Wall extends Mappable{
    private String type = "W";

    public Wall(int r, int c){ //Constructor
        super(r,c);
    }

    //Setters and Getters
    public String getSymbol(){
        return "X";
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
        return false;
    }
}
