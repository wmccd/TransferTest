package com.wmcc.farmyardrewards.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.wmcc.farmyardrewards.R;


class SimpleRowItemAdapter extends BaseAdapter {


    private final String[] itemArray;
    private final Context context;
    private final LayoutInflater layoutInflater;

    public SimpleRowItemAdapter(Context context, String[] itemArray){
        this.context = context;
        this.itemArray = itemArray;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return itemArray.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.simple_row_item, null);
        TextView item = (TextView)convertView.findViewById(R.id.simpleRowItem_text1);

        item.setText(itemArray[position]);

        return convertView;
    }
}
