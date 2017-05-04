package com.example.adam.cardviewandrecyclerview;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Adam on 9/12/2016.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {
    private Context mContext;
    private List<Album> albumList;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position, List<Object> positon) {
        super.onBindViewHolder(holder, position, positon);
        Album album=albumList.get(position);
        holder.tittle.setText(album.getName());
        holder.count.setText(album.getNumOfSongs()+" songs");

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopupMenu(holder.overflow);
            }
        });
    }
    private void ShowPopupMenu(View view){
        // inflate menu
        PopupMenu popupMenu=new PopupMenu(mContext,view);
        MenuInflater inflater=popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_album,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new MyMenuItemClikckListener() );
        popupMenu.show();
    }
    class  MyMenuItemClikckListener implements PopupMenu.OnMenuItemClickListener{
        public MyMenuItemClikckListener(){

        }
        @Override
        public boolean onMenuItemClick(MenuItem item) {
             switch (item.getItemId()){
                 case R.id.action_favourite:
                     Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                     return true;
                 case R.id.action_play_next:
                     Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                     return true;
                 default:
             }
            return false;
        }
    }

    public AlbumsAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public  class  MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tittle,count;
        public ImageView thumbnail,overflow;


        public MyViewHolder(View itemView) {
            super(itemView);
            tittle=(TextView)itemView.findViewById(R.id.tv_title);
            count=(TextView)itemView.findViewById(R.id.count);
            thumbnail=(ImageView)itemView.findViewById(R.id.thumbnail);
            overflow=(ImageView)itemView.findViewById(R.id.overflow);
        }
    }
}
