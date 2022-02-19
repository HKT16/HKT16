public class Vase extends Mappable{
    private String type = "V";

    public Vase(int r, int c){ //Constructor
        super(r,c);
    }
    
    //Setters and Getters
    public String getSymbol(){
        return "V";
    }

    public String getType() {
        return type;
    }
    
    public boolean isAttackable(){
        return true;
    }

    public boolean isAlive(){
        return false;
    }

    public boolean isInteractable(){
        return true;
    }

    public boolean isParticle(){
        return false;
    }
}

