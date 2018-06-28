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


public class cirikhas_frag extends Fragment {
    private Snackbar snackbar;
    private Button gofrag5;
    private ProgressBar pd;
    private TextView fisik_nonjol,pribadi_nonjol,bakat_nonjol,prestasi,nama_saudara,jk_saudara,pendidikan_saudara;
    private String Urlinsert = "api/isidata";
    com.example.biem.alamien.model.baseUrlApi baseUrlApi = new baseUrlApi();
    private String URL = baseUrlApi.getBaseUrl();
    RadioButton lk,pr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cirikhas_frag, container, false);
        fisik_nonjol = view.findViewById(R.id.fisik_nonjol);
        pribadi_nonjol = view.findViewById(R.id.pribadi_nonjol);
        bakat_nonjol = view.findViewById(R.id.bakat_nonjol);
        lk = view.findViewById(R.id.laki);
        pr = view.findViewById(R.id.perempuan);
        prestasi = view.findViewById(R.id.prestasi);
        nama_saudara = view.findViewById(R.id.nama_saudara);
        pendidikan_saudara = view.findViewById(R.id.pendidikan_saudara);
        return view;
    }
    public void showSnackbar(String stringSnackbar){
        snackbar.make(getActivity().findViewById(android.R.id.content), stringSnackbar.toString(), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }
    public void clik() {
        String f_nonjol = fisik_nonjol.getText().toString();
        String p_nonjol = pribadi_nonjol.getText().toString();
        String b_nonjol = bakat_nonjol.getText().toString();
        String pres= prestasi.getText().toString();
        String n_saudara = nama_saudara.getText().toString();
        String p_saudara = pendidikan_saudara.getText().toString();

        View focusView = null;
        boolean cancel = false;
        if (!lk.isChecked() && !pr.isChecked()){
            showSnackbar("harap pilih jenis kelamin");
        }
        if (TextUtils.isEmpty(f_nonjol)) {
            showSnackbar("harap di isi");
            fisik_nonjol.setError(getString(R.string.error_field_required));
            focusView = fisik_nonjol;
            fisik_nonjol.requestFocus();
            cancel = true;
        } else if (TextUtils.isEmpty(p_nonjol)) {
            showSnackbar("harap di isi");
            prestasi.setError(getString(R.string.error_field_required));
            focusView =pribadi_nonjol;
            pribadi_nonjol.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(b_nonjol)) {
            showSnackbar("harap di isi");
            bakat_nonjol.setError(getString(R.string.error_field_required));
            focusView = bakat_nonjol;
            bakat_nonjol.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(pres)) {
            showSnackbar("harap di isi");
            prestasi.setError(getString(R.string.error_field_required));
            focusView = prestasi;
            prestasi.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(n_saudara)) {
            showSnackbar("harap di isi");
            nama_saudara.setError(getString(R.string.error_field_required));
            focusView = nama_saudara;
            nama_saudara.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(p_saudara)) {
            showSnackbar("harap di isi");
            pendidikan_saudara.setError(getString(R.string.error_field_required));
            focusView = pendidikan_saudara;
            pendidikan_saudara.requestFocus();
            cancel = true;
        }else{
            signupRequest();
        }

    }
    private void signupRequest() {
        pd.setVisibility(View.VISIBLE);
        gofrag5.setVisibility(View.GONE);

        final String f_nonjol = fisik_nonjol.getText().toString().trim();
        final String p_nonjol = pribadi_nonjol.getText().toString().trim();
        final String b_nonjol = bakat_nonjol.getText().toString().trim();
        final String pres= prestasi.getText().toString().trim();
        final String n_saudara = nama_saudara.getText().toString().trim();
        final String p_saudara = pendidikan_saudara.getText().toString().trim();

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
//                                android.support.v4.app.Fragment fragment = new cirikhas_frag();
//                                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
//                                ft.replace(R.id.screen_area,fragment);
//                                ft.addToBackStack("detail");//action untuk bisa back ke fragment sebelumnya
//                                ft.commit();
                            }else {
                                showSnackbar("Data Gagal input");
                                pd.setVisibility(View.GONE);
                                gofrag5.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            showSnackbar("Data gagal di input");
                            pd.setVisibility(View.GONE);
                            gofrag5.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showSnackbar("Data gagal di input, cek koneksimu" + error.toString());
                        pd.setVisibility(View.GONE);
                        gofrag5.setVisibility(View.VISIBLE);
                    }
                })
        {
            final String f_nonjol = fisik_nonjol.getText().toString().trim();
            final String p_nonjol = pribadi_nonjol.getText().toString().trim();
            final String b_nonjol = bakat_nonjol.getText().toString().trim();
            final String pres= prestasi.getText().toString().trim();
            final String n_saudara = nama_saudara.getText().toString().trim();
            final String p_saudara = pendidikan_saudara.getText().toString().trim();

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("f_nonjol", f_nonjol);
                params.put("p_nonjol", p_nonjol);
                params.put("b_nonjol", b_nonjol);
                params.put("pres", pres);
                params.put("n_saudara", n_saudara);
                params.put("p_saudara", p_saudara);
                if (lk.isChecked()){
                    params.put("jk", "1");
                }else {
                    params.put("jk", "2");
                }
                params.put("api", "cirikhas");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}

