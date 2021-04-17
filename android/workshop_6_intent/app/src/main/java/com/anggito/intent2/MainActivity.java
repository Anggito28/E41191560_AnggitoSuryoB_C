package com.anggito.intent2;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
// Deklarasi class private dan variable
    private static final int REQUEST_CODE = 75;
    private static final int PICK_CONTACT_REQUEST = 1;
    private Button btn_pindah, btn_pindahdata, buttonMoveWithObject, buttonDialNumber,
            buttonMaps, buttonShare, buttonSMS, buttonGetContactPhone, buttonPindahActivityUntukResult;
    private TextView textViewResult;
//methode button click
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_pindah = findViewById(R.id.btn_pindah);
        btn_pindah.setOnClickListener(this);

        btn_pindahdata = findViewById(R.id.btn_pindahdata);
        btn_pindahdata.setOnClickListener(this);

        buttonMoveWithObject = findViewById(R.id.btn_pindahobject);
        buttonMoveWithObject.setOnClickListener(this);

        buttonDialNumber = findViewById(R.id.btn_dial);
        buttonDialNumber.setOnClickListener(this);

        buttonMaps = findViewById(R.id.btn_maps);
        buttonMaps.setOnClickListener(this);

        buttonShare = findViewById(R.id.btn_share);
        buttonShare.setOnClickListener(this);

        buttonSMS = findViewById(R.id.btn_sms);
        buttonSMS.setOnClickListener(this);

        buttonPindahActivityUntukResult = findViewById(R.id.btn_pindahactivityresult);
        buttonPindahActivityUntukResult.setOnClickListener(this);

        buttonGetContactPhone = findViewById(R.id.btn_getcontact);
        buttonGetContactPhone.setOnClickListener(this);

        textViewResult = findViewById(R.id.textViewResult);
    }
//method melakukan aksi
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pindah:
                Intent moveIntent = new Intent(this, PindahActivity.class);
                startActivity(moveIntent);
                break;
            case R.id.btn_pindahdata:
                Intent moveWithDataIntent = new Intent(this, MoveWithDataActivity.class);
                moveWithDataIntent.putExtra("extra_name", "Anggito Suryo Baskoro");
                moveWithDataIntent.putExtra("extra_age", 20);
                startActivity(moveWithDataIntent);
                break;
            case R.id.btn_pindahobject:

                break;
            case R.id.btn_dial:
                String phoneNumber = "081335319674";
                Intent dialphone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
                startActivity(dialphone);
                break;
            case R.id.btn_maps:
                Intent maps = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-8.208222, 113.686913"));
                startActivity(maps);
                break;
            case R.id.btn_share:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_TEXT, "Mari Bermain PUBG STEAM");
                share.setType("text/plain");
                startActivity(Intent.createChooser(share, "share link"));
                break;
            case R.id.btn_sms:
                Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto: 081335319674"));
                sms.putExtra("sms_body", "Gas gann");
                startActivity(sms);
                break;
            case R.id.btn_pindahactivityresult:
                Intent moveForResultIntent = new Intent(this, MoveForResultActivity.class);
                startActivityForResult(moveForResultIntent, REQUEST_CODE);
                break;
            case R.id.btn_getcontact:
                Intent pickContact = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
                pickContact.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(pickContact, PICK_CONTACT_REQUEST);
                break;
        }
    }
//method pick
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_CONTACT_REQUEST){
            if (resultCode == RESULT_OK){
                Uri contactUri = data.getData();
                String []projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(column);
                textViewResult.setText(number);
            }
        }
        if (requestCode == REQUEST_CODE){
            if (resultCode == MoveForResultActivity.RESULT_CODE){
                int selectedValue = data.getIntExtra(MoveForResultActivity.EXTRA_VALUE, 0);
                textViewResult.setText("Hasil: "+selectedValue);
            }
        }
    }
}