package data;

public enum Genders {
    MALE("Male"), FEMALE("Female"), OTHER("Other");

    public final String descriptionGenders;

    Genders(String descriptionGenders) {
        this.descriptionGenders = descriptionGenders;
    }

    public String getDescriptionGenders() {
        return descriptionGenders;
    }
}
