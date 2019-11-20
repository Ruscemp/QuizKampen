package Client;

public enum Categories {
    GEOGRAPHY,
    NATURE,
    POLITICS,
    SPORT,
    FOOD,
    ALLMÄNT;

    @Override
    public String toString() {
        return super.toString().toLowerCase();

    }
    public static Categories getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}