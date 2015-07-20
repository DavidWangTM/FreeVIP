package tm.davidwang.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import tm.davidwang.model.GridInfo;
import tm.davidwang.vip.R;

/**
 * Created by mac on 15/7/9.
 */
public class GridAdapter extends BaseAdapter {

    private ArrayList<GridInfo> data;
    private LayoutInflater inflater;
    private Context context;

    public GridAdapter(ArrayList<GridInfo> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_view, null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.showimg);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        GridInfo info = data.get(position);
        viewHolder.image.setBackgroundResource(info.imagename);

        return convertView;
    }

    private class ViewHolder {
        public ImageView image;
    }


}
