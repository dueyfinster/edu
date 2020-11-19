package Lab2;

public class PerennialGarden extends Garden {
    public Plant getShade() {
        return new Plant("Astilbe");
    }
    public Plant getShadeFeed() {
        return new Plant("Feed: Duranium");
    }
    public Plant getCenter() {
        return new Plant("Dicentrum");
    }
    public Plant getCenterFeed() {
        return new Plant("Feed: Miracle Grow");
    }
    public Plant getBorder() {
        return new Plant("Sedum");
    }
    public Plant getBorderFeed() {
        return new Plant("Feed: Slugs");
    }

}
