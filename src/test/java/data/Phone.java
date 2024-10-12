package data;

public class Phone {
    private final String phone;

    public Phone(String phone){
        this.phone=phone;
    }

    public String getPhone(){
        return phone;
    }

    @Override
    public String toString() {
        return phone;
    }
}
