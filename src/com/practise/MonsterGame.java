package com.practise;

public class MonsterGame {
    public static int countTurnsToDefeatAll(int[] monsters, int attackPower) {
        int turns = 0;
        boolean allDead = false;

        while (!allDead) {
            allDead = true;
            for (int i = 0; i < monsters.length; i++) {
                if (monsters[i] > 0) {
                    monsters[i] -= attackPower;
                    allDead = false;
                }
            }
            if (!allDead) {
                turns++;
            }
        }

        return turns;
    }

    public static void main(String[] args) {
        int[] monsters = {10, 20, 15}; // HP of monsters
        int attackPower = 5;           // Damage per turn

        int turns = countTurnsToDefeatAll(monsters, attackPower);
        System.out.println("Total turns required: " + turns);
    }
}
