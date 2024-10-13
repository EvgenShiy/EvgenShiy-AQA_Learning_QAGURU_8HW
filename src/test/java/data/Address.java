package data;

public class Address {
    private final String currentAddress;

    public Address(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getCurrentAddress(){
        return currentAddress;
    }

    @Override
    public String toString() {
        return currentAddress;
    }
}
