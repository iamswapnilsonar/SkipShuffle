/*
 * Copyright (c) 2014. Jean-Francois Berube, all rights reserved.
 */

package com.dontbelievethebyte.skipshuffle.ui.mapper;

import com.dontbelievethebyte.skipshuffle.R;
import com.dontbelievethebyte.skipshuffle.ui.mapper.types.UITypes;

public class ColorMapper {
    public static int getBackground(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.background_mono_light;
            case UITypes.MONO_DARK :
                return R.color.background_mono_dark;
            case UITypes.NEON :
                return R.color.background_neon;
            case UITypes.XMAS :
                return R.color.background_xmas;
            default:
                return R.color.background_neon;
        }
    }

    public static int getEmptyListText(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.list_empty_text_mono_light;
            case UITypes.MONO_DARK :
                return R.color.list_empty_text_mono_dark;
            case UITypes.NEON :
                return R.color.list_empty_text_neon;
            case UITypes.XMAS :
                return R.color.list_empty_text_xmas;
            default:
                return R.color.list_empty_text_neon;
        }
    }

    public static int getListDivider(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.list_divider_mono_light;
            case UITypes.MONO_DARK :
                return R.color.list_divider_mono_dark;
            case UITypes.NEON :
                return R.color.list_divider_neon;
            case UITypes.XMAS :
                return R.color.list_divider_xmas;
            default:
                return R.color.list_divider_neon;
        }
    }

    public static int getNavDrawerBackground(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.nav_drawer_background_mono_light;
            case UITypes.MONO_DARK :
                return R.color.nav_drawer_background_mono_dark;
            case UITypes.NEON :
                return R.color.nav_drawer_background_neon;
            case UITypes.XMAS:
                return R.color.nav_drawer_background_xmas;
            default:
                return R.color.nav_drawer_background_neon;
        }
    }

    public static int getNavHeaderText(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.nav_drawer_header_text_mono_light;
            case UITypes.MONO_DARK :
                return R.color.nav_drawer_header_text_mono_dark;
            case UITypes.NEON :
                return R.color.nav_drawer_header_text_neon;
            case UITypes.XMAS :
                return R.color.nav_drawer_header_text_xmas;
            default:
                return R.color.nav_drawer_header_text_neon;
        }
    }

    public static int getNavDrawerText(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.nav_drawer_text_mono_light;
            case UITypes.MONO_DARK :
                return R.color.nav_drawer_text_mono_dark;
            case UITypes.NEON :
                return R.color.nav_drawer_text_neon;
            case UITypes.XMAS :
                return R.color.nav_drawer_text_xmas;
            default:
                return R.color.nav_drawer_text_neon;
        }
    }

    public static int getSongLabel(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.song_label_mono_light;
            case UITypes.MONO_DARK :
                return R.color.song_label_mono_dark;
            case UITypes.NEON :
                return R.color.song_label_neon;
            case UITypes.XMAS :
                return R.color.song_label_xmas;
            default:
                return R.color.song_label_neon;
        }
    }

    public static int getPlaylistTitle(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.playlist_item_track_title_mono_light;
            case UITypes.MONO_DARK :
                return R.color.playlist_item_track_title_mono_dark;
            case UITypes.NEON :
                return R.color.playlist_item_track_title_neon;
            case UITypes.XMAS :
                return R.color.playlist_item_track_title_xmas;
            default:
                return R.color.playlist_item_track_title_neon;
        }
    }


    public static int getPlaylistArtist(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.playlist_item_track_artist_mono_light;
            case UITypes.MONO_DARK :
                return R.color.playlist_item_track_artist_mono_dark;
            case UITypes.NEON :
                return R.color.playlist_item_track_artist_neon;
            case UITypes.XMAS :
                return R.color.playlist_item_track_artist_xmas;
            default:
                return R.color.playlist_item_track_artist_neon;
        }
    }

    public static int getPlayButton(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.play_button_mono_light;
            case UITypes.MONO_DARK :
                return R.color.play_button_mono_dark;
            case UITypes.NEON :
                return R.color.play_button_neon;
            case UITypes.XMAS :
                return R.color.play_button_xmas;
            default:
                return R.color.play_button_neon;
        }
    }

    public static int getPauseButton(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.pause_button_mono_light;
            case UITypes.MONO_DARK :
                return R.color.pause_button_mono_dark;
            case UITypes.NEON :
                return R.color.pause_button_neon;
            case UITypes.XMAS:
                return R.color.pause_button_xmas;
            default:
                return R.color.pause_button_neon;
        }
    }

    public static int getSkipButton(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.skip_button_mono_light;
            case UITypes.MONO_DARK :
                return R.color.skip_button_mono_dark;
            case UITypes.NEON :
                return R.color.skip_button_neon;
            case UITypes.XMAS :
                return R.color.skip_button_xmas;
            default:
                return R.color.skip_button_neon;
        }
    }

    public static int getPrevButton(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.prev_button_mono_light;
            case UITypes.MONO_DARK :
                return R.color.prev_button_mono_dark;
            case UITypes.NEON :
                return R.color.prev_button_neon;
            case UITypes.XMAS :
                return R.color.prev_button_xmas;
            default:
                return R.color.prev_button_neon;
        }
    }

    public static int getShuffleButton(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.shuffle_button_mono_light;
            case UITypes.MONO_DARK :
                return R.color.shuffle_button_mono_dark;
            case UITypes.NEON :
                return R.color.shuffle_button_neon;
            case UITypes.XMAS :
                return R.color.shuffle_button_xmas;
            default:
                return R.color.shuffle_button_neon;
        }
    }

    public static int getPlaylistButton(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.playlist_button_mono_light;
            case UITypes.MONO_DARK :
                return R.color.playlist_button_mono_dark;
            case UITypes.NEON :
                return R.color.playlist_button_neon;
            case UITypes.XMAS:
                return R.color.playlist_button_xmas;
            default:
                return R.color.playlist_button_neon;
        }
    }

    public static int getSeekbarProgress(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.seekbar_progress_mono_light;
            case UITypes.MONO_DARK :
                return R.color.seekbar_progress_mono_dark;
            case UITypes.NEON :
                return R.color.seekbar_progress_neon;
            case UITypes.XMAS:
                return R.color.seekbar_progress_xmas;
            default:
                return R.color.seekbar_progress_neon;
        }
    }

    public static int getSeekbarBackground(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.seekbar_background_mono_light;
            case UITypes.MONO_DARK :
                return R.color.seekbar_background_mono_dark;
            case UITypes.NEON :
                return R.color.seekbar_background_neon;
            case UITypes.XMAS:
                return R.color.seekbar_background_xmas;
            default:
                return R.color.seekbar_background_neon;
        }
    }

    public static int getSeekbarThumb(Integer uiType)
    {
        switch (uiType) {
            case UITypes.MONO_LIGHT :
                return R.color.seekbar_thumb_mono_light;
            case UITypes.MONO_DARK :
                return R.color.seekbar_thumb_mono_dark;
            case UITypes.NEON :
                return R.color.seekbar_thumb_neon;
            case UITypes.XMAS:
                return R.color.seekbar_thumb_xmas;
            default:
                return R.color.seekbar_thumb_neon;
        }
    }

}
