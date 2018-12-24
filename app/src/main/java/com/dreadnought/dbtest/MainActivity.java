package com.dreadnought.dbtest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private MyDBSQLiteHelper myDBSQLiteHelper;//helper for create a database and table
    private EditText name, surname, id;
    private Button btnAdd, btnDelete;
    private RecyclerView recycler;
    private EmployeeAdapter adapter;
    private ArrayList<Employee> employees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDBSQLiteHelper = new MyDBSQLiteHelper(this);

        name = findViewById(R.id.editTextName);
        surname = findViewById(R.id.editTextSurname);
        id = findViewById(R.id.editTextID);

        btnAdd = findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(this);
        btnDelete = findViewById(R.id.buttonDelete);
        btnDelete.setOnClickListener(this);

        InitializationRecycler();
    }

    private void InitializationRecycler(){
        recycler = findViewById(R.id.recyclerView);
        adapter = new EmployeeAdapter(getEmployees());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(new EmployeeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                id.setText(String.valueOf(employees.get(position).getId()));
                name.setText(employees.get(position).getName());
                surname.setText(employees.get(position).getSurname());
            }
        });
    }

    private ArrayList<Employee> getEmployees(){
        Cursor cursor = Employee.getAllEmployee(myDBSQLiteHelper.getReadableDatabase());
        employees = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                employees.add(new Employee(cursor.getInt(0),
                                            cursor.getString(1),
                                            cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return employees;
    }

    private void clearField(){
        name.setText("");
        surname.setText("");
        id.setText("");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAdd:
                if (!name.getText().toString().isEmpty() && !surname.getText().toString().isEmpty()) {
                    if (id.getText().toString().isEmpty()) {
                        Employee.addEmployee(name.getText().toString().toLowerCase(), surname.getText().toString().toLowerCase(), myDBSQLiteHelper.getWritableDatabase());
                    }else{
                        Employee.updateEmployee(Integer.parseInt(id.getText().toString()),name.getText().toString().toLowerCase(), surname.getText().toString().toLowerCase(), myDBSQLiteHelper.getWritableDatabase());
                    }
                }
                break;
            case R.id.buttonDelete:
                if (!id.getText().toString().isEmpty())
                    Employee.deleteEmployee(Integer.parseInt(id.getText().toString()),myDBSQLiteHelper.getWritableDatabase());
                break;
        }
        InitializationRecycler();
        clearField();
    }
}
