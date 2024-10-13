package data;

public enum Hobbies {
    SPORTS("Sports"), READING("Reading"), MUSIC("Music");

    public final String descriptionHobbies;

    Hobbies(String descriptionHobbies) {
        this.descriptionHobbies = descriptionHobbies;
    }

    public String getDescriptionHobbies(){
        return descriptionHobbies;
    }
}
