package com.hansheng.studynote.bluetooth.AndroidBluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.hansheng.studynote.R;

import java.util.List;

/**
 * Created by hansheng on 2016/7/21.
 */
public class DeviceListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<BluetoothDevice> mData;
    private OnPairButtonClickListener mListener;

    public DeviceListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<BluetoothDevice> data) {
        mData = data;
    }

    public void setListener(OnPairButtonClickListener listener) {
        mListener = listener;
    }


    @Override
    public int getCount() {
        return (mData==null)?0:mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.list_item_device,null);
            holder=new ViewHolder();
            holder.nameTv= (TextView) convertView.findViewById(R.id.tv_name);
            holder.addressTv 	= (TextView) convertView.findViewById(R.id.tv_address);
            holder.pairBtn		= (Button) convertView.findViewById(R.id.btn_pair);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BluetoothDevice device=mData.get(position);
        holder.nameTv.setText(device.getName());
        holder.addressTv.setText(device.getBondState()==BluetoothDevice.BOND_BONDED?"Unpair":"Pair");
        holder.pairBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onPairButtonClick(position);
                }
            }
        });

        return convertView;
    }
    static class ViewHolder {
        TextView nameTv;
        TextView addressTv;
        TextView pairBtn;
    }

    public interface OnPairButtonClickListener {
        public abstract void onPairButtonClick(int position);
    }
}
