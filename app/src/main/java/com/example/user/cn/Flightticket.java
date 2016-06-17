package com.example.user.cn;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Flightticket extends Fragment implements ImageButton.OnClickListener, AdapterView.OnItemSelectedListener {
    Button c;
    EditText msg;

    private String mParam1;
    private String mParam2;
    private TextView txtdate;
    private int dd;
    private int mm;
    private int yy;
    private DatePickerDialog mDatePickerDialog;
    private ImageButton dates;
    private SimpleDateFormat dateFormatter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        return inflater.inflate(R.layout.fragment_blank, container, false);
        View view = null;
        view = inflater.inflate(R.layout.fragment_flightticket, container, false);
        c = (Button) view.findViewById(R.id.Conf);
        dates = (ImageButton) view.findViewById(R.id.imgbtncal);
        msg = (EditText) view.findViewById(R.id.msg);
        dates.setOnClickListener(this);
        c.setOnClickListener(this);

        txtdate = (TextView) view.findViewById(R.id.txtappdate);
        txtdate.setInputType(InputType.TYPE_NULL);
        String[] Flight =
                {"National", "International", "Chartered"};
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, Flight);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        spinner.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClick(View view) {
//        mDatePickerDialog.show();
//
//        String message = msg.getText().toString();
        sendEmail("hello dholu");
    }

    public void sendEmail(String messsage) {

        /*String[] to = new String[]{"stc2065@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_TEXT, messsage);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email"));*/


        try {



            GMailSender sender = new GMailSender("stc2065@gmail.com", "lordsaveus2");
            sender.sendMail("This is Subject",
                    "This is Body",
                    "stc2065@gmail.com",
                    "theanilpaudel@gmail.com");
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }




    }

    private void setDateTimeField() {
//        dates.setOnClickListener(this);
        mDatePickerDialog = new DatePickerDialog(getActivity(), mDateSetListener, 2016, 05, 16);
        Calendar newCalendar = Calendar.getInstance();
    }

    DatePickerDialog.OnDateSetListener mDateSetListener
            = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            yy = selectedYear;
            mm = selectedMonth;
            dd = selectedDay;
            //age.setDateOfBirth(startYear, startMonth, startDay);
            txtdate.setText("" + dd + "-" + (mm + 1) + "-" + yy);
            //calculateAge();
        }
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
