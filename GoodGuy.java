public class GoodGuy extends SuperHero {
    private String catchPhrase;

    public GoodGuy(String n, String j, String s, boolean c, int p, String cP) {
        super(n, j, s, c, p);
        catchPhrase = cP;
    }

    //getter method start
    public String getCatchPhrase() {
        return catchPhrase;
    }
    //getter method end

    //overrides getPowerLevel in SuperHero
    public int getPowerLevel() {
        return super.getPowerLevel() + (int) (Math.random() * 10) + 1;
    }

    //adds powerUp
    public void powerUp() {
        super.setPowerLevel(super.getPowerLevel() + (int) (Math.random() * 4) + 2);
    }

}
