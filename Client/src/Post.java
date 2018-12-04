import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Post {
    private String postText;
    private String date;
    private String userName;
    private String time;
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

    public void addComment(Comment c){
        this.comments.add(c);
    }

    public void addLike(Like l){
        this.likes.add(l);
    }

    public String toJSON(){
        String temp = "{\"id\":" + this.id + ",\"text\":" + this.postText + ",";
        temp += "\"date\":" + this.date + "\"time\":" + this.time + ",";
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

    public Post parseJSON(String JSON){ //given a json string, return a new user with those parameters
        Post newPost = new Post();

        try {

            JSONObject obj = new JSONObject(JSON); //make json object, passing json string

            //getting parameters from objects given the keys
            JSONArray array = obj.getJSONArray("posts");
            for (int i = 0; i < array.length();i++ ){
                JSONObject temp = array.getJSONObject(i);

                String text = obj.getString("text");
                String date = obj.getString("date");
                String time = obj.getString("time");
                String username = obj.getString("time");

                JSONArray arrayComments = temp.getJSONArray("comments");

                for(int k = 0; k < arrayComments.length(); k++){
                    JSONObject temp2 = arrayComments.getJSONObject(k);


                    String datecomment = temp2.getString("date");
                    String timecomment = temp2.getString("time");
                    String usernamecomment = temp2.getString("username");
                    String textcomment = temp2.getString("text");


                    Comment newComment = new Comment(usernamecomment,datecomment,timecomment,textcomment);
                    newPost.addComment(newComment);

                }

                JSONArray arraylikes = temp.getJSONArray("likes");

                for(int j = 0; j < arraylikes.length(); j++){
                    JSONObject temp2 = arraylikes.getJSONObject(j);


                    String datelike = temp2.getString("date");
                    String timelike = temp2.getString("time");
                    String usernamelike = temp2.getString("time");

                    Like newLike = new Like(usernamelike,datelike,timelike);
                    newPost.addLike(newLike);

                }


            }


        }
        catch (JSONException jex){
            System.out.println("JSON is not correct");
        }

        return newPost;

    }
}
