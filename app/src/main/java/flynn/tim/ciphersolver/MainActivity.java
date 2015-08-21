package flynn.tim.ciphersolver;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ColorDrawable newColor = new ColorDrawable(getResources().getColor(R.color.accent_material_light));
        getSupportActionBar().setBackgroundDrawable(newColor);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("");
        final ListView listview = (ListView) findViewById((R.id.listView));
        final ArrayList<Result> cipherList = new ArrayList<>();
        cipherList.add(new Result("Caesar Cipher", false, false));
        cipherList.add(new Result("Frequency Analysis", false, false));
        cipherList.add(new Result("Rot-13 Cipher", false, false));
        cipherList.add(new Result ("Vigenère Cipher", false, false));

        MainListAdapter adapter = new MainListAdapter(getApplicationContext(), R.layout.list_item_main, cipherList);
        listview.setAdapter(adapter);
        //Set click listener for each item in the listview
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                if(cipherList.get(position).getResult().contentEquals("Caesar Cipher"))
                {
                    Intent i = new Intent(MainActivity.this, CaesarCipherActivity.class);
                    MainActivity.this.startActivity(i);
                    //MainActivity.this.finish();
                }

                if(cipherList.get(position).getResult().contentEquals("Frequency Analysis"))
                {
                    Intent i = new Intent(MainActivity.this, FrequencyActivity.class);
                    MainActivity.this.startActivity(i);
                    //MainActivity.this.finish();
                }

                if(cipherList.get(position).getResult().contentEquals("Rot-13 Cipher"))
                {
                    Intent i = new Intent(MainActivity.this, Rot13CipherActivity.class);
                    MainActivity.this.startActivity(i);
                    //MainActivity.this.finish();
                }

                if(cipherList.get(position).getResult().contentEquals("Vigenère Cipher"))
                {
                    Intent i = new Intent(MainActivity.this, VigenereCipherActivity.class);
                    MainActivity.this.startActivity(i);
                    //MainActivity.this.finish();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
