package airline.management.system.structData;

public class SearchConditions {
    private String PlaceToGo, Destination, Date, TimeStart1, TimeStart2, number[], Airlines;

    public SearchConditions(String placeToGo, String destination, String date, String timeStart1, String timeStart2, String[] number, String airlines) {
        PlaceToGo = placeToGo;
        Destination = destination;
        Date = date;
        TimeStart1 = timeStart1;
        TimeStart2 = timeStart2;
        this.number = number;
        Airlines = airlines;
    }

    public SearchConditions() {
    }

    public String getPlaceToGo() {
        return PlaceToGo;
    }

    public void setPlaceToGo(String placeToGo) {
        PlaceToGo = placeToGo;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTimeStart1() {
        return TimeStart1;
    }

    public void setTimeStart1(String timeStart1) {
        TimeStart1 = timeStart1;
    }

    public String getTimeStart2() {
        return TimeStart2;
    }

    public void setTimeStart2(String timeStart2) {
        TimeStart2 = timeStart2;
    }

    public String[] getNumber() {
        return number;
    }

    public void setNumber(String[] number) {
        this.number = number;
    }

    public String getAirlines() {
        return Airlines;
    }

    public void setAirlines(String airlines) {
        Airlines = airlines;
    }

    @Override
    public String toString() {
        int num = 0;
        if(number != null)
            for (String i : number)
                num += Integer.parseInt(i);
        num = num < 1 ? 1 : num;
        String str = "";
        str += "Source='" + PlaceToGo +"' and Destination = '" + Destination ;
        if(!Date.equalsIgnoreCase(""))
            str += "' and Date = '" + Date ;
        str += "' and TimeStart >= '" + getTimeStart1() +"' and EmptySeat >= " + num;
        if(getTimeStart2() != null && getTimeStart1() != null && getTimeStart2().compareToIgnoreCase(getTimeStart1())>0)
            str += " and TimeStart <= '" + getTimeStart2()  +"'";
        if(!getAirlines().equalsIgnoreCase("Tất cả"))
            str += " and Airlines = '" + getAirlines() + "'" ;
        return str;
    }

    public static void main(String[] args) {
        SearchConditions sc = new SearchConditions();
        System.out.println(sc);
    }
}
