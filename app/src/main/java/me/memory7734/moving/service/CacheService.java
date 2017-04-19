package me.memory7734.moving.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.memory7734.moving.app.API;
import me.memory7734.moving.app.VolleyCallback;
import me.memory7734.moving.app.VolleySingleton;
import me.memory7734.moving.bean.DataBean;
import me.memory7734.moving.bean.UserBean;
import me.memory7734.moving.database.DataDao;

/**
 * Created by Elijah on 16/11/22.
 */

public class CacheService  {


    public static void update_health_data(final View view, final int date_time) {
        DataDao dao = new DataDao(view.getContext());
        if (dao.queryDataBean(date_time) != null) {
            StringRequest request = new StringRequest(Request.Method.POST, API.UPDATE_HEALTH_DATA, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    SharedPreferences sp = view.getContext().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
                    int userkey = sp.getInt("userkey", 0);
                    Gson gson = new Gson();
                    DataDao dao = new DataDao(view.getContext());
                    DataBean dataBean = dao.queryDataBean(date_time);
                    dataBean.setUserkey(userkey);
                    params.put("json", gson.toJson(dataBean));
                    return params;
                }
            };
            VolleySingleton.getVolleySingleton(view.getContext()).getRequestQueue().add(request);
        }
    }

    public static void update_health_all_data(final View view) {
        DataDao dao = new DataDao(view.getContext());
        if (dao.queryAll() != null) {
            StringRequest request = new StringRequest(Request.Method.POST, API.UPDATE_HEALTH_ALL_DATA, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    SharedPreferences sp = view.getContext().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
                    int userkey = sp.getInt("userkey", 0);
                    Gson gson = new Gson();
                    DataDao dao = new DataDao(view.getContext());
                    List<DataBean> dataBeanList = dao.queryAll();
                    HashMap<String, DataBean> map = new HashMap<>();
                    for (int i = 0; i < dataBeanList.size(); i++) {
                        dataBeanList.get(i).setUserkey(userkey);
                        map.put("data" + i, dataBeanList.get(i));
                    }
                    params.put("json", gson.toJson(map));
                    return params;
                }
            };
            VolleySingleton.getVolleySingleton(view.getContext()).getRequestQueue().add(request);
        }

    }

    public static void get_health_data(final View view,final int date_time) {
        final SharedPreferences sp = view.getContext().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        StringRequest request = new StringRequest(Request.Method.POST, API.GET_HEALTH_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                DataBean dataBean = gson.fromJson(response, DataBean.class);
                DataDao dao = new DataDao(view.getContext());
                dao.insertOrUpdateDataBean(dataBean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userkey", String.valueOf(sp.getInt("userkey", 0)));
                params.put("date_time", String.valueOf(date_time));
                return params;
            }
        };
        VolleySingleton.getVolleySingleton(view.getContext()).getRequestQueue().add(request);
    }

    public static void get_health_all_data(final View view) {
        final SharedPreferences sp = view.getContext().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        StringRequest request = new StringRequest(Request.Method.POST, API.GET_HEALTH_ALL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length() > 0) {
                    Gson gson = new Gson();
                    Map<String, DataBean> map = gson.fromJson(response, new TypeToken<Map<String, DataBean>>() {
                    }.getType());
                    DataDao dao = new DataDao(view.getContext());
                    for (Map.Entry<String, DataBean> entry : map.entrySet()) {
                        dao.insertOrUpdateDataBean(entry.getValue());
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userkey", String.valueOf(sp.getInt("userkey", 0)));
                return params;
            }
        };
        VolleySingleton.getVolleySingleton(view.getContext()).getRequestQueue().add(request);
    }

    public static void register(final View view, final String username, final String phone, final String password, final VolleyCallback callback) {
        StringRequest request = new StringRequest(Request.Method.POST, API.REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.matches("^\\d+$")) {
                    get_user_data(view,response);
                    callback.onSuccess(response);
                } else {
                    Snackbar.make(view, response, Snackbar.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("phone", phone);
                params.put("password", password);
                return params;
            }
        };
        VolleySingleton.getVolleySingleton(view.getContext()).getRequestQueue().add(request);
    }

    public static void login(final View view, final String phone, final String password, final VolleyCallback callback) {
        StringRequest request = new StringRequest(Request.Method.POST, API.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.matches("^\\d+$")) {
                    get_user_data(view,response);
                    get_health_all_data(view);
                    callback.onSuccess(response);
                } else {
                    Snackbar.make(view, response, Snackbar.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("phone", phone);
                params.put("password", password);
                return params;
            }
        };
        VolleySingleton.getVolleySingleton(view.getContext()).getRequestQueue().add(request);
    }

    public static void get_user_data(final View view,final String userkey) {
        StringRequest request = new StringRequest(Request.Method.POST, API.GET_USER_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                UserBean user = gson.fromJson(response, UserBean.class);
                SharedPreferences sp = view.getContext().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("userkey", user.getUserkey());
                editor.putString("phone", user.getPhone());
                editor.putString("username", user.getUsername());
                editor.putInt("birthdate", user.getBirthdate());
                editor.putString("city", user.getCity());
                editor.putString("gender", user.getGender());
                editor.putString("blood_type", user.getBlood_type());
                editor.putString("fitzpatrick_skin_type", user.getFitzpatrick_skin_type());
                editor.putString("wheelchair", user.getWheelchair());
                editor.apply();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userkey", userkey);
                return params;
            }
        };
        VolleySingleton.getVolleySingleton(view.getContext()).getRequestQueue().add(request);
    }

    public static void update_user_data(final View view, final VolleyCallback callback) {
        StringRequest request = new StringRequest(Request.Method.POST, API.UPDATE_USER_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences sp = view.getContext().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
                Map<String, String> params = new HashMap<>();
                params.put("userkey", String.valueOf(sp.getInt("userkey", 0)));
                params.put("birthdate", String.valueOf(sp.getInt("birthdate", 19900101)));
                params.put("gender", sp.getString("gender", null));
                params.put("city", sp.getString("city", null));
                params.put("wheelchair", sp.getString("wheelchair", null));
                params.put("blood_type", sp.getString("blood_type", null));
                params.put("fitzpatrick_skin_type", sp.getString("fitzpatrick_skin_type", null));
                return params;
            }
        };
        VolleySingleton.getVolleySingleton(view.getContext()).getRequestQueue().add(request);
    }
    public static void update_user_name(final View view,final String name, final VolleyCallback callback) {
        StringRequest request = new StringRequest(Request.Method.POST, API.UPDATE_USER_NAME, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences sp = view.getContext().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
                Map<String, String> params = new HashMap<>();
                params.put("userkey", String.valueOf(sp.getInt("userkey", 0)));
                params.put("username", name);
                return params;
            }
        };
        VolleySingleton.getVolleySingleton(view.getContext()).getRequestQueue().add(request);
    }
    public static void update_user_phone(final View view,final String phone, final VolleyCallback callback) {
        StringRequest request = new StringRequest(Request.Method.POST, API.UPDATE_USER_PHONE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences sp = view.getContext().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
                Map<String, String> params = new HashMap<>();
                params.put("userkey", String.valueOf(sp.getInt("userkey", 0)));
                params.put("phone", phone);
                return params;
            }
        };
        VolleySingleton.getVolleySingleton(view.getContext()).getRequestQueue().add(request);
    }
    public static void update_user_password(final View view,final String password, final VolleyCallback callback) {
        StringRequest request = new StringRequest(Request.Method.POST, API.UPDATE_USER_PASSWORD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences sp = view.getContext().getSharedPreferences("user_settings", Context.MODE_PRIVATE);
                Map<String, String> params = new HashMap<>();
                params.put("userkey", String.valueOf(sp.getInt("userkey", 0)));
                params.put("password", password);
                return params;
            }
        };
        VolleySingleton.getVolleySingleton(view.getContext()).getRequestQueue().add(request);
    }
}
