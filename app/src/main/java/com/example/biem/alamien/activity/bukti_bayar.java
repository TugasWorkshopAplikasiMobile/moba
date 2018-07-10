package com.example.biem.alamien.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.biem.alamien.R;
import com.example.biem.alamien.serivices.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class bukti_bayar extends AppCompatActivity {
    private ImageView setImage;
    private String url="http://192.168.1.12/alamin/WEB/alamin/android/uploadimg.php";
    private Bitmap bitmap;
    Snackbar snackbar;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bukti_bayar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back icon
        setImage = findViewById(R.id.img_set);
        pd = new ProgressDialog(bukti_bayar.this);
    }

    //back
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int t = item.getItemId();
        if (t==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void btn_browse(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Pilih Gambar"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode==RESULT_OK && data !=null && data.getData() !=null){
            Uri filepath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                setImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void upload(View view) {
        uploadimage();
    }
    private String getStringImage(Bitmap bitmap){
    ByteArrayOutputStream bm = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.JPEG,100,bm);
    byte[] imagebyte = bm.toByteArray();
    String encode = Base64.encodeToString(imagebyte,Base64.DEFAULT);
    return encode;
    }
    private void uploadimage() {
        pd.setMessage("Upload File . . .");
        pd.show();
        String response = null;
        final String finalResponse = response;
        final SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        final String mIduser = String.valueOf(user.get(sessionManager.ID_USER));
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                String kode = null;
                try {
                    kode = jsonObject.getString("kode");
                    if (kode.equals("1")){
                        Log.d( "TAG", "urlFoto: "+jsonObject.getString( "urlFoto" ) );
                        showSnackbar("Upload Foto Berhasil");
                        pd.dismiss();
                    }else {
                        showSnackbar("Gagal Input");
                        pd.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    showSnackbar("Gagal Input");
                    pd.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                pd.dismiss();
//                Log.d("ErrorResponse", finalResponse);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String image = getStringImage(bitmap);

                Map<String,String> params = new HashMap<>();
                params.put("id_user",mIduser);
                params.put("foto",image);
                params.put("api","ubahfoto");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void showSnackbar(String stringSnackbar){
        snackbar.make(findViewById(android.R.id.content), stringSnackbar.toString(), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();

    }
}
