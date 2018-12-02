package de.twins.ui;

public class ControlOption {

    private char up = 'w';
    private char down = 's';
    private char left = 'a';
    private char right = 'd';
    private char attack = 'e';

    public char getUp() {
        return up;
    }

    public void setUp(char up) {
        this.up = up;
    }

    public char getDown() {
        return down;
    }

    public void setDown(char down) {
        this.down = down;
    }

    public char getLeft() {
        return left;
    }

    public void setLeft(char left) {
        this.left = left;
    }

    public char getRight() {
        return right;
    }

    public void setRight(char right) {
        this.right = right;
    }

    public char getAttack() {
        return attack;
    }

    public void setAttack(char attack) {
        this.attack = attack;
    }
}
