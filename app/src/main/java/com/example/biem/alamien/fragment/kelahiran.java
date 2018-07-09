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

public class kelahiran extends Fragment {
    private Snackbar snackbar;
    private Button gofrag6;
    private ProgressBar pd;
    private TextView lk,kk,kl,pk,pmbntu_klhrn,bp,usiaibu;
    private String Urlinsert = "api/isidata";
    com.example.biem.alamien.model.baseUrlApi baseUrlApi = new baseUrlApi();
    private String URL = baseUrlApi.getBaseUrl();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View nv = inflater.inflate(R.layout.fragment_kelahiran, container, false);
        pd = new ProgressBar(getContext());
        lk= nv.findViewById(R.id.lama_kandungan);
        kk= nv.findViewById(R.id.keadaan_kandungan);
        kl= nv.findViewById(R.id.keadaan_lahir);
        pk= nv.findViewById(R.id.proses_kelahiran);
        pmbntu_klhrn = nv.findViewById(R.id.pembantu_kelahiran);
        bp= nv.findViewById(R.id.beratpanjang);
        usiaibu= nv.findViewById(R.id.usia_ibu_melahirkan);
        gofrag6 = nv.findViewById(R.id.kefrag6);
        gofrag6.setOnClickListener(new View.OnClickListener() {
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
        String keadaan_kndngan = kk.getText().toString();
        String lama_kndngan = lk.getText().toString();
        String keadaan_lhr = kl.getText().toString();
        String proses_klhrn = pk.getText().toString();
        String pembantu_klhrn = pmbntu_klhrn.getText().toString();
        String brt_pnjng = bp.getText().toString();
        String usia_ibu_mlhrkn = usiaibu.getText().toString();
        View focusView = null;
        boolean cancel = false;
        if (TextUtils.isEmpty(keadaan_kndngan)) {
            showSnackbar("harap di isi");
            kk.setError(getString(R.string.error_field_required));
            focusView = kk;
            kk.requestFocus();
            cancel = true;
        } else if (TextUtils.isEmpty(lama_kndngan)) {
            showSnackbar("harap di isi");
            lk.setError(getString(R.string.error_field_required));
            focusView = lk;
            lk.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(keadaan_lhr)) {
            showSnackbar("harap di isi");
            kl.setError(getString(R.string.error_field_required));
            focusView = kl;
            kl.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(proses_klhrn)) {
            showSnackbar("harap di isi");
            pk.setError(getString(R.string.error_field_required));
            focusView = pk;
            pk.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(pembantu_klhrn)) {
            showSnackbar("harap di isi");
            pmbntu_klhrn.setError(getString(R.string.error_field_required));
            focusView = pmbntu_klhrn;
            pmbntu_klhrn.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(brt_pnjng)) {
            showSnackbar("harap di isi");
            bp.setError(getString(R.string.error_field_required));
            focusView = bp;
            bp.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(usia_ibu_mlhrkn)) {
            showSnackbar("harap di isi");
            usiaibu.setError(getString(R.string.error_field_required));
            focusView = usiaibu;
            usiaibu.requestFocus();
            cancel = true;
        }else{
            signupRequest();
        }

    }
    private void signupRequest() {
        pd.setVisibility(View.VISIBLE);
        gofrag6.setVisibility(View.GONE);

        final String keadaan_kndngan = kk.getText().toString().trim();
        final String lama_kndngan = lk.getText().toString().trim();
        final String keadaan_lhr = kl.getText().toString().trim();
        final String proses_klhrn = pk.getText().toString().trim();
        final String pembantu_klhrn = pmbntu_klhrn.getText().toString().trim();
        final String brt_pnjng = bp.getText().toString().trim();
        final String usia_ibu_mlhrkn = usiaibu.getText().toString().trim();

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
                                gofrag6.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            showSnackbar("Data gagal di input");
                            pd.setVisibility(View.GONE);
                            gofrag6.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showSnackbar("Data gagal di input, cek koneksimu" + error.toString());
                        pd.setVisibility(View.GONE);
                        gofrag6.setVisibility(View.VISIBLE);
                    }
                })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("kk", keadaan_kndngan);
                params.put("lk", lama_kndngan);
                params.put("kl", keadaan_lhr);
                params.put("pk", proses_klhrn);
                params.put("pmbntu_klhrn", pembantu_klhrn);
                params.put("bp", brt_pnjng);
                params.put("usiaibu", usia_ibu_mlhrkn);
                params.put("api", "kelahiran");
                params.put("id_user", mIduser);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}