package lab03.eim.systems.cs.pub.phonedialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText phoneNumberEditText;
    private ImageButton callImageButton;
    private ImageButton hangupImageButton;
    private ImageButton backspaceImageButton;
    private Button genericButton;
    private ImageButton contactsImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        phoneNumberEditText = (EditText)findViewById(R.id.phone_number_edit_text);
        callImageButton = (ImageButton)findViewById(R.id.call_image_button);
        callImageButton.setOnClickListener(callImageButtonClickListener);
        hangupImageButton = (ImageButton)findViewById(R.id.hangup_image_button);
        hangupImageButton.setOnClickListener(hangupImageButtonClickListener);
        backspaceImageButton = (ImageButton)findViewById(R.id.backspace_image_button);
        backspaceImageButton.setOnClickListener(backspaceButtonClickListener);

        genericButton = (Button)findViewById(R.id.number_0_button);
        genericButton.setOnClickListener(genericButtonClickListener);
        genericButton = (Button)findViewById(R.id.number_1_button);
        genericButton.setOnClickListener(genericButtonClickListener);
        genericButton = (Button)findViewById(R.id.number_2_button);
        genericButton.setOnClickListener(genericButtonClickListener);
        genericButton = (Button)findViewById(R.id.number_3_button);
        genericButton.setOnClickListener(genericButtonClickListener);
        genericButton = (Button)findViewById(R.id.number_4_button);
        genericButton.setOnClickListener(genericButtonClickListener);
        genericButton = (Button)findViewById(R.id.number_5_button);
        genericButton.setOnClickListener(genericButtonClickListener);
        genericButton = (Button)findViewById(R.id.number_6_button);
        genericButton.setOnClickListener(genericButtonClickListener);
        genericButton = (Button)findViewById(R.id.number_7_button);
        genericButton.setOnClickListener(genericButtonClickListener);
        genericButton = (Button)findViewById(R.id.number_8_button);
        genericButton.setOnClickListener(genericButtonClickListener);
        genericButton = (Button)findViewById(R.id.number_9_button);
        genericButton.setOnClickListener(genericButtonClickListener);
        genericButton = (Button)findViewById(R.id.star_button);
        genericButton.setOnClickListener(genericButtonClickListener);
        genericButton = (Button)findViewById(R.id.number_0_button);
        genericButton.setOnClickListener(genericButtonClickListener);
        genericButton = (Button)findViewById(R.id.pound_button);
        genericButton.setOnClickListener(genericButtonClickListener);


        contactsImageButton = (ImageButton)findViewById(R.id.hangup_image_button2);
        contactsImageButton.setOnClickListener(contactsImageButtonClickListener);
    }

    private CallImageButtonClickListener callImageButtonClickListener = new CallImageButtonClickListener();
    private class CallImageButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        Constants.PERMISSION_REQUEST_CALL_PHONE);
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
                startActivity(intent);
            }
        }
    }

    private HangupImageButtonClickListener hangupImageButtonClickListener = new HangupImageButtonClickListener();
    private class HangupImageButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            finish();
        }
    }

    private BackspaceButtonClickListener backspaceButtonClickListener = new BackspaceButtonClickListener();
    private class BackspaceButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String phoneNumber = phoneNumberEditText.getText().toString();
            if (phoneNumber.length() > 0) {
                phoneNumber = phoneNumber.substring(0, phoneNumber.length() - 1);
                phoneNumberEditText.setText(phoneNumber);
            }
        }
    }

    private GenericButtonClickListener genericButtonClickListener = new GenericButtonClickListener();
    private class GenericButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            phoneNumberEditText.setText(phoneNumberEditText.getText().toString() + ((Button)view).getText().toString());
        }
    }



    private ContactsImageButtonClickListener contactsImageButtonClickListener = new ContactsImageButtonClickListener();
    private class ContactsImageButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String phoneNumber = phoneNumberEditText.getText().toString();
            Log.i("[phone dialer]", phoneNumber);
            if (phoneNumber.length() > 0) {
                Intent intent = new Intent("ro.pub.cs.systems.eim.lab04.contactsmanager.intent.action.ContactsManagerActivity");
                intent.putExtra("ro.pub.cs.systems.eim.lab04.contactsmanager.PHONE_NUMBER_KEY", phoneNumber);
                startActivityForResult(intent, 2017);
            } else {
                Toast.makeText(getApplication(), "Some Error", Toast.LENGTH_LONG).show();
            }
        }
    }
}