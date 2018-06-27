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


public class kesehatan_frag extends Fragment {
    private Snackbar snackbar;
    private Button gofrag5;
    private ProgressBar pd;
    private TextView bb,tb,minum_asi,perkem_2_bulan,kelainan,makan_tambahan,imunisasi,alergi,penglihatan,pendengaran,penampilan;
    private String Urlinsert = "api/isidata";
    com.example.biem.alamien.model.baseUrlApi baseUrlApi = new baseUrlApi();
    private String URL = baseUrlApi.getBaseUrl();
    RadioButton a,b,ab,o;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View nv = inflater.inflate(R.layout.fragment_kesehatan, container, false);
        pd = new ProgressBar(getContext());
        bb= nv.findViewById(R.id.bb);
        tb= nv.findViewById(R.id.tb);
        a = nv.findViewById(R.id.A);
        b = nv.findViewById(R.id.B);
        ab = nv.findViewById(R.id.AB);
        o = nv.findViewById(R.id.O);
        minum_asi= nv.findViewById(R.id.minum_asi);
        perkem_2_bulan= nv.findViewById(R.id.perkem_2_bulan);
        kelainan = nv.findViewById(R.id.kelaina);
        makan_tambahan= nv.findViewById(R.id.makan_tambahan);
        imunisasi= nv.findViewById(R.id.imunisasi);
        alergi= nv.findViewById(R.id.alergi);
        penglihatan= nv.findViewById(R.id.penglihatan);
        pendengaran= nv.findViewById(R.id.pendengaran);
        penampilan= nv.findViewById(R.id.penampilan);
        gofrag5 = nv.findViewById(R.id.kefrag5);

        gofrag5.setOnClickListener(new View.OnClickListener() {
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
        String t_badan = tb.getText().toString();
        String b_badan = bb.getText().toString();
        String m_asi = minum_asi.getText().toString();
        String p_2 = perkem_2_bulan.getText().toString();
        String klainan = kelainan.getText().toString();
        String m_tambahan = makan_tambahan.getText().toString();
        String imun = imunisasi.getText().toString();
        String alerg = alergi.getText().toString();
        String penglihat= penglihatan.getText().toString();
        String penngran= pendengaran.getText().toString();
        String pnmpilan= penampilan.getText().toString();
        View focusView = null;
        boolean cancel = false;
        if (TextUtils.isEmpty(t_badan)) {
            showSnackbar("harap di isi");
            tb.setError(getString(R.string.error_field_required));
            focusView = tb;
            tb.requestFocus();
            cancel = true;
        } else if (TextUtils.isEmpty(b_badan)) {
            showSnackbar("harap di isi");
            bb.setError(getString(R.string.error_field_required));
            focusView = bb;
            bb.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(m_asi)) {
            showSnackbar("harap di isi");
            minum_asi.setError(getString(R.string.error_field_required));
            focusView = minum_asi;
            minum_asi.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(p_2)) {
            showSnackbar("harap di isi");
            perkem_2_bulan.setError(getString(R.string.error_field_required));
            focusView = perkem_2_bulan;
            perkem_2_bulan.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(klainan)) {
            showSnackbar("harap di isi");
            kelainan.setError(getString(R.string.error_field_required));
            focusView = kelainan;
            kelainan.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(m_tambahan)) {
            showSnackbar("harap di isi");
            makan_tambahan.setError(getString(R.string.error_field_required));
            focusView = makan_tambahan;
            makan_tambahan.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(imun)) {
            showSnackbar("harap di isi");
            imunisasi.setError(getString(R.string.error_field_required));
            focusView = imunisasi;
            imunisasi.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(alerg)) {
            showSnackbar("harap di isi");
            alergi.setError(getString(R.string.error_field_required));
            focusView = alergi;
            alergi.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(penglihat)) {
            showSnackbar("harap di isi");
            penglihatan.setError(getString(R.string.error_field_required));
            focusView = penglihatan;
            penglihatan.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(penngran)) {
            showSnackbar("harap di isi");
            pendengaran.setError(getString(R.string.error_field_required));
            focusView = pendengaran;
            pendengaran.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(pnmpilan)) {
            showSnackbar("harap di isi");
            penampilan.setError(getString(R.string.error_field_required));
            focusView = penampilan;
            penampilan.requestFocus();
            cancel = true;
        }else{
            signupRequest();
        }

    }
    private void signupRequest() {
        pd.setVisibility(View.VISIBLE);
        gofrag5.setVisibility(View.GONE);

        final String t_badan = tb.getText().toString().trim();
        final String b_badan = bb.getText().toString().trim();
        final String m_asi = minum_asi.getText().toString().trim();
        final String p_2 = perkem_2_bulan.getText().toString().trim();
        final String klainan = kelainan.getText().toString().trim();
        final String m_tambahan = makan_tambahan.getText().toString().trim();
        final String imun = imunisasi.getText().toString().trim();
        final String alerg = alergi.getText().toString().trim();
        final String penglihat= penglihatan.getText().toString().trim();
        final String penngran= pendengaran.getText().toString().trim();
        final String pnmpilan= penampilan.getText().toString().trim();


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
            final String t_badan = tb.getText().toString().trim();
            final String b_badan = bb.getText().toString().trim();
            final String m_asi = minum_asi.getText().toString().trim();
            final String p_2 = perkem_2_bulan.getText().toString().trim();
            final String klainan = kelainan.getText().toString().trim();
            final String m_tambahan = makan_tambahan.getText().toString().trim();
            final String imun = imunisasi.getText().toString().trim();
            final String alerg = alergi.getText().toString().trim();
            final String penglihat= penglihatan.getText().toString().trim();
            final String penngran= pendengaran.getText().toString().trim();
            final String pnmpilan= penampilan.getText().toString().trim();
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("t_badan", t_badan);
                params.put("b_badan", b_badan);
                params.put("m_asi", m_asi);
                params.put("p_2", p_2);
                params.put("klainan", klainan);
                params.put("m_tambahan", m_tambahan);
                params.put("imun", imun);
                params.put("alerg", alerg);
                params.put("penglihat", penglihat);
                params.put("penngran", penngran);
                params.put("pnmpilan", pnmpilan);
                params.put("api", "kesehatan");
                params.put("id_user", mIduser);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}