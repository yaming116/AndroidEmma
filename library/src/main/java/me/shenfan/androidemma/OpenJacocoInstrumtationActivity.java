package me.shenfan.androidemma;

import android.content.ComponentName;
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

        startInstrumentation(new ComponentName(getPackageName(),
                "me.shenfan.androidemma.EmmaInstrumentation"), null, null);
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
            process = Runtime.getRuntime().exec("sh");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execCommand(String command)  {
        Runtime runtime = Runtime.getRuntime();
        Process proc = null;
        try {
            proc = runtime.exec(command);
            if (proc.waitFor() != 0) {
                Log.e("test","exit value = " + proc.exitValue());
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line+" ");
            }
            System.out.println(stringBuffer.toString());

        } catch (InterruptedException e) {
            System.err.println(e);
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                proc.destroy();
            } catch (Exception e2) {
            }
        }
    }
}
