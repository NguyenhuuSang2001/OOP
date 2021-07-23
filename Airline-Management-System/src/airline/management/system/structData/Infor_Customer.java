package airline.management.system.structData;

public class Infor_Customer {
    private String Flightcode, Name, Passportno, Pnrno, Address, Nationality, Gender, Phno;

    public Infor_Customer() {
    }

    public Infor_Customer(String flightcode, String name, String passportno, String pnrno, String address, String nationality, String gender, String phno) {
        Flightcode = flightcode;
        Name = name;
        Passportno = passportno;
        Pnrno = pnrno;
        Address = address;
        Nationality = nationality;
        Gender = gender;
        Phno = phno;
    }

    public String getFlightcode() {
        return Flightcode;
    }

    public void setFlightcode(String flightcode) {
        Flightcode = flightcode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassportno() {
        return Passportno;
    }

    public void setPassportno(String passportno) {
        Passportno = passportno;
    }

    public String getPnrno() {
        return Pnrno;
    }

    public void setPnrno(String pnrno) {
        Pnrno = pnrno;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPhno() {
        return Phno;
    }

    public void setPhno(String phno) {
        Phno = phno;
    }

    @Override
    public String toString() {
        return "Infor_Customer{" +
                "Flightcode='" + Flightcode + '\'' +
                ", Name='" + Name + '\'' +
                ", Passportno='" + Passportno + '\'' +
                ", Pnrno='" + Pnrno + '\'' +
                ", Address='" + Address + '\'' +
                ", Nationality='" + Nationality + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Phno='" + Phno + '\'' +
                '}';
    }

}
