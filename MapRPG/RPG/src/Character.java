public class Character extends Mappable {
    private String name;
    private int health;
    private int strength;
    private int defense;
    private String characterType;

    public Character(int r, int c, String name, String characterType) { //Constructor
        super(r, c);
        this.name = name;
        this.characterType = characterType;
    }

    public Character(int r, int c, String name, int health, int strength, int defense) { //Constructor
        super(r, c);
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.defense = defense;
        characterType = "Monster";
    }


    public void attack(Player opponent) { //Calculates damage an enemy will do to the player.
        int damage = ((int)(Math.random() * strength));
        int block = (int)(Math.random() * opponent.getDefense());

        if (damage > block) {
            opponent.setHealth(opponent.getHealth() - (damage - block));
        } else if (block >= damage) {
            opponent.setHealth(opponent.getHealth());
        }
        System.out.println("--------------------------------------------------");
        if (damage > block) {
            System.out.println(this.name + " did " + (damage - block) + " damage, " + opponent.getName() + " blocked " + block + " damage" + "\n" + opponent.getName() + "'s health: " + opponent.getHealth());
        } else if (block >= damage) {
            System.out.println(this.name + " did" + " 0 damage" + ", " + opponent.getName() + " blocked " + damage + "\n" + opponent.getName() + "'s" + " health: " + opponent.getHealth());
        }
        System.out.println("--------------------------------------------------");
    }

    //Setters and Getters
    public String getSymbol() {
        return name.substring(0, 1).toUpperCase();
    }

    public boolean isAttackable() {
        return true;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int h){
        this.health = h;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int s){
        strength = s;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int d){
        defense = d;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return true;
    }

    public String getType(){
        return "C";
    }

    public boolean isInteractable(){
        if(characterType.equals("NPC")){
            return true;
        } 
        return false;
        
    }

    public boolean isParticle(){
        return false;
    }

}
