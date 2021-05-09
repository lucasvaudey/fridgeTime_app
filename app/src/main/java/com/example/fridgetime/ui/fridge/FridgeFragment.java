package com.example.fridgetime.ui.fridge;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fridgetime.R;

import java.util.HashMap;
import java.util.Map;

public class FridgeFragment extends Fragment {

    private FridgeViewModel mViewModel;
    private FridgeListAdaptater adaptater;

    public static FridgeFragment newInstance() {
        return new FridgeFragment();
    }
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fridge_fragment, container, false);
        recyclerView = view.findViewById(R.id.fridgeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        Map<String, Integer> test = new HashMap<String, Integer>();
        test.put("lait", 200);
        test.put("oeuf", 400);
        test.put("bite", 300);
        test.put("hodfs", 300);
        test.put("biisjdf", 300);
        test.put("bitesdjf" ,2939);
        test.put("sjdfjal", 293);
        test.put("sjlk", 20394);
        test.put("jsdflk", 20394);
        test.put("jsdfl", 20394);
        test.put("sjdflks", 20394);
        test.put("sjdfjklas", 20394);
        test.put("slajfkd", 20394);
        test.put("sjdflasd", 20394);
        test.put("jslfkdslfk", 20394);
        test.put("sjaldkfsa", 20394);
        test.put("sdalfkjasd", 20394);
        test.put("dkjlfas", 20394);
        test.put("jsadlfj", 20394);
        test.put("jdsflal", 20394);
        test.put("jdfljkahdf", 20394);
        test.put("ajskdflas", 20394);
        test.put("sdhjkfas", 20394);
        test.put("dfjkhaskdfl", 20394);
        test.put("jdhlasdjfhjs", 20394);
        test.put("dhfjkashf", 20394);
        test.put("hsdfjlkha", 20394);
        test.put("jdflasd", 20394);
        test.put("jsflkasf", 20394);
        test.put("jfhljkadshfj", 20394);
        test.put("jdfkla", 20394);
        test.put("jsdhfljka", 20394);
        test.put("dfjkasdh", 20394);
        test.put("jdfka", 20394);
        test.put("sadf", 20394);
        test.put("dsjfh", 20394);
        test.put("djfhjkasd", 20394);
        adaptater = new FridgeListAdaptater(test);
        recyclerView.setAdapter(adaptater);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FridgeViewModel.class);
        // TODO: Use the ViewModel
    }

}