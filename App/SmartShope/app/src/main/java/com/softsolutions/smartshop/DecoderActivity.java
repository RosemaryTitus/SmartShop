package com.softsolutions.smartshop;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.softsolutions.smartshop.qrcodereaderview.QRCodeReaderView;



public class DecoderActivity extends AppCompatActivity
    implements ActivityCompat.OnRequestPermissionsResultCallback, QRCodeReaderView.OnQRCodeReadListener {

  private static final int MY_PERMISSION_REQUEST_CAMERA = 0;

  private ViewGroup mainLayout;

  private TextView resultTextView;
  private QRCodeReaderView qrCodeReaderView;
  private CheckBox flashlightCheckBox;
  private CheckBox enableDecodingCheckBox;
  private PointsOverlayView pointsOverlayView;
  String data[]=new String[50];
  String value;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_decoder);

    mainLayout = (ViewGroup) findViewById(R.id.main_layout);

    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        == PackageManager.PERMISSION_GRANTED) {
      initQRCodeReaderView();
    } else {
      requestCameraPermission();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    if (qrCodeReaderView != null) {
      qrCodeReaderView.startCamera();
    }
  }

  @Override
  protected void onPause() {
    super.onPause();

    if (qrCodeReaderView != null) {
      qrCodeReaderView.stopCamera();
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                         @NonNull int[] grantResults) {
    if (requestCode != MY_PERMISSION_REQUEST_CAMERA) {
      return;
    }

    if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      Snackbar.make(mainLayout, "Camera permission was granted.", Snackbar.LENGTH_SHORT).show();
      initQRCodeReaderView();
    } else {
      Snackbar.make(mainLayout, "Camera permission request was denied.", Snackbar.LENGTH_SHORT)
          .show();
    }
  }

  // Called when a QR is decoded
  // "text" : the text encoded in QR
  // "points" : points where QR control points are placed
  @Override
  public void onQRCodeRead(String text, PointF[] points) {
    resultTextView.setText("QR VALUE  : "+text);
    value=text;
    pointsOverlayView.setPoints(points);
//    new itemview().execute();
    Intent i=new Intent(getApplicationContext(),ViewQRItem.class);
    i.putExtra("pid",value);

    startActivity(i);

  }

  private void requestCameraPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
      Snackbar.make(mainLayout, "Camera access is required to display the camera preview.",
          Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          ActivityCompat.requestPermissions(DecoderActivity.this, new String[] {
              Manifest.permission.CAMERA
          }, MY_PERMISSION_REQUEST_CAMERA);
        }
      }).show();
    } else {
      Snackbar.make(mainLayout, "Permission is not available. Requesting camera permission.",
          Snackbar.LENGTH_SHORT).show();
      ActivityCompat.requestPermissions(this, new String[] {
          Manifest.permission.CAMERA
      }, MY_PERMISSION_REQUEST_CAMERA);
    }
  }

  private void initQRCodeReaderView() {
    View content = getLayoutInflater().inflate(R.layout.content_decoder,mainLayout, true);

    qrCodeReaderView = (QRCodeReaderView) content.findViewById(R.id.qrdecoderview);
    resultTextView = (TextView) content.findViewById(R.id.result_text_view);
    flashlightCheckBox = (CheckBox) content.findViewById(R.id.flashlight_checkbox);
    enableDecodingCheckBox = (CheckBox) content.findViewById(R.id.enable_decoding_checkbox);
    pointsOverlayView = (PointsOverlayView) content.findViewById(R.id.points_overlay_view);

    qrCodeReaderView.setAutofocusInterval(2000L);
    qrCodeReaderView.setOnQRCodeReadListener(this);
    qrCodeReaderView.setBackCamera();
    flashlightCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        qrCodeReaderView.setTorchEnabled(isChecked);
      }
    });
    enableDecodingCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        qrCodeReaderView.setQRDecodingEnabled(isChecked);
      }
    });
    qrCodeReaderView.startCamera();
  }
//  public class itemview extends AsyncTask<String, String, String>
//  {
//
//    ProgressDialog pd;
//    protected void onPreExecute()
//    {
//      super.onPreExecute();
//      pd=new ProgressDialog(DecoderActivity.this);
//      pd.setCancelable(false);
//      //pd.isIndeterminate();
//      pd.setMessage("Getting..");
//      pd.setTitle("Please wait");
//      pd.show();
//      Log.d("inside onpre","onpre");
//    }
//
//    protected String doInBackground(String... params) {
//      // TODO Auto-generated method stub
//      String str="";
//
//      Log.d("inside inback","inside inback");
//
//      List<NameValuePair> pdat=new ArrayList<NameValuePair>(6);
//      pdat.add(new BasicNameValuePair("key","get_qr"));
//
//      pdat.add(new BasicNameValuePair("value",value));
//
//
//      HttpClient client=new DefaultHttpClient();
//      HttpPost mypdat=new HttpPost("http://dattaanjaneya.biz/quikly/controller1.php");
//
//      Log.d("iinback","inside inback");
//      try
//      {
//        mypdat.setEntity(new UrlEncodedFormEntity(pdat));
//        Log.d("post data","post data");
//      }
//      catch (UnsupportedEncodingException e)
//      {
//        e.printStackTrace();
//      }
//      try
//      {
//        Log.d("inside responce","response");
//        HttpResponse re=client.execute(mypdat);
//        HttpEntity entity=re.getEntity();
//        str= EntityUtils.toString(entity);
//        Log.d("response",str);
//        int status=re.getStatusLine().getStatusCode();
//        Log.d("Staus code",""+status);
//        if(status==200)
//        {
//          Log.d("insdie status check",str);
//          return str;
//
//        }}
//      catch (ClientProtocolException e)
//      {
//        e.printStackTrace();
//      }
//      catch (IOException e)
//      {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }return null;
//    }
//
//    protected void onPostExecute(String result)
//    {
//      super.onPostExecute(result);
//      Log.d("@@@@",result);
//
//      pd.dismiss();
//      if(!result.trim().equals("FAILED"))
//      {
//       data=result.split(":");
//        SharedPreferences sp=getSharedPreferences("item",Context.MODE_PRIVATE);
//        SharedPreferences.Editor ed=sp.edit();
//        ed.putString("ob1",data[0]);
//        ed.putString("ob2",data[1]);
//        ed.putString("ob3",data[2]);
//
//        ed.commit();
//        Bundle b=new Bundle();
//        b.putString("val",value);
//        Intent i=new Intent(getApplicationContext(),ViewPrice.class);
//        i.putExtras(b);
//        startActivity(i);
//       Toast.makeText(getApplicationContext(),data[0],Toast.LENGTH_SHORT).show();
//      }
//      else
//      {
//        Toast.makeText(getApplicationContext(), "Check Again", Toast.LENGTH_LONG).show();
//      }
//    }
//  }

}