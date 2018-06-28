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
    TextView na,ni,ttla,ttli,pndda,pnddi,agma,agmi,keskoma,keskomi,jura,juri,tlpka,tlpki,insa,insi,panga,pangi,lamkera,hpa,hpi,lamkeri,penga,pengi,tanga,tangi,alamta,alamti,alamka,alamki;
    private String Urlinsert = "api/isidata";
    com.example.biem.alamien.model.baseUrlApi baseUrlApi = new baseUrlApi();
    private String URL = baseUrlApi.getBaseUrl();
    private RadioButton islamA,islamI,kristenA,kristenI,katolikA,katolikI,hinduA,hinduI,budhaA,budhaI,wniA,wnaA,wniI,wnaI;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View nv =  inflater.inflate(R.layout.fragment_keluarga, container, false);
        pd = new ProgressBar(getContext());
        gofrag4 = nv.findViewById(R.id.kefrag4);
        na = nv.findViewById(R.id.nama_ayah);
        ni = nv.findViewById(R.id.nama_ibu);
        keskoma = nv.findViewById(R.id.kesempatan_kom_ayah);
        keskomi = nv.findViewById(R.id.kesempatan_kom_ibu);
        tlpka = nv.findViewById(R.id.tlp_kantor_ayah);
        tlpki = nv.findViewById(R.id.tlp_kantor_ibu);
        hpa = nv.findViewById(R.id.hp_ayah);
        hpi = nv.findViewById(R.id.hp_ibu);
        alamka = nv.findViewById(R.id.alamatkantor_ayah);
        alamki = nv.findViewById(R.id.alamatkantor_ibu);
        alamta = nv.findViewById(R.id.alamat_ayah);
        alamti = nv.findViewById(R.id.alamat_ibu);
        penga = nv.findViewById(R.id.penghasilan_ayah);
        pengi = nv.findViewById(R.id.penghasilan_ibu);
        tanga = nv.findViewById(R.id.tanggungan_ayah);
        tangi = nv.findViewById(R.id.tanggungan_ibu);
        lamkera = nv.findViewById(R.id.lamakerja_ayah);
        lamkeri = nv.findViewById(R.id.lamakerja_ibu);
        panga = nv.findViewById(R.id.pangkat_ayah);
        pangi = nv.findViewById(R.id.pangkat_ibu);
        insa = nv.findViewById(R.id.instansi_ayah);
        insi = nv.findViewById(R.id.instansi_ibu);
        jura = nv.findViewById(R.id.jur_ambil_ayah);
        juri = nv.findViewById(R.id.jur_ambil_ibu);
        pndda = nv.findViewById(R.id.pend_ayah);
        pnddi = nv.findViewById(R.id.pend_ibu);
        wniA = nv.findViewById(R.id.wniA);
        wnaA = nv.findViewById(R.id.wnaA);
        wniI = nv.findViewById(R.id.wniI);
        wnaI = nv.findViewById(R.id.wnaI);
        ttla = nv.findViewById(R.id.ttl_ayah);
        ttli = nv.findViewById(R.id.ttl_ibu);
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
        String nama_A = na.getText().toString();
        String nama_I = ni.getText().toString();
        String kesempatankomA = keskoma.getText().toString();
        String kesempatankomI = keskomi.getText().toString();
        String hpA = hpa.getText().toString();
        String hpI = hpi.getText().toString();
        String tlpkA = tlpka.getText().toString();
        String almtkantorA = alamka.getText().toString();
        String alamatA = alamta.getText().toString();
        String alamatI = alamti.getText().toString();
        String tanggunganA = tanga.getText().toString();
        String pendidikanA = pndda.getText().toString();
        String pendidikanI = pnddi.getText().toString();
        String tgl_a = ttla.getText().toString();
        String tgl_i = ttli.getText().toString();
        String agamaA = agma.getText().toString();
        String agamaI = agmi.getText().toString();
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
        }else if (TextUtils.isEmpty(nama_A)) {
            showSnackbar("Tidak Boleh Kosong !");
            na.setError(getString(R.string.error_field_required));
            focusView = na;
            na.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(nama_I)) {
            showSnackbar("Tidak Boleh Kosong !");
            ni.setError(getString(R.string.error_field_required));
            focusView = ni;
            ni.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(kesempatankomA)) {
            showSnackbar("Tidak Boleh Kosong !");
            keskoma.setError(getString(R.string.error_field_required));
            focusView = keskoma;
            keskoma.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(kesempatankomI)) {
            showSnackbar("Tidak Boleh Kosong !");
            keskomi.setError(getString(R.string.error_field_required));
            focusView = keskomi;
            keskomi.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(tlpkA)) {
            showSnackbar("Tidak Boleh Kosong !");
            tlpka.setError(getString(R.string.error_field_required));
            focusView = tlpka;
            tlpka.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(hpA)) {
            showSnackbar("Tidak Boleh Kosong !");
            hpa.setError(getString(R.string.error_field_required));
            focusView = hpa;
            hpa.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(hpI)) {
            showSnackbar("Tidak Boleh Kosong !");
            hpi.setError(getString(R.string.error_field_required));
            focusView = hpi;
            hpi.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(almtkantorA)) {
            showSnackbar("Tidak Boleh Kosong !");
            alamka.setError(getString(R.string.error_field_required));
            focusView = alamka;
            alamka.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(alamatA)) {
            showSnackbar("Tidak Boleh Kosong !");
            alamta.setError(getString(R.string.error_field_required));
            focusView = alamta;
            alamta.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(alamatI)) {
            showSnackbar("Tidak Boleh Kosong !");
            alamti.setError(getString(R.string.error_field_required));
            focusView = alamti;
            alamti.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(tanggunganA)) {
            showSnackbar("Tidak Boleh Kosong !");
            tanga.setError(getString(R.string.error_field_required));
            focusView = tanga;
            tanga.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(pendidikanA)) {
            showSnackbar("Tidak Boleh Kosong !");
            pndda.setError(getString(R.string.error_field_required));
            focusView = pndda;
            pndda.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(pendidikanI)) {
            showSnackbar("Tidak Boleh Kosong !");
            pnddi.setError(getString(R.string.error_field_required));
            focusView = pnddi;
            pnddi.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(tgl_a)) {
            showSnackbar("Tidak Boleh Kosong !");
            ttla.setError(getString(R.string.error_field_required));
            focusView = ttla;
            ttla.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(tgl_i)) {
            showSnackbar("Tidak Boleh Kosong !");
            ttli.setError(getString(R.string.error_field_required));
            focusView = ttli;
            ttli.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(agamaA)) {
            showSnackbar("Tidak Boleh Kosong !");
            agma.setError(getString(R.string.error_field_required));
            focusView = agma;
            agma.requestFocus();
            cancel = true;
        }else if (TextUtils.isEmpty(agamaI)) {
            showSnackbar("Tidak Boleh Kosong !");
            agmi.setError(getString(R.string.error_field_required));
            focusView = agmi;
            agmi.requestFocus();
            cancel = true;
        }else{
            signupRequest();
        }

    }
    private void signupRequest() {
        pd.setVisibility(View.VISIBLE);
        gofrag4.setVisibility(View.GONE);

        final String nama_A = na.getText().toString().trim();
        final String nama_I = ni.getText().toString().trim();
        final String kesempatankomA = keskoma.getText().toString().trim();
        final String kesempatankomI = keskomi.getText().toString().trim();
        final String tlpkA = tlpka.getText().toString().trim();
        final String tlpkI = tlpki.getText().toString().trim();
        final String hpA = hpa.getText().toString().trim();
        final String hpI = hpi.getText().toString().trim();
        final String almtkantorA = alamka.getText().toString().trim();
        final String almtkantorI = alamki.getText().toString().trim();
        final String alamatA = alamta.getText().toString().trim();
        final String alamatI = alamti.getText().toString().trim();
        final String tanggunganA = tanga.getText().toString().trim();
        final String tanggunganI = tangi.getText().toString().trim();
        final String penghasilanA = penga.getText().toString().trim();
        final String penghasilanI = pengi.getText().toString().trim();
        final String lamakerA = lamkera.getText().toString().trim();
        final String lamakerI = lamkeri.getText().toString().trim();
        final String pangkatA = panga.getText().toString().trim();
        final String pangkatI = pangi.getText().toString().trim();
        final String instansiA = insa.getText().toString().trim();
        final String instansiI = insi.getText().toString().trim();
        final String pendidikanA = pndda.getText().toString().trim();
        final String pendidikanI = pnddi.getText().toString().trim();
        final String tgl_a = ttla.getText().toString().trim();
        final String tgl_i = ttli.getText().toString().trim();
        final String jurusanA = jura.getText().toString().trim();
        final String jurusanI = juri.getText().toString().trim();

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
                params.put("na", nama_A);
                params.put("ni", nama_I);
                params.put("keskoma", kesempatankomA);
                params.put("keskomi", kesempatankomI);
                params.put("tlpkA", tlpkA);
                params.put("tlpkI", tlpkI);
                params.put("hpa", hpA);
                params.put("hpi", hpI);
                params.put("alamka", almtkantorA);
                params.put("alamki", almtkantorI);
                params.put("alamta", alamatA);
                params.put("alamti", alamatI);
                params.put("tanga", tanggunganA);
                params.put("tangi", tanggunganI);
                params.put("penga", penghasilanA);
                params.put("pengi", penghasilanI);
                params.put("lamkera", lamakerA);
                params.put("lamkeri", lamakerI);
                params.put("panga", pangkatA);
                params.put("pangi", pangkatI);
                params.put("insa", instansiA);
                params.put("insi", instansiI);
                params.put("jura", jurusanA);
                params.put("juri", jurusanI);
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
                params.put("pendidikanA", pendidikanA);
                params.put("pendidikanI", pendidikanI);
                params.put("ttla", tgl_a);
                params.put("ttli", tgl_i);
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
                params.put("id_user", mIduser);
                params.put("api", "datasiswa");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}