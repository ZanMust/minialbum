package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataHelper {

    List<User> mUsers = new ArrayList<>();

    private static DataHelper mInstance;

    private DataHelper(){ }

    public static synchronized DataHelper getInstance( ) {
        if (mInstance == null)
            mInstance=new DataHelper();
        return mInstance;
    }

    public void setUsers(JSONArray mJSONArray){
        for(int i = 0; i<mJSONArray.length();i++){
            try {
                JSONObject mUser = (JSONObject) mJSONArray.get(i);
                mUsers.add(new User(mUser.getString("name"),mUser.getString("username"),mUser.getInt("id")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<User> getUsers(){
        return (ArrayList<User>) mUsers;
    }

    public int getUserID(int i){
        return mUsers.get(i).id;
    }

    public void addAlbumToUser(int UserID, ArrayList<Album> albums){
        for (User user:mUsers
             ) {
            if(user.id==UserID){
                user.setAlbums(albums);
            }
        }
    }

    public ArrayList<Photo> getPicturesFromUserAlbum(int UserID, int AlbumID){
        for (User user:mUsers
                ) {
            if(user.id==UserID){
                for (Album al: user.albums
                     ) {
                    if(al.id==AlbumID)
                        return al.pictures;

                }
            }
        }
        return null;
    }

    public String getUsername(int UserID){
        for (User user:mUsers
                ) {
            if(user.id==UserID){
                return user.username;
            }
        }
        return "";
    }
    public String getAlbumName(int UserID,int AlbumID){
        for (User user:mUsers
                ) {
            if(user.id==UserID){
                for (Album al: user.albums
                        ) {
                    if(al.id==AlbumID)
                        return al.name;

                }
            }
        }
        return "";
    }



    public static class User {
        public String name;
        public String username;
        public int id;
        public ArrayList<Album> albums;


        public User(String name, String username, int id){
            this.name=name;
            this.username=username;
            this.id=id;
        }

        public void setAlbums(ArrayList<Album> albums){
            this.albums = albums;
        }



    }
    public static class Album {

        public String name;
        public Photo cover;
        public int id;
        public ArrayList<Photo> pictures;






        public Album(String name, int id, ArrayList<Photo> pictures){
            this.name=name;
            this.id=id;
            this.pictures = pictures;
            if(pictures.size()!=0){
                Random mRandom = new Random();
                cover= pictures.get(mRandom.nextInt(pictures.size()));
            }
        }





    }
    public static class Photo {

        public String title;
        public String url;
        public String thumbnailUrl;

        public Photo(String title, String url, String thumbnailUrl){
            this.title = title;
            this.url = url;
            this.thumbnailUrl = thumbnailUrl;
        }

    }
}


