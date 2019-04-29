package utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class RequestManager {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";



    public static void getAllUsers(Context mContext, Response.Listener<JSONArray> mResponse, Response.ErrorListener mErrResponse){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(BASE_URL+"/users",mResponse,mErrResponse);
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(jsonArrayRequest);
    }

}
