import java.util.Date;

public class Like extends User {
    private String userName1;
    private String date;
    public Like()
    {
        String userName1 = "";
        String date = "";
    }
    public Like(String userName, String date)
    {

    }

    public void setDate(String iDate)
    {
        date = iDate;
    }
    public String getDate()
    {
        return date;
    }
    public void setUserName(String iUserName1)
    {
        userName1 = iUserName1;
    }
    public String getUserName1()
    {
        return userName1;
    }

    public String toJSON(){
        String temp = "{\"date\":" + this.date.toString() + ",\"time\":" + this.time + ",";
        temp += "\"username\":" + this.userName + "}";

        return temp;
    }

}
