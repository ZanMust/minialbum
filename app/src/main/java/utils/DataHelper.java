package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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






    public static class User {
        public String name;
        public String username;
        public int id;


        public User(String name, String username, int id){
            this.name=name;
            this.username=username;
            this.id=id;
        }

    }
}


