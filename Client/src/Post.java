import java.util.ArrayList;

public class Post {
    private String postText;
    private String date;
    static private int _ID = 0;
    private int id;
    private ArrayList<Comment> comments = new ArrayList();
    private ArrayList<Like> likes = new ArrayList();

    public Post()
    {
        postText = "";
        date = "";
        id = ++this._ID;
    }
    public void setPostText(String iPostText)
    {
        postText = iPostText;
    }
    public String getPostText()
    {
        return postText;
    }
    public void setDate(String Date)
    {
        date = Date;
    }
    public String getDate()
    {
        return date;
    }

    public String toJSON(){
        String temp = "{\"id\":" + this.id + ",\"text\":" + this.postText + ",";
        temp += "\"date\":" + this.date.toString() + "\"time\":" + this.time + ",";
        temp += "\"username\":" + this.userName + ",\"comments\":[";

        for (Comment comment:this.comments){
            temp += comment.toJSON() + ",";
        }

        if (temp.charAt(temp.length() - 1) == ','){
            temp = temp.substring(0,temp.length()-1); //remove comma
        }

        temp += "],\"likes\":[";

        for (Like like:this.likes){
            temp += like.toJSON();
            temp += ",";
        }

        if (temp.charAt(temp.length() - 1) == ','){
            temp = temp.substring(0,temp.length()-1); //remove comma
        }

        temp += "]}";

        return temp;

    }
}
