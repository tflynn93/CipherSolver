package flynn.tim.ciphersolver;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class CaesarCipherActivity extends ActionBarActivity {

    ArrayList<Result> resultsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar_cipher);
        ColorDrawable newColor = new ColorDrawable(getResources().getColor(R.color.accent_material_light));//your color from res
        getSupportActionBar().setBackgroundDrawable(newColor);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        final EditText userString = (EditText) findViewById(R.id.editText3);
        final ListView listview = (ListView) findViewById((R.id.listView2));
        final RadioButton encrypt = (RadioButton) findViewById(R.id.radioButton);
        final RadioButton decrypt = (RadioButton) findViewById(R.id.radioButton2);
        final Spinner offset = (Spinner) findViewById(R.id.spinner);
        encrypt.setChecked(true);

        userString.setFilters(new InputFilter[] {
                new InputFilter() {
                    public CharSequence filter(CharSequence src, int start, int end, Spanned dst, int dstart, int dend) {
                        if(src.equals("")){ // for backspace
                            return src;
                        }
                        if(src.toString().matches("[a-zA-Z ]+")){
                            return src;
                        }
                        return "";
                    }
                }
        });
        final Button button2 = (Button) findViewById(R.id.button2);
        final CaesarCipher c = new CaesarCipher();
        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(CaesarCipherActivity.this.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                String result;
                resultsList.clear();
                if(userString.getText().toString().equals(""))
                {
                    resultsList.add(new Result("No string entered!", false, false));
                }
                else {

                    if(offset.getSelectedItem().toString().equalsIgnoreCase("All")) {
                        if(encrypt.isChecked()==true) {
                            for (int i = 1; i <= 26; i++) {
                                //Store the value of the encode with the given offset in result

                                result = c.encode(userString.getText().toString(), i);
                                //Display result with some formatting
                                if (i == 1) {
                                    //text.setText("\n" + "Offset " + i + ":\t" + result);
                                    resultsList.add(new Result("Offset " + i + ":\t" + result, false, false));
                                } else {
                                    //text.setText(text.getText() + "\n\n" + "Offset " + i + ":\t" + result);
                                    resultsList.add(new Result("Offset " + i + ":\t" + result, false, false));
                                }
                                //System.out.println("Offset " + i + ":\t" + result);
                                //Loop again with new offset
                            }
                        }
                        else
                        {
                            for (int i = 1; i <= 26; i++) {
                                //Store the value of the encode with the given offset in result

                                result = c.decode(userString.getText().toString(), i);
                                //Display result with some formatting
                                if (i == 1) {
                                    //text.setText("\n" + "Offset " + i + ":\t" + result);
                                    resultsList.add(new Result("Offset " + i + ":\t" + result, false, false));
                                } else {
                                    //text.setText(text.getText() + "\n\n" + "Offset " + i + ":\t" + result);
                                    resultsList.add(new Result("Offset " + i + ":\t" + result, false, false));
                                }
                            }
                        }
                    }
                    else
                    {
                        if(encrypt.isChecked()==true) {
                            result = c.encode(userString.getText().toString(), Integer.parseInt(offset.getSelectedItem().toString()));
                            resultsList.add(new Result("Offset " + offset.getSelectedItem().toString() + ": " + result, true, false));
                        }
                        else
                        {
                            result = c.decode(userString.getText().toString(), Integer.parseInt(offset.getSelectedItem().toString()));
                            resultsList.add(new Result("Offset " + offset.getSelectedItem().toString() + ": " + result, true, false));
                        }
                    }
                }

                final MyListAdapter adapter = new MyListAdapter(getApplicationContext(), R.layout.list_item_caesar, resultsList);
                listview.setAdapter(adapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                        if(resultsList.get(position).getEx()==false && resultsList.get(position).getChecked()==false) {
                            resultsList.get(position).setEx(true);
                        }
                        else if(resultsList.get(position).getEx() == true)
                        {
                            resultsList.get(position).setEx(false);
                            resultsList.get(position).setChecked(true);
                        }
                        else if(resultsList.get(position).getChecked()==true)
                        {
                            resultsList.get(position).setChecked(false);
                        }
                        adapter.updateList(resultsList);
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
