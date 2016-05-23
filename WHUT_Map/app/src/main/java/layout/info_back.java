package layout;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.alfredgao.whut_map.R;
/**
 * Created by AlfredGao on 5/22/16.
 */
public class info_back extends Activity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

       setContentView(R.layout.info_back_window);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int)(height*0.8));

        final EditText send_text = (EditText)findViewById(R.id.send_back_text);
        final EditText send_mail = (EditText)findViewById(R.id.send_email_text);
        send_mail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        Button send_but = (Button)findViewById(R.id.send_info_button);

        send_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String send_text_str = send_text.getText().toString();
                String send_mail_str = send_mail.getText().toString();
                if (send_text_str == null || send_text_str.length() == 0) {
                    //String send_text_str = send_text.getText().toString();
                    Toast.makeText(getApplicationContext(), "反馈信息不能为空", Toast.LENGTH_LONG).show();
                }
                else if (send_mail_str == null || send_mail_str.length() == 0) {
                    send_mail.setError("邮箱不能为空!");
                }
                else if (!checkEmail(send_mail_str)){
                    send_mail.setError("邮箱格式不正确");
                }
                else {
                    Toast.makeText(getApplicationContext(), "反馈已成功发送!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private boolean checkEmail(String s) {
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(s);
        return m.matches();
    }



}
