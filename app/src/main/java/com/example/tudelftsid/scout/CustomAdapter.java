package com.example.tudelftsid.scout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter<String>{
    public CustomAdapter(Context context, String[] foods) {
        super(context, R.layout.custom_row ,foods);
    }

    class MyViewHolder {

        ImageView photo;
        TextView itemText;

        MyViewHolder (View v){
            itemText= (TextView) v.findViewById(R.id.item_text);
            photo = (ImageView) v.findViewById(R.id.Placephoto);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // default -  return super.getView(position, convertView, parent);
        // add the layout
       View row= convertView;
       MyViewHolder holder=null;

       if(row==null){

           LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
           row = myCustomInflater.inflate(R.layout.custom_row, parent, false);
           holder= new MyViewHolder(row);
           row.setTag(holder);
       }

       else {
           holder= (MyViewHolder) row.getTag();
       }

        // get references.
        String singlePlace = getItem(position);

        // dynamically update the text from the array
        holder.itemText.setText(singlePlace);
        // dynamically update the picture
        switch (position) {
            case 0:
                holder.photo.setImageResource(R.drawable.delft);
                break;
            case 1:
                holder.photo.setImageResource(R.drawable.nature_1);
                break;
            case 2:
                holder.photo.setImageResource(R.drawable.streetart_1);
                break;
            case 3:
                holder.photo.setImageResource(R.drawable.delft);
                break;
            case 4:
                holder.photo.setImageResource(R.drawable.nature_1);
                break;
            case 5:
                holder.photo.setImageResource(R.drawable.streetart_1);
                break;
        }
        return row;

    }

}
