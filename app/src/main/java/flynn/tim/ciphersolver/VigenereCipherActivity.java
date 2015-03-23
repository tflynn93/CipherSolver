package flynn.tim.ciphersolver;

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

import java.util.ArrayList;


public class VigenereCipherActivity extends ActionBarActivity {

    ArrayList<Result> resultsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigenere_cipher);
        final ListView listView = (ListView) findViewById(R.id.listView3);
        final EditText ciphertext = (EditText) findViewById(R.id.editText2);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        final EditText keyword = (EditText) findViewById(R.id.editText4);
        final RadioButton encrypt = (RadioButton) findViewById(R.id.radioButton3);
        final RadioButton decrypt = (RadioButton) findViewById(R.id.radioButton4);
        final Button solve = (Button) findViewById(R.id.button3);
        encrypt.setChecked(true);

        final VigenereCipher vc = new VigenereCipher();

        ciphertext.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        ciphertext.setFilters(new InputFilter[] {
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

        keyword.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        keyword.setFilters(new InputFilter[] {
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

        solve.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(VigenereCipherActivity.this.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                String result;
                resultsList.clear();
                if(ciphertext.getText().toString().equals(""))
                {
                    resultsList.add(new Result("No ciphertext entered!", false, true));
                }
                else if(keyword.getText().toString().equals(""))
                {
                    resultsList.add(new Result("No keyword entered!", false, true));
                }
                else {
                        if(encrypt.isChecked()==true)
                        {
                                result = vc.encrypt(ciphertext.getText().toString().toUpperCase(), keyword.getText().toString());
                                resultsList.add(new Result(result, true, false));
                        }
                        else
                        {
                                result = vc.decrypt(ciphertext.getText().toString().toUpperCase(), keyword.getText().toString());
                                resultsList.add(new Result(result, true, false));
                        }
                    }

                final MyListAdapter adapter = new MyListAdapter(getApplicationContext(), R.layout.list_item_caesar, resultsList);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        getMenuInflater().inflate(R.menu.menu_vigenere_cipher, menu);
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
