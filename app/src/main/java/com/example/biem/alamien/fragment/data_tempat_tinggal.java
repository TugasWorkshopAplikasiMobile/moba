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

public class data_tempat_tinggal extends Fragment {
    private Snackbar snackbar;
    private Button gofrag8;
    private ProgressBar pd;
    private TextView stt,jr,lr,jk;
    private String Urlinsert = "api/isidata";
    com.example.biem.alamien.model.baseUrlApi baseUrlApi = new baseUrlApi();
    private String URL = baseUrlApi.getBaseUrl();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View nv = inflater.inflate(R.layout.fragment_data_tempat_tinggal, container, false);
        pd = new ProgressBar(getContext());
        stt= nv.findViewById(R.id.status_tmpt_tinggal);
        jr= nv.findViewById(R.id.jarak_rmh);
        lr= nv.findViewById(R.id.luas_rumah);
        jk= nv.findViewById(R.id.jumlah_kamar);
        gofrag8 = nv.findViewById(R.id.kefrag8);
        gofrag8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clik();
            }
        });
        return nv;
    }
    public void showSnackbar(String stringSnackbar){
        snackbar.make(getActivity().findViewById(android.R.id.content), stringSnackbar.toString(), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }
    public void clik() {
        String jarak_rumah = jr.getText().toString();
        String stts_tmpt_tinggal = stt.getText().toString();
        String luas_rmh = lr.getText().toString();
        String jmlh_kmr = jk.getText().toString();
        View focusView = null;
        boolean cancel = false;
        if (TextUtils.isEmpty(jarak_rumah)) {
            showSnackbar("harap di isi");
            jr.setError(getString(R.string.error_field_required));
            focusView = jr;
            jr.requestFocus();
            cancel = true;
        } else if (TextUtils.isEmpty(stts_tmpt_tinggal)) {
            showSnackbar("harap di isi");
            stt.setError(getString(R.string.error_field_required));
            focusView = stt;
            stt.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(luas_rmh)) {
            showSnackbar("harap di isi");
            lr.setError(getString(R.string.error_field_required));
            focusView = lr;
            lr.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(jmlh_kmr)) {
            showSnackbar("harap di isi");
            jk.setError(getString(R.string.error_field_required));
            focusView = jk;
            jk.requestFocus();
            cancel = true;
        }else{
            signupRequest();
        }

    }
    private void signupRequest() {
        pd.setVisibility(View.VISIBLE);
        gofrag8.setVisibility(View.GONE);

        final String jarak_rumah = jr.getText().toString().trim();
        final String stts_tmpt_tinggal = stt.getText().toString().trim();
        final String luas_rmh = lr.getText().toString().trim();
        final String jmlh_kmr = jk.getText().toString().trim();

        SessionManager sessionManager = new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        final String mIduser = String.valueOf(user.get(sessionManager.ID_USER));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL + Urlinsert,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String succes = jsonObject.getString("succes");
                            if (succes.equals("1")) {
//                                startActivity(new Intent(getApplicationContext(),bukti_bayar.class));
                                showSnackbar("Data berhasil di input");
                                android.support.v4.app.Fragment fragment = new cirikhas_frag();
                                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.screen_area,fragment);
                                ft.addToBackStack("detail");//action untuk bisa back ke fragment sebelumnya
                                ft.commit();
                            }else {
                                showSnackbar("Data Gagal input");
                                pd.setVisibility(View.GONE);
                                gofrag8.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            showSnackbar("Data gagal di input");
                            pd.setVisibility(View.GONE);
                            gofrag8.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showSnackbar("Data gagal di input, cek koneksimu" + error.toString());
                        pd.setVisibility(View.GONE);
                        gofrag8.setVisibility(View.VISIBLE);
                    }
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("jr", jarak_rumah);
                params.put("stt", stts_tmpt_tinggal);
                params.put("lr", luas_rmh);
                params.put("jk", jmlh_kmr);
                params.put("api", "data_tempat_tinggal");
                params.put("id_user", mIduser);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}