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

public class kuisioner extends Fragment {
    private Snackbar snackbar;
    private Button kirim;
    private ProgressBar pd;
    TextView alasand4,alasane4,alasand5,alasane5,jwbn1,jwbn2,jwbn3,jwbn4,jwbn5;
    private String Urlinsert = "api/isidata";
    com.example.biem.alamien.model.baseUrlApi baseUrlApi = new baseUrlApi();
    private String URL = baseUrlApi.getBaseUrl();
    private RadioButton A1,B1,C1,D1,E1,F1,A2,B2,C2,D2,E2,F2,A3,B3,C3,D3,E3,F3,A4,B4,C4,D4,E4,A5,B5,C5,D5,E5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View nv =  inflater.inflate(R.layout.fragment_kuisioner, container, false);
        pd = new ProgressBar(getContext());
        A1 = nv.findViewById(R.id.a1);
        B1 = nv.findViewById(R.id.b1);
        C1 = nv.findViewById(R.id.c1);
        D1 = nv.findViewById(R.id.d1);
        E1 = nv.findViewById(R.id.e1);
        F1 = nv.findViewById(R.id.f1);
        A2 = nv.findViewById(R.id.a2);
        B2 = nv.findViewById(R.id.b2);
        C2 = nv.findViewById(R.id.c2);
        D2 = nv.findViewById(R.id.d2);
        E2 = nv.findViewById(R.id.e2);
        F2 = nv.findViewById(R.id.f2);
        A3 = nv.findViewById(R.id.a3);
        B3 = nv.findViewById(R.id.b3);
        C3 = nv.findViewById(R.id.c3);
        D3 = nv.findViewById(R.id.d3);
        E3 = nv.findViewById(R.id.e3);
        F3 = nv.findViewById(R.id.f3);
        A4 = nv.findViewById(R.id.a4);
        B4 = nv.findViewById(R.id.b4);
        C4 = nv.findViewById(R.id.c4);
        D4 = nv.findViewById(R.id.d4);
        E4 = nv.findViewById(R.id.e4);
        A5 = nv.findViewById(R.id.a5);
        B5 = nv.findViewById(R.id.b5);
        C5 = nv.findViewById(R.id.c5);
        D5 = nv.findViewById(R.id.d5);
        E5 = nv.findViewById(R.id.e5);
        alasand4 = nv.findViewById(R.id.alasan4d);
        alasane4 = nv.findViewById(R.id.alasan4e);
        alasand5 = nv.findViewById(R.id.alasan5d);
        alasane5 = nv.findViewById(R.id.alasan5e);
        kirim = nv.findViewById(R.id.kirim);
        kirim.setOnClickListener(new View.OnClickListener() {
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
        String jawab1 = jwbn1.getText().toString();
        String jawab2 = jwbn2.getText().toString();
        String jawab3 = jwbn3.getText().toString();
        String jawab4 = jwbn4.getText().toString();
        String jawab5 = jwbn5.getText().toString();
        String alasan_4d = alasand4.getText().toString();
        String alasan_4e = alasane4.getText().toString();
        String alasan_5d = alasand5.getText().toString();
        String alasan_5e = alasane5.getText().toString();

        View focusView = null;
        boolean cancel = false;
        if (!A1.isChecked() && !B1.isChecked() && !C1.isChecked() && !D1.isChecked() && !E1.isChecked() && !F1.isChecked()) {
            showSnackbar("Pilih Jawaban ");
        }
        if (!A2.isChecked() && !B2.isChecked() && !C2.isChecked() && !D2.isChecked() && !E2.isChecked() && !F2.isChecked()) {
            showSnackbar("Pilih Jawaban ");
        }
        if (!A3.isChecked() && !B3.isChecked() && !C3.isChecked() && !D3.isChecked() && !E3.isChecked() && !F3.isChecked()) {
            showSnackbar("Pilih Jawaban ");
        }
        if (!A4.isChecked() && !B4.isChecked() && !C4.isChecked()) {
            showSnackbar("Pilih Jawaban ");
        }else if (!D4.isChecked() && !E4.isChecked()){
            alasand4.setVisibility(View.VISIBLE);
            alasane4.setVisibility(View.VISIBLE);
        }
        if (!A5.isChecked() && !B5.isChecked() && !C5.isChecked()) {
            showSnackbar("Pilih Jawaban ");
        }else if (!D5.isChecked() && !E5.isChecked()){
            alasand5.setVisibility(View.VISIBLE);
            alasane5.setVisibility(View.VISIBLE);
        }else if (TextUtils.isEmpty(jawab1)) {
            showSnackbar("Tidak Boleh Kosong !");
            jwbn1.setError(getString(R.string.error_field_required));
            focusView = jwbn1;
            jwbn1.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(jawab2)) {
            showSnackbar("Tidak Boleh Kosong !");
            jwbn2.setError(getString(R.string.error_field_required));
            focusView = jwbn2;
            jwbn2.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(jawab3)) {
            showSnackbar("Tidak Boleh Kosong !");
            jwbn3.setError(getString(R.string.error_field_required));
            focusView = jwbn3;
            jwbn3.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(jawab4)) {
            showSnackbar("Tidak Boleh Kosong !");
            jwbn4.setError(getString(R.string.error_field_required));
            focusView = jwbn4;
            jwbn4.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(jawab5)) {
            showSnackbar("Tidak Boleh Kosong !");
            jwbn5.setError(getString(R.string.error_field_required));
            focusView = jwbn5;
            jwbn5.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(alasan_4d)) {
            showSnackbar("Tidak Boleh Kosong !");
            alasand4.setError(getString(R.string.error_field_required));
            focusView = alasand4;
            alasand4.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(alasan_4e)) {
            showSnackbar("Tidak Boleh Kosong !");
            alasane4.setError(getString(R.string.error_field_required));
            focusView = alasane4;
            alasane4.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(alasan_5d)) {
            showSnackbar("Tidak Boleh Kosong !");
            alasand5.setError(getString(R.string.error_field_required));
            focusView = alasand5;
            alasand5.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(alasan_5e)) {
            showSnackbar("Tidak Boleh Kosong !");
            alasane5.setError(getString(R.string.error_field_required));
            focusView = alasane5;
            alasane5.requestFocus();
            cancel = true;
        }
    }
    private void signupRequest() {
        pd.setVisibility(View.VISIBLE);
        kirim.setVisibility(View.GONE);
        final String alasan_4d = alasand4.getText().toString().trim();
        final String alasan_4e = alasane4.getText().toString().trim();
        final String alasan_5d = alasand5.getText().toString().trim();
        final String alasan_5e = alasane5.getText().toString().trim();

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
//                                android.support.v4.app.Fragment fragment = new kesehatan_frag();
//                                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
//                                ft.replace(R.id.screen_area,fragment);
//                                ft.addToBackStack("detail");//action untuk bisa back ke fragment sebelumnya
//                                ft.commit();
                            } else {
                                showSnackbar("Data Gagal input");
                                pd.setVisibility(View.GONE);
                                kirim.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            showSnackbar("Data gagal di input");
                            pd.setVisibility(View.GONE);
                            kirim.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showSnackbar("Data gagal di input, cek koneksimu" + error.toString());
                        pd.setVisibility(View.GONE);
                        kirim.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("alasan4D", alasan_4d);
                params.put("alasan4E", alasan_4e);
                params.put("alasan5D", alasan_5d);
                params.put("alasan5E", alasan_5e);
                if (A1.isChecked()) {
                    params.put("jwbn1", "1");
                } else if (B1.isChecked()) {
                    params.put("jwbn1", "2");
                } else if (C1.isChecked()) {
                    params.put("jwbn1", "3");
                } else if (D1.isChecked()) {
                    params.put("jwbn1", "4");
                } else if (E1.isChecked()) {
                    params.put("jwbn1", "5");
                } else {
                    params.put("jwbn1", "6");
                }
                if (A2.isChecked()) {
                    params.put("jwbn2", "1");
                } else if (B2.isChecked()) {
                    params.put("jwbn2", "2");
                } else if (C2.isChecked()) {
                    params.put("jwbn2", "3");
                } else if (D2.isChecked()) {
                    params.put("jwbn2", "4");
                } else if (E2.isChecked()) {
                    params.put("jwbn2", "5");
                } else {
                    params.put("jwbn2", "6");
                }
                if (A3.isChecked()) {
                    params.put("jwbn3", "1");
                } else if (B3.isChecked()) {
                    params.put("jwbn3", "2");
                } else if (C3.isChecked()) {
                    params.put("jwbn3", "3");
                } else if (D3.isChecked()) {
                    params.put("jwbn3", "4");
                } else if (E3.isChecked()) {
                    params.put("jwbn3", "5");
                } else {
                    params.put("jwbn3", "6");
                }
                if (A4.isChecked()) {
                    params.put("jwbn4", "1");
                } else if (B4.isChecked()) {
                    params.put("jwbn4", "2");
                } else if (C4.isChecked()) {
                    params.put("jwbn4", "3");
                } else if (D4.isChecked()) {
                    params.put("jwbn4", "4");
                } else {
                    params.put("jwbn4", "5");
                }
                if (A5.isChecked()) {
                    params.put("jwbn5", "1");
                } else if (B5.isChecked()) {
                    params.put("jwbn5", "2");
                } else if (C5.isChecked()) {
                    params.put("jwbn5", "3");
                } else if (D5.isChecked()) {
                    params.put("jwbn5", "4");
                } else {
                    params.put("jwbn5", "5");
                }
                params.put("id_user", mIduser);
                params.put("api", "kuisioner");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
