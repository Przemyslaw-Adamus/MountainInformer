package com.mountaininformer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class Fragment_2 extends Fragment {
//#####################################################################################################################################
    private RecyclerView recyclerView;
    private ItemPersonAdapter itemAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;
    private List<Person> personList;
    private PersonsDBAdapter personsDBAdapter;

    //#####################################################################################################################################
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//-------------------------------------------------------------------------------------------------------------------------------------
        view = inflater.inflate(R.layout.fragment_2_layout,container,false);
//-------------------------------------------------------------------------------------------------------------------------------------
        initPersonList();
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(recyclerView.getContext());
        itemAdapter = new ItemPersonAdapter(personList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(itemAdapter);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorAccent);
        reloadRecyclerView();
//-------------------------------------------------------------------------------------------------------------------------------------
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadRecyclerView();
            }
        });

        itemAdapter.setOnItemClickListener(new ItemPersonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

        itemAdapter.setOnItemLongClickListener(new ItemPersonAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(int position) {

            }
        });
//-------------------------------------------------------------------------------------------------------------------------------------
        return view;
    }
//#####################################################################################################################################
    private void reloadRecyclerView() {
        itemAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void initPersonList() {
        personList = new ArrayList<>();
        personsDBAdapter = new PersonsDBAdapter(getContext());

        personsDBAdapter.open();
        Cursor cursor = personsDBAdapter.getEntry();
        Person personTmp;
        if(cursor == null){
            return;
        }
        while(cursor.moveToNext()) {
            personTmp = new Person(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getInt(5), cursor.getInt(6));
            personList.add(personTmp);
        }
        personsDBAdapter.close();
    }
//#####################################################################################################################################

}