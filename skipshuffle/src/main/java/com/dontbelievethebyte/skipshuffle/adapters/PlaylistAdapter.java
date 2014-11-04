package com.dontbelievethebyte.skipshuffle.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dontbelievethebyte.skipshuffle.R;
import com.dontbelievethebyte.skipshuffle.exceptions.PlaylistEmptyException;
import com.dontbelievethebyte.skipshuffle.playlists.PlaylistInterface;
import com.dontbelievethebyte.skipshuffle.playlists.Track;
import com.dontbelievethebyte.skipshuffle.ui.structured.Drawables;

public class PlaylistAdapter extends BaseAdapter {

    private static class ViewHolder {
        public TextView title;
        public TextView artist;
        public ImageView image;
    }

    private PlaylistInterface playlist;
    private LayoutInflater layoutInflater;
    private Activity activity;
    private Drawables drawables;

    public PlaylistAdapter(Activity activity, Drawables drawables ,PlaylistInterface playlist)
    {
        this.activity = activity;
        this.drawables = drawables;
        this.playlist = playlist;
        layoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount()
    {
        return playlist.getSize();
    }

    @Override
    public Object getItem(int position)
    {
        return playlist.getAtPosition(position);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup)
    {
        ViewHolder viewHolder;
        Track track = playlist.getAtPosition(position);
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(
                    R.layout.playlist_item,
                    null)
            ;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title = setTitle(
                convertView,
                R.id.track_title,
                track
        );
        viewHolder.artist = setArtist(
                convertView,
                R.id.track_artist,
                track
        );
        viewHolder.image = setImage(
                convertView,
                R.id.track_image,
                track,
                position == playlist.getPosition()
        );
        return convertView;
    }

    private ImageView setImage(View view, int resourceId, Track track, boolean isPlayButton)
    {
        ImageView iv = (ImageView) view.findViewById(resourceId);
        LayoutParams params = iv.getLayoutParams();
        try {
            if (track.getId() == playlist.getCurrent().getId()) {
                params.width = params.height;
                iv.setImageDrawable(
                        isPlayButton ? drawables.getPlay()
                                     : drawables.getPause()
                );
            } else {
                iv.setImageDrawable(null);
                params.width = params.height / 2;
            }
        } catch (PlaylistEmptyException e){
            Log.d("TAG", e.getMessage());
        }
        iv.setLayoutParams(params);
        return iv;
    }

    private TextView setTitle(View view, int resourceId, Track track)
    {
        TextView tv = (TextView) view.findViewById(resourceId);
        String title = track.getTitle();
        if (title == null) {
            title = track.getPath();
            title = title.substring(title.lastIndexOf("/") + 1);
        }
        tv.setText(title);
        return tv;
    }

    private TextView setArtist(View view, int resourceId, Track track)
    {
        String artist = track.getArtist();
        TextView tv = (TextView) view.findViewById(resourceId);
        if (artist == null) {
            artist = activity.getString(R.string.meta_data_unknown_artist);
        }
        tv.setText(artist);
        return tv;
    }
}