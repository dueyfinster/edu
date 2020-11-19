package Lab2;

public class RoseGarden extends Garden {
    public Plant getShade() {
        return new Plant("Red Rose");
    }
    public Plant getShadeFeed() {
        return new Plant("Feed: Cow manure");
    }
    public Plant getCenter() {
        return new Plant("Black Rose");
    }
    public Plant getCenterFeed() {
        return new Plant("Feed: Wasps");
    }
    public Plant getBorder() {
        return new Plant("Yellow Rose");
    }
    public Plant getBorderFeed() {
        return new Plant("Feed: Fecal matter");
    }
}
