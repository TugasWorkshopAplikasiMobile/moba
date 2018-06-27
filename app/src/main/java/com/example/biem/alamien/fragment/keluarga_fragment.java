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

public class keluarga_fragment extends Fragment {
    private Snackbar snackbar;
    private Button gofrag4;
    private ProgressBar pd;
    TextView nl,np,tl,up,agm,kwn,tglbr,ak;
    private String Urlinsert = "api/isidata";
    com.example.biem.alamien.model.baseUrlApi baseUrlApi = new baseUrlApi();
    private String URL = baseUrlApi.getBaseUrl();
    private RadioButton lk,pr,islamA,islamI,kristenA,kristenI,katolikA,katolikI,hinduA,hinduI,budhaA,budhaI,wniA,wnaA,wniI,wnaI;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View nv =  inflater.inflate(R.layout.fragment_keluarga, container, false);
        pd = new ProgressBar(getContext());
        gofrag4 = nv.findViewById(R.id.kefrag4);
        nl = nv.findViewById(R.id.nama_lngkap);
        np = nv.findViewById(R.id.nm_panggilan);
        up = nv.findViewById(R.id.usia);
        wniA = nv.findViewById(R.id.wniA);
        wnaA = nv.findViewById(R.id.wnaA);
        wniI = nv.findViewById(R.id.wniI);
        wnaI = nv.findViewById(R.id.wnaI);
        tl = nv.findViewById(R.id.tanggal_lahir);
        lk = nv.findViewById(R.id.laki);
        pr = nv.findViewById(R.id.perempuan);
        islamA= nv.findViewById(R.id.islamA);
        islamI= nv.findViewById(R.id.islamI);
        kristenA= nv.findViewById(R.id.kristenA);
        kristenI= nv.findViewById(R.id.kristenI);
        katolikA = nv.findViewById(R.id.katolikA);
        katolikI = nv.findViewById(R.id.katolikI);
        hinduA= nv.findViewById(R.id.hinduA);
        hinduI= nv.findViewById(R.id.hinduI);
        budhaA= nv.findViewById(R.id.budhaA);
        budhaI= nv.findViewById(R.id.budhaI);
        tglbr = nv.findViewById(R.id.Tinggal_bersama);
        ak = nv.findViewById(R.id.anakke);
        gofrag4.setOnClickListener(new View.OnClickListener() {
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
        String nama_p = np.getText().toString();
        String nama_l = nl.getText().toString();
        String usia = up.getText().toString();
        String kwng = kwn.getText().toString();
        String tgl_ = tl.getText().toString();
        String agama = agm.getText().toString();
        String ting = tglbr.getText().toString();
        String ake = ak.getText().toString();
        View focusView = null;
        boolean cancel = false;
        if (!wnaA.isChecked() && !wniA.isChecked()){
            showSnackbar("Pilih Kewarganegaraan ");
        }
        if (!wnaI.isChecked() && !wniI.isChecked()){
            showSnackbar("Pilih Kewarganegaraan ");
        }
        if (!islamA.isChecked() && !kristenA.isChecked() && !katolikA.isChecked() && !hinduA.isChecked() && !budhaA.isChecked()){
            showSnackbar("Pilih Agama ");
        }
        if (!islamI.isChecked() && !kristenI.isChecked() && !katolikI.isChecked() && !hinduI.isChecked() && !budhaI.isChecked()){
            showSnackbar("Pilih Agama ");
        }
        if (!lk.isChecked() && !pr.isChecked()){
            showSnackbar("Pilih Jenis Kelamin");
        }else if (TextUtils.isEmpty(nama_l)) {
            showSnackbar("Isi Username");
            nl.setError(getString(R.string.error_field_required));
            focusView = nl;
            nl.requestFocus();
            cancel = true;
        } else if (TextUtils.isEmpty(nama_p)) {
            showSnackbar("Isi password");
            np.setError(getString(R.string.error_field_required));
            focusView = np;
            np.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(usia)) {
            showSnackbar("Isi password");
            up.setError(getString(R.string.error_field_required));
            focusView = up;
            up.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(kwng)) {
            showSnackbar("Isi password");
            kwn.setError(getString(R.string.error_field_required));
            focusView = kwn;
            kwn.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(tgl_)) {
            showSnackbar("Isi password");
            tl.setError(getString(R.string.error_field_required));
            focusView = tl;
            tl.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(agama)) {
            showSnackbar("Isi password");
            agm.setError(getString(R.string.error_field_required));
            focusView = agm;
            agm.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(ting)) {
            showSnackbar("Isi password");
            tglbr.setError(getString(R.string.error_field_required));
            focusView = tglbr;
            tglbr.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(ake)) {
            showSnackbar("Isi password");
            ak.setError(getString(R.string.error_field_required));
            focusView = ak;
            ak.requestFocus();
            cancel = true;
        }else{
            signupRequest();
        }

    }
    private void signupRequest() {
        pd.setVisibility(View.VISIBLE);
        gofrag4.setVisibility(View.GONE);

        final String nama_p = np.getText().toString().trim();
        final String nama_l = nl.getText().toString().trim();
        final String usia = up.getText().toString().trim();
        final String tgl_ = tl.getText().toString().trim();
        final String ting = tglbr.getText().toString().trim();
        final String ake = ak.getText().toString().trim();

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
                                android.support.v4.app.Fragment fragment = new riwayatsekolah_frag();
                                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.screen_area,fragment);
                                ft.addToBackStack("detail");//action untuk bisa back ke fragment sebelumnya
                                ft.commit();
                            }else {
                                showSnackbar("Data Gagal input");
                                pd.setVisibility(View.GONE);
                                gofrag4.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            showSnackbar("Data gagal di input");
                            pd.setVisibility(View.GONE);
                            gofrag4.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showSnackbar("Data gagal di input, cek koneksimu" + error.toString());
                        pd.setVisibility(View.GONE);
                        gofrag4.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nl", nama_l);
                params.put("np", nama_p);
                if (wniA.isChecked()){
                    params.put("kewarga", "1");
                }else {
                    params.put("kewarga", "2");
                }
                if (wniI.isChecked()){
                    params.put("kewarga", "1");
                }else {
                    params.put("kewarga", "2");
                }
                params.put("usia", usia);
                params.put("tl", tgl_);
                if (pr.isChecked()){
                    params.put("jk", "2");
                }else {
                    params.put("jk", "1");
                }
                if (islamA.isChecked()){
                    params.put("agamaA", "1");
                }else if (kristenA.isChecked()){
                    params.put("agamaA", "2");
                }else if (katolikA.isChecked()){
                    params.put("agamaA", "3");
                }else if (hinduA.isChecked()){
                    params.put("agamaA", "4");
                }else {
                    params.put("agamaA", "5");
                }
                if (islamI.isChecked()){
                    params.put("agamaI", "1");
                }else if (kristenI.isChecked()){
                    params.put("agamaI", "2");
                }else if (katolikI.isChecked()){
                    params.put("agamaI", "3");
                }else if (hinduI.isChecked()){
                    params.put("agamaI", "4");
                }else {
                    params.put("agamaI", "5");
                }
                params.put("tglbr", ting);
                params.put("ake", ake);
                params.put("id_user", mIduser);
                params.put("api", "datasiswa");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}