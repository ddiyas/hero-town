public class BadGuy extends SuperHero {
    private String evilLaugh;

    public BadGuy(String n, String j, String s, boolean c, int p, String eL) {
        super(n, j, s, c, p);
        evilLaugh = eL;
    }

    // getter method start
    public String getEvilLaugh() {
        return evilLaugh;
    }
    // getter method end

    //adds powerUp
    public void powerUp() {
        super.setPowerLevel(super.getPowerLevel() + (int) (Math.random() * 6) + 1);
    }
}
