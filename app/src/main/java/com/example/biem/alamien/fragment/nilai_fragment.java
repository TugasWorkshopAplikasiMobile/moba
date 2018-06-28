package com.example.biem.alamien.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biem.alamien.R;
import com.example.biem.alamien.model.baseUrlApi;
import com.example.biem.alamien.serivices.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class nilai_fragment extends Fragment {
    private Snackbar snackbar;
    private ProgressBar pd;
    private TextView bin,bing,mtk,ipa,tpa;
    private String Urlinsert = "api/isidata";
    RequestQueue rq;
    com.example.biem.alamien.model.baseUrlApi baseUrlApi = new baseUrlApi();
    private String URL = baseUrlApi.getBaseUrl();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View nv = inflater.inflate(R.layout.fragment_nilai_fragment, container, false);
        rq=Volley.newRequestQueue(getContext());
        bin = nv.findViewById(R.id.bin);
        bing = nv.findViewById(R.id.bing);
        mtk = nv.findViewById(R.id.mtk);
        ipa = nv.findViewById(R.id.ipa);
        tpa= nv.findViewById(R.id.psikologi);
        return nv;
    }
    private void signupRequest() {
        pd.setVisibility(View.VISIBLE);

        SessionManager sessionManager = new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        final String mIduser = String.valueOf(user.get(sessionManager.ID_USER));
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL + Urlinsert,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String succes = jsonObject.getString("succes");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pd.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.setVisibility(View.GONE);

                    }
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}
