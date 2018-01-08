package com.innobles.smartplaykids.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innobles.smartplaykids.ui.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by musharib ali on 11/12/2017.
 */
public class BaseAdapter<S, T extends RecyclerView.ViewHolder> extends EmptyRecyclerView.Adapter<T> {

    private static final String TAG = "BaseRecyclerAdapter";
    private Context mContext;
    private List<S> mObjects=new ArrayList<>();
    private BindAdapterListener mListener;
    private T mHolder;
    private Class<T> mHolderClass;
    private int layoutId;


    public BaseAdapter(Context context, List<S> objects, BindAdapterListener listener, Class<T> holderClass, int layoutId) {
        mContext = context;
        mObjects = objects == null ? mObjects : objects;
        mHolderClass = holderClass;
        mListener = listener;
        this.layoutId = layoutId;
    }

    public void setData(List<S> objects){
        this.mObjects=objects;
        notifyDataSetChanged();
    }

    public void addAll(List<S> objects){
        this.mObjects.addAll(objects);
        notifyDataSetChanged();
    }

    public void remove(int index){
           this.mObjects.remove(index);
           this.notifyItemRemoved(index);
       //notifyItemRangeRemoved(index,mObjects.size());
       this.notifyItemChanged(index,mObjects.size());

    }

    public S getItem(int index)
    {
        return mObjects.get(index);
    }

    public List<S>  getList( )
    {
        return mObjects;
    }

    public void refresh( ){
        mObjects.clear();
        notifyDataSetChanged();
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            return mHolderClass.getConstructor(View.class).newInstance(LayoutInflater.from(mContext).inflate(layoutId, parent, false));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        mListener.onBind(holder, position);
    }

    @Override
    public int getItemCount() {
        return mObjects == null ? 0 : mObjects.size();
    }

    public interface BindAdapterListener<T> {
        void onBind(T holder, int position);
    }
}
