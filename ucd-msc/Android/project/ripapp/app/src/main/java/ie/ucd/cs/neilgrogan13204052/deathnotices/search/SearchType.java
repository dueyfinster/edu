package ie.ucd.cs.neilgrogan13204052.deathnotices.search;


public enum SearchType {
    NAME("Name"), ADDRESS("Address");

    String name;

    SearchType(final String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
