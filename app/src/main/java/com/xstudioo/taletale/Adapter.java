package com.xstudioo.taletale;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater inflater;
    private String[] sTitles;
    private String[] sContent;

    Adapter(Context context, String[] titles,String[] contents){
        this.inflater = LayoutInflater.from(context);
        this.sTitles = titles;
        this.sContent = contents;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_view,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String title = sTitles[i];
        String content = sContent[i];
        viewHolder.storyTitle.setText(title);
        viewHolder.storyContent.setText(content);
        viewHolder.number.setText(String.valueOf(title.charAt(0)));
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        // generate random color

        Random r = new Random();
        int red = r.nextInt(255 - 0 + i);
        int green = r.nextInt(255 - i + 1);
        int blue = r.nextInt(255 - 0 + (i+1));
        shape.setColor(Color.rgb(red,green,blue));
        viewHolder.circle.setBackground(shape);
    }

    @Override
    public int getItemCount() {
        return sTitles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView storyTitle,storyContent,number;
        CardView circle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // implement onClick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),Details.class);
                    // send story title and contents through recyclerview to detail activity
                    i.putExtra("titleOfStory",sTitles[getAdapterPosition()]);
                    i.putExtra("contentOfStory",sContent[getAdapterPosition()]);
                    v.getContext().startActivity(i);
                }
            });
            storyTitle = itemView.findViewById(R.id.storyTitle);
            storyContent = itemView.findViewById(R.id.storyContent);
            number = itemView.findViewById(R.id.number);
            circle = itemView.findViewById(R.id.circle);


        }
    }

}

