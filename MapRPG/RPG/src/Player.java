public class Player extends Character {
    private String name;
    private int baseHealth = 20;
    private int health = 20;
    private int strength = 6;
    private int defense = 4;
    private int range = 1;
    private int coins = 0;
    private int location = 1;

    public Player(int r, int c, String name) { // Constructor
        super(r, c, name, "PLAYER");
        this.name = name;
    }

    public void attack(Character opponent) { // Calculates damage randomly with max being the value of strength and
                                             // reduces the targeted characters health by that amount
        int damage = ((int) (Math.random() * strength));
        int block = (int) (Math.random() * opponent.getDefense());

        if (damage > block) { // if damage is more than what is being blocked reduce opponents hp
            opponent.setHealth(opponent.getHealth() - (damage - block));
        } else if (block >= damage) { // if all damage is blocked, do nothing
            opponent.setHealth(opponent.getHealth());
        }
        System.out.println("--------------------------------------------------");
        if (damage > block) { // prints damage dealt and stats
            System.out.println(this.name + " did " + (damage - block) + " damage, " + opponent.getName() + " blocked "
                    + block + " damage" + "\n" + opponent.getName() + "'s health: " + opponent.getHealth());
        } else if (block >= damage) { // prints that no damage was dealt
            System.out.println(this.name + " did" + " 0 damage" + ", " + opponent.getName() + " blocked " + damage
                    + "\n" + opponent.getName() + "'s" + " health: " + opponent.getHealth());
        }
        System.out.println("--------------------------------------------------");
    }

    public void move(String direction, Map room) { // checks input for direction the player wants to move, if the spot
                                                   // they want to move to is empty or has a mappable. If mappable and
                                                   // is interactable, something will happen
        room.removeObject(this);
        if (direction.equalsIgnoreCase("W")) {
            if (boundsCheck(this.getCurrRow() - 1, this.getCurrCol(), room, 2)) {
                if (room.getGrid()[this.getCurrRow() - 1][this.getCurrCol()].getType().equals("V")) { // if vase, breaks
                                                                                                      // vase and places
                                                                                                      // either coin or
                                                                                                      // herb into the
                                                                                                      // grid
                    this.interact(this.getCurrRow() - 1, this.getCurrCol(), room);
                } else if (room.getGrid()[this.getCurrRow() - 1][this.getCurrCol()].getType().equals("$")) { // if coin,
                                                                                                             // moves to
                                                                                                             // coin and
                                                                                                             // collects
                                                                                                             // it,
                                                                                                             // gives
                                                                                                             // currency
                    this.setCurrRow(this.getCurrRow() - 1);
                    this.coins++;
                } else if (room.getGrid()[this.getCurrRow() - 1][this.getCurrCol()].getType().equals("%")) { // if herb,
                                                                                                             // moves to
                                                                                                             // herb and
                                                                                                             // collects
                                                                                                             // it,
                                                                                                             // buffs
                                                                                                             // character
                    this.setCurrRow(this.getCurrRow() - 1);
                    this.setBaseHealth(this.getbaseHealth() + 1);
                    if (this.getHealth() + 3 > this.getbaseHealth()) {
                        this.setHealth(this.getbaseHealth());
                    } else {
                        this.setHealth(this.getHealth() + 3);
                    }
                } else if (room.getGrid()[this.getCurrRow() - 1][this.getCurrCol()].getType().equals("E")) { // if
                                                                                                             // player
                                                                                                             // moves to
                                                                                                             // entrance,
                                                                                                             // increases
                                                                                                             // condition
                                                                                                             // that
                                                                                                             // decides
                                                                                                             // which
                                                                                                             // room to
                                                                                                             // place
                                                                                                             // the
                                                                                                             // player
                                                                                                             // in
                    location++;
                }
            } else if (boundsCheck(this.getCurrRow() - 1, this.getCurrCol(), room, 1)) { // if empty, player moves to
                                                                                         // that spot and nothing
                                                                                         // happens
                this.setCurrRow(this.getCurrRow() - 1);
            } else {
                System.out.println("You hit a wall"); // if player tries to move into a wall, stay in the same spot and
                                                      // print they hit a wall
            }
        } else if (direction.equalsIgnoreCase("A")) {
            if (boundsCheck(this.getCurrRow(), this.getCurrCol() - 1, room, 2)) {
                if (room.getGrid()[this.getCurrRow()][this.getCurrCol() - 1].getType().equals("V")) {
                    this.interact(this.getCurrRow(), this.getCurrCol() - 1, room);
                } else if (room.getGrid()[this.getCurrRow()][this.getCurrCol() - 1].getType().equals("$")) {
                    this.setCurrCol(this.getCurrCol() - 1);
                    this.coins++;
                } else if (room.getGrid()[this.getCurrRow()][this.getCurrCol() - 1].getType().equals("%")) {
                    this.setCurrCol(this.getCurrCol() - 1);
                    this.setBaseHealth(this.getbaseHealth() + 1);
                    if (this.getHealth() + 3 > this.getbaseHealth()) {
                        this.setHealth(this.getbaseHealth());
                    } else {
                        this.setHealth(this.getHealth() + 3);
                    }
                } else if (room.getGrid()[this.getCurrRow()][this.getCurrCol() - 1].getType().equals("E")) {
                    location++;
                }
            } else if (boundsCheck(this.getCurrRow(), this.getCurrCol() - 1, room, 1)) {
                this.setCurrCol(this.getCurrCol() - 1);
            } else {
                System.out.println("You hit a wall");
            }
        } else if (direction.equalsIgnoreCase("S")) {
            if (boundsCheck(this.getCurrRow() + 1, this.getCurrCol(), room, 2)) {
                if (room.getGrid()[this.getCurrRow() + 1][this.getCurrCol()].getType().equals("V")) {
                    this.interact(this.getCurrRow() + 1, this.getCurrCol(), room);
                } else if (room.getGrid()[this.getCurrRow() + 1][this.getCurrCol()].getType().equals("$")) {
                    this.setCurrRow(this.getCurrRow() + 1);
                    this.coins++;
                } else if (room.getGrid()[this.getCurrRow() + 1][this.getCurrCol()].getType().equals("%")) {
                    this.setCurrRow(this.getCurrRow() + 1);
                    this.setBaseHealth(this.getbaseHealth() + 1);
                    if (this.getHealth() + 3 > this.getbaseHealth()) {
                        this.setHealth(this.getbaseHealth());
                    } else {
                        this.setHealth(this.getHealth() + 3);
                    }
                } else if (room.getGrid()[this.getCurrRow() + 1][this.getCurrCol()].getType().equals("E")) {
                    location++;
                }
            } else if (boundsCheck(this.getCurrRow() + 1, this.getCurrCol(), room, 1)) {
                this.setCurrRow(this.getCurrRow() + 1);
            } else {
                System.out.println("You hit a wall");
            }
        } else if (direction.equalsIgnoreCase("D")) {
            if (boundsCheck(this.getCurrRow(), this.getCurrCol() + 1, room, 2)) {
                if (room.getGrid()[this.getCurrRow()][this.getCurrCol() + 1].getType().equals("V")) {
                    this.interact(this.getCurrRow(), this.getCurrCol() + 1, room);
                } else if (room.getGrid()[this.getCurrRow()][this.getCurrCol() + 1].getType().equals("$")) {
                    this.setCurrCol(this.getCurrCol() + 1);
                    this.coins++;
                } else if (room.getGrid()[this.getCurrRow()][this.getCurrCol() + 1].getType().equals("%")) {
                    this.setCurrCol(this.getCurrCol() + 1);
                    this.setBaseHealth(this.getbaseHealth() + 1);
                    if (this.getHealth() + 3 > this.getbaseHealth()) {
                        this.setHealth(this.getbaseHealth());
                    } else {
                        this.setHealth(this.getHealth() + 3);
                    }
                } else if (room.getGrid()[this.getCurrRow()][this.getCurrCol() + 1].getType().equals("E")) {
                    location++;
                }
            } else if (boundsCheck(this.getCurrRow(), this.getCurrCol() + 1, room, 1)) {
                this.setCurrCol(this.getCurrCol() + 1);
            } else {
                System.out.println("You hit a wall");
            }
        } else {
            System.out.println("Bad Input");
        }
        room.placeObject(this);
        System.out.println("Health: " + this.getHealth() + ", Coins: " + this.getCoins());
    }

    public boolean boundsCheck(int row, int column, Map room, int type) { // method use depends on type in parameter
        int conditionals = 0;
        if (type == 1) { // used to check if a space is in array bound and either null or a particle;
            if ((row >= 0 && row <= room.getGrid().length - 1) // if in array bounds
                    && (column >= 0 && column <= room.getGrid()[0].length - 1)) {
                conditionals++;
                if (room.getGrid()[row][column] == null || room.getGrid()[row][column].isInteractable()) { // if null or  particle
                    conditionals++;
                }

            }
            if (conditionals == 2) { // if both conditions met, return true
                return true;
            }
            return false;
        } else if (type == 2) { // used to check if grid being checked is in array bounds and not null
            if ((row >= 0 && row <= room.getGrid().length - 1) && (column >= 0 && column <= room.getGrid()[0].length - 1)) { //if in bounds
                conditionals++;
                if (room.getGrid()[row][column] != null) { // if not null
                    conditionals++;
                }

            }
            if (conditionals == 2) {
                return true;
            }
        }
        return false;
    }

    public void interact(int r, int c, Map room) { // if an object targeted is a vase, drop either a coin or herb into the grid
        if (room.getGrid()[r][c].isInteractable()) {
            if (room.getGrid()[r][c].getType().equals("V")) {
                if (Math.random() < 0.7) {
                    room.placeObject(new Coin(r, c));
                } else {
                    room.placeObject(new Herb(r, c));
                }
            }
        }
    }

    //Setters and Getters
    public int getLocation() {
        return location;
    }

    public void setLocation(int l) {
        location = l;
    }

    public String getSymbol() {
        return name.substring(0, 1).toUpperCase();
    }

    public boolean isAttackable() {
        return true;
    }

    public int getbaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int bh) {
        this.baseHealth = bh;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int h) {
        health = h;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public String getName() {
        return name;
    }

    public int getRange() {
        return range;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int c) {
        coins = c;
    }

    public boolean isAlive() {
        return true;
    }

    public String getType() {
        return "C";
    }

    public boolean isInteractable() {
        return false;
    }

    public boolean isParticle() {
        return false;
    }

}
