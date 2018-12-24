package com.dreadnought.dbtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employeeList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mListener = onItemClickListener;
    }

    public EmployeeAdapter(List employeeList) {
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.employee_view,viewGroup,false);
        EmployeeViewHolder employeeViewHolder = new EmployeeViewHolder(view);
        return employeeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder, int i) {
        employeeViewHolder.id.setText(String.valueOf(employeeList.get(i).getId()));
        employeeViewHolder.name.setText(employeeList.get(i).getName());
        employeeViewHolder.surname.setText(employeeList.get(i).getSurname());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder{

        private TextView id, name, surname;
        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.dataID);
            name = itemView.findViewById(R.id.dataName);
            surname = itemView.findViewById(R.id.dataSurname);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
