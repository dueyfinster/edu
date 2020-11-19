package Lab2;

public class AnnualGarden extends Garden {
    public Plant getShade() {
        return new Plant("Coleus");
    }
    public Plant getShadeFeed() {
        return new Plant("Feed: Roses");
    }
    public Plant getCenter() {
        return new Plant("Marigold");
    }
    public Plant getCenterFeed() {
        return new Plant("Feed: Bees");
    }
    public Plant getBorder() {
        return new Plant("Alyssum");
    }
    public Plant getBorderFeed() {
        return new Plant("Feed: Slugs");
    }

}
