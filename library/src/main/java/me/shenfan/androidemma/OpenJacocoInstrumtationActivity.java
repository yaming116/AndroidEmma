package me.shenfan.androidemma;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Sun on 2016/7/20.
 */
public class OpenJacocoInstrumtationActivity extends AppCompatActivity {
    private static final String COMMAND = "{command}";
    private static final String COMMAND_INSTRUMENT = "am instrument " + COMMAND +
            "/me.shenfan.androidemma.EmmaInstrumentation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emma_instrumentation_main);
    }

    public void open(View view){
        adbCommand(COMMAND_INSTRUMENT.replace(COMMAND, getPackageName()));
    }

    public void generateCoverageReport(View view){
        EmmaUtil.dump(this);
    }

    private void adbCommand(String command){
        //adb push core code
        Process process = null;
        DataOutputStream os = null;
        Log.e("test", command);
        try {
            process = Runtime.getRuntime().exec("su");// the phone must be root,it can exctue the adb command
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
