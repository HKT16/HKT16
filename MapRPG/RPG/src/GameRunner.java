import java.util.Scanner;

public class GameRunner {

    public static void main(String[] args) {
        //allows user input
        Scanner scan = new Scanner(System.in);
        System.out.print("Name your character: ");
        String input = scan.next();
        //premade rooms/dungeons
        Map room1 = new Map("room1");
        Map room2 = new Map("room2");
        //creates player and enemies
        Player player = new Player(0, 3, input);
        Character enemy = new Character(2, 2, "Slime", 10, 3, 0);
        //arrays of conditions needed to enter or leave a room
        int[] roomKill = new int[5];
        boolean[] roomSpawned = new boolean[5];
        //first spawning or player and enemy
        room1.placeObjectS(enemy);
        room1.placeObject(player);
        roomSpawned[0] = true;
        while ((!(input.equalsIgnoreCase("Quit"))) && player.getHealth() > 0) { // loops while input is not to quit and player is still alive
            System.out.println("What direction do you want to move? (WASD)");
            input = scan.next();
            if (player.getLocation() == 1) { //if the room the player is in is room1
                player.move(input, room1);
                if (room1.enemyInRange(player)) { // if player is near an enemy they can attack
                    System.out.println("\n" + enemy.getName() + " Encountered!");
                    while (player.getHealth() > 0 && enemy.getHealth() > 0) { // player and enemy will attack each other until one dies
                        player.attack(enemy);
                        enemy.attack(player);
                    }
                    if (enemy.getHealth() <= 0) { // if enemy dies print, spawn a new enemy and what they get for winning
                        System.out.println("Health: " + player.getHealth() + ", Coins: " + player.getCoins());
                        room1.removeObject(enemy);
                        roomKill[0]++;
                        if (roomKill[0] == 1) {
                            room1.removeObject(enemy);
                            enemy = new Character(4, 4, "Skeleton", 15, 3, 2);
                            room1.placeObject(enemy);
                        } else if (roomKill[0] == 2) {
                            System.out.println("Health: " + player.getHealth() + ", Coins: " + player.getCoins());
                            System.out.println("Enemy defeated: Found Rusty Chestplate, +2 Defense");
                            player.setDefense(player.getDefense() + 2);
                            room1.removeObject(enemy);
                            enemy = new Character(7, 5, "Goblin", 8, 4, 1);
                            room1.placeObject(enemy);
                            System.out.println("Health: " + player.getHealth() + ", Coins: " + player.getCoins());
                            System.out.println("Enemy defeated: Found Rusty Dagger, + 5 Strength");
                            player.setStrength(player.getStrength() + 5);
                        } else if (roomKill[0] == 3) {
                            room1.removeObject(enemy);
                            enemy = new Character(8, 2, "King Slime", 25, 4, 0);
                            room1.placeObject(enemy);
                            System.out.println("Health: " + player.getHealth() + ", Coins: " + player.getCoins());
                            System.out.println("Enemy defeated: Found Rusty Dagger, + 5 Strength");
                            player.setStrength(player.getStrength() + 5);
                        } else if (roomKill[0] == 4) {
                            room1.placeObject(new Entrance(8, 8));
                            System.out.println("Health: " + player.getHealth() + ", Coins: " + player.getCoins());
                            System.out.println("Enemy defeated: Key obtained!");

                        }
                    }
                }
            } 
             if(player.getLocation()==2){ // if room the player is in is room 2; code structure similar to room 1
                if(roomSpawned[1] == false){ 
                    room2.getGrid()[8][7] = player;
                    roomSpawned[1] = true;
                }
                player.move(input, room2);
                if (room2.enemyInRange(player)) {
                    System.out.println("\n" + enemy.getName() + " Encountered!");
                    while (player.getHealth() > 0 && enemy.getHealth() > 0) {
                        player.attack(enemy);
                        enemy.attack(player);
                    }
                    if (enemy.getHealth() <= 0) {
                        System.out.println("Health: " + player.getHealth() + ", Coins: " + player.getCoins());
                        room2.removeObject(enemy);
                        roomKill[1]++;
                        if (roomKill[1] == 1) {
                           
                        } else if (roomKill[1] == 2) {
                            
                        } else if (roomKill[1] == 3) {
                            
                        } else if (roomKill[1] == 4) {
                           
                        }
                    }
                }
            }
        }
        System.out.println("Game Over"); // only reaches here if the player tells game to quit or when they die
        scan.close();
    }

}
