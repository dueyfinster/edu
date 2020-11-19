package Lab2;

public class VeggieGarden extends Garden {
    public Plant getShade() {
        return new Plant("Broccoli");
    }
    public Plant getShadeFeed() {
        return new Plant("Feed: Carrots");
    }
    public Plant getCenter() {
        return new Plant("Corn");
    }
    public Plant getCenterFeed() {
        return new Plant("Feed: Manure");
    }
    public Plant getBorder() {
        return new Plant("Peas");
    }
    public Plant getBorderFeed() {
        return new Plant("Feed: Potatoes");
    }

}
