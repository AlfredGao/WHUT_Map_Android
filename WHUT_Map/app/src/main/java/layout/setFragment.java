package layout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.alfredgao.whut_map.DataCleanManager;
import com.example.alfredgao.whut_map.MainActivity;
import com.example.alfredgao.whut_map.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class setFragment extends Fragment {


    public setFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View map =  inflater.inflate(R.layout.fragment_set, container, false);
        final Button about_us = (Button)map.findViewById(R.id.aboutus);
        final FragmentManager manager = getActivity().getSupportFragmentManager();
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(), aboutus_pop_window.class));
            }
        });

        final Button update_but = (Button)map.findViewById(R.id.update);
        update_but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "当前版本已是最新版本", Toast.LENGTH_LONG).show();
            }
        });

        final Button info_back_but = (Button)map.findViewById(R.id.infoback);
        info_back_but.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), info_back.class));
            }
        });

        final Button clear_cache = (Button)map.findViewById(R.id.clear_cache_button);
        try {
            String cache_button_text = "清除地图缓存           " + DataCleanManager.getTotalSize(getActivity().getApplicationContext());
            clear_cache.setText(cache_button_text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        clear_cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String show = null;
                    String cacheSize = DataCleanManager.getTotalSize(getActivity().getApplicationContext());
                    if (cacheSize.charAt(0) == '0'){
                        show = "当前缓存已为0, 无需清除";
                        String cache_button_text = "清除地图缓存           " + DataCleanManager.getTotalSize(getActivity().getApplicationContext());
                        clear_cache.setText(cache_button_text);
                    } else {
                        show = "已成功清除缓存 " + cacheSize;
                        String cache_button_text = "清除地图缓存           " + DataCleanManager.getTotalSize(getActivity().getApplicationContext());
                        clear_cache.setText(cache_button_text);
                    }
                    DataCleanManager.clearAllCache(getActivity().getApplicationContext());
                    Toast.makeText(getActivity().getApplicationContext(), show, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return map;
    }

}
