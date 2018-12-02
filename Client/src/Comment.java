

public class Comment extends Post {
    private String postText;
    private String date;
    public Comment()
    {
        postText = "";
        date = "";
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
    public void addLike()
    {

    }
    public void removeLike()
    {

    }
}
