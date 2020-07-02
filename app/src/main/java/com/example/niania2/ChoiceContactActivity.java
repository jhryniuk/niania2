package com.example.niania2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ChoiceContactActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_contact);
        this.read();
    }

    public final void read() {
        Cursor cursor = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, (String[])null, (String)null, (String[])null, "display_name");
        String[] from = new String[]{"display_name", "data1", "_id"};
        int[] to = new int[]{16908308, 16908309};
        SimpleCursorAdapter simple = new SimpleCursorAdapter((Context)this, 17367044, cursor, from, to, 2);

        this.listView = (ListView) findViewById(R.id.contacts);
        this.listView.setAdapter(simple);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String number = (((TextView)view.findViewById(android.R.id.text2)).getText()).toString();
                String text = getString(R.string.chosen_number_is) + " " + number;

                String name = (((TextView)view.findViewById(android.R.id.text1)).getText()).toString();

                Toast.makeText(ChoiceContactActivity.this, text, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ChoiceContactActivity.this, WorkActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("number", number);
                startActivity(intent);
                finish();
            }
        });
    }
}
