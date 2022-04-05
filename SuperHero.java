public class SuperHero extends Person {
    private String superPower;
    private boolean cape;
    private int powerLevel;

    public SuperHero(String n, String j, String s, boolean c, int p) {
        super(n, j);
        superPower = s;
        cape = c;
        powerLevel = p;
    }

    //getter method start
    public String getSuperPower() {
        return superPower;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int newLevel) {
        powerLevel = newLevel;
    }
    //getter method end

    public boolean wearsCape() {
        return cape;
    }

    public void powerLevelModification(int newPowerLevel) {
        powerLevel = newPowerLevel;
    }
}
