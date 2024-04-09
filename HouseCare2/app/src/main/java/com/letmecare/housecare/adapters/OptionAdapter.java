package com.letmecare.housecare.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.letmecare.housecare.R;
import com.letmecare.housecare.activities.BookActivity;
import com.letmecare.housecare.models.Option;

import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHoder>{

    private Context context;
    private List<Option> listOption;

    public OptionAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Option> listOption) {
        this.listOption = listOption;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OptionViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option, parent, false);

        return new OptionViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHoder holder, int position) {

        SharedPreferences preferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        Option option = listOption.get(position);
        holder.srcImg.setImageResource(option.getSrc());
        holder.textView.setText(option.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0) {
                    editor.putString("Loai", "Dọn Dẹp Riêng Lẻ");
                    editor.apply();

                    Intent intent = new Intent(context, BookActivity.class);
                    context.startActivity(intent);
                }
                if(position == 1) {
                    Intent intent = new Intent(context, BookActivity.class);
                    context.startActivity(intent);
                }
                if(position == 2) {

                    editor.putString("Loai", "Dọn Dẹp Riêng Lẻ Cao Cấp");
                    editor.apply();


                    Intent intent = new Intent(context, BookActivity.class);
                    context.startActivity(intent);
                }
                if(position == 3) {

                    editor.putString("Loai", "Dọn Dẹp Định Kỳ Cao Cấp");
                    editor.apply();

                    Intent intent = new Intent(context, BookActivity.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listOption != null) {
            return listOption.size();
        }
        return 0;
    }

    public class OptionViewHoder extends RecyclerView.ViewHolder {

        private ImageView srcImg;
        private TextView textView;

        public OptionViewHoder(@NonNull View itemView) {
            super(itemView);

            srcImg = itemView.findViewById(R.id.option_img);
            textView = itemView.findViewById(R.id.tv_option);
        }
    }
}
