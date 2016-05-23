package layout;

import com.example.alfredgao.whut_map.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 * Created by AlfredGao on 5/22/16.
 */
public class aboutus_pop_window extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));
    }

}
