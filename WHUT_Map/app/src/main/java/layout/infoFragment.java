package layout;

import java.util.List;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.alfredgao.whut_map.R;
import com.example.alfredgao.whut_map.build_info_listadapter;
import com.example.alfredgao.whut_map.campus_build_info;

/**
 * A simple {@link Fragment} subclass.
 */
public class infoFragment extends Fragment {


    public infoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ListView info_list_view = (ListView)view.findViewById(R.id.listView);

        List<campus_build_info> mList = campus_build_info.campus_build_infoList;

        build_info_listadapter adapter = new build_info_listadapter(getActivity().getApplicationContext(),mList);
        info_list_view.setAdapter(adapter);
        return view;
    }

}
