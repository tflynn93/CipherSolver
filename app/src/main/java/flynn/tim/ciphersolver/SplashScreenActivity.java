package flynn.tim.ciphersolver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                SplashScreenActivity.this.startActivity(i);
                //overridePendingTransition(R.anim.abc_slide_in_bottom,R.anim.abc_slide_out_top);
                SplashScreenActivity.this.finish();
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}