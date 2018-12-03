import java.util.Date;

public class Comment  {
    private String postText;
    static private int _ID = 0;
    private int id;
    private Date date;
    private String time;
    private String userName;

    public Comment()
    {
        postText = "";
        date = new Date();
        id = ++this._ID;
        this.time = Long.toString(date.getTime());
        this.userName = "";

    }
    public void setPostText(String iPostText)
    {
        postText = iPostText;
    }
    public String getPostText()
    {
        return postText;
    }
    public void setDate(Date Date)
    {

        date = Date;
    }
    public String getDate()
    {
        return date.toString();
    }
    public void addLike()
    {

    }
    public void removeLike()
    {

    }

    public void setUserName(String userName){this.userName = userName;}
    public String getUserName(){return this.userName;}

    public String toJSON(){
        String temp = "{\"id\":" + this.id + ",\"text\":" + this.postText + ",";
        temp += "\"date\":" + this.date.toString() + "\"time\":" + this.time + ",";
        temp += "\"username\":" + this.userName + "}";

        return  temp;
    }

}
