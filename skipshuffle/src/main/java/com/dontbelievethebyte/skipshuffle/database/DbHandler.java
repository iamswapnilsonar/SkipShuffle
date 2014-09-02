package com.dontbelievethebyte.skipshuffle.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dontbelievethebyte.skipshuffle.playlist.PlaylistInterface;
import com.dontbelievethebyte.skipshuffle.playlist.Track;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    private static final String TAG = "SkipShuffleDB";

    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "skipshuffle.db";
    private static final String TABLE_TRACKS = "tracks";
    private static final String TABLE_PLAYLIST = "playlist";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TRACKS = "tracks";
    public static final String COLUMN_PATH = "path";
    public static final String COLUMN_METADATA_ALBUM = "album";
    public static final String COLUMN_METADATA_ARTIST = "artist";
    public static final String COLUMN_METADATA_TITLE = "title";
    public static final String COLUMN_METADATA_GENRE = "genre";


    public DbHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String CREATE_TRACKS_TABLE = "CREATE TABLE " +
                TABLE_TRACKS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PATH + " TEXT," +
                COLUMN_METADATA_TITLE + " TEXT," +
                COLUMN_METADATA_ARTIST + " TEXT," +
                COLUMN_METADATA_ALBUM + " TEXT," +
                COLUMN_METADATA_GENRE + " TEXT" +
                ")";
        sqLiteDatabase.execSQL(CREATE_TRACKS_TABLE);

        String CREATE_PLAYLIST_TABLE = "CREATE TABLE " +
                TABLE_PLAYLIST + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TRACKS + " TEXT" +
                ")";
        sqLiteDatabase.execSQL(CREATE_PLAYLIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYLIST);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TRACKS);
        onCreate(sqLiteDatabase);
    }

    //int return is newly added id of the row or -1 on error
    public void addTrack(Track track)
    {

        long trackId;

        ContentValues contentValues = new ContentValues();
        contentValues.put(
                COLUMN_PATH,
                track.getPath()
        );
        contentValues.put(
                COLUMN_METADATA_TITLE,
                track.getTitle()
        );
        contentValues.put(
                COLUMN_METADATA_ARTIST,
                track.getArtist()
        );
        contentValues.put(
                COLUMN_METADATA_ALBUM,
                track.getAlbum()
        );
        contentValues.put(
                COLUMN_METADATA_GENRE,
                track.getGenre()
        );

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                TABLE_TRACKS, //From the tracks table
                new String[]{COLUMN_ID}, //Get the id back as a result
                COLUMN_PATH + "=?", //Where path
                new String[]{track.getPath()}, //path from track
                null,
                null,
                null
        );

        if(cursor.moveToFirst()){
            trackId = cursor.getLong(0);
        } else {
            trackId = sqLiteDatabase.insert(
                    TABLE_TRACKS, //Into the tracks table
                    null,
                    contentValues //Values extracted from the Track instance.
            );
        }
        track.setId(trackId); //This is necessary to set manually for the first time into the live
                              // instance Track object if it's a newly inserted track in order to
                              // build a playlist.
        sqLiteDatabase.close();
    }

    public long addPlaylist(PlaylistInterface playlistInterface)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(
                COLUMN_PATH,
                playlistInterface.toString()
        );
        contentValues.put(
                COLUMN_TRACKS,
                new JSONArray(playlistInterface.getList()).toString() //Transform the Collection to a JSon string before saving as TEXT
        );

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long playlistId = sqLiteDatabase.insert(
                TABLE_TRACKS,
                null,
                contentValues
        );
        sqLiteDatabase.close();
        return playlistId;
    };

    public Track getTrack(Long id)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                TABLE_TRACKS,
                new String[]{COLUMN_ID, COLUMN_PATH},
                COLUMN_ID + "=?",
                new String[]{id.toString()},
                null,
                null,
                null
        );
        Track track = new Track();
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            track.setId(cursor.getInt(0));
            track.setPath(cursor.getString(1));
        }
        sqLiteDatabase.close();
        return track;
    }

    public List<Track> getAllPlaylistTracks(List<Long> tracksIds)
    {
        List<Track> tracks = new ArrayList<Track>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        for(Long trackId : tracksIds){
            Track track = new Track();
            Cursor cursor = sqLiteDatabase.query(
                    TABLE_TRACKS,
                    new String[]{COLUMN_ID, COLUMN_PATH},
                    COLUMN_ID+"=?",
                    new String[]{trackId.toString()},
                    null,
                    null,
                    null
            );
            if(cursor.moveToFirst()){
                cursor.moveToFirst();
                track.setId(cursor.getInt(0));
                track.setPath(cursor.getString(1));
                tracks.add(track);
            }
        }
        sqLiteDatabase.close();
        return tracks;
    }

    public List<Long> loadPlaylist(Long playlistId) throws JSONException
    {
        List<Long> playlistTracks = new ArrayList<Long>();
        if(playlistId != null){
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.query(
                    TABLE_PLAYLIST,
                    new String[]{COLUMN_TRACKS},
                    COLUMN_ID + "=?",
                    new String[]{playlistId.toString()},
                    null,
                    null,
                    null
            );
            if(cursor.moveToFirst()){
                JSONArray jsonArray = new JSONArray(cursor.getString(0));
                for(int i=0; i<jsonArray.length(); i++){
                    playlistTracks.add(jsonArray.getLong(i));
                }
            }
            Log.d("PLAYLS", "WUILL NOT NOT NOT RETURN NULL : " + playlistTracks.size());

            return playlistTracks;
        } else {
            Log.d("PLAYLS", "WUILL RETURN NULL");
            return null;
        }
    }

    public void deletePlaylist(long id)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(
                TABLE_PLAYLIST,
                null,
                null
        );
        sqLiteDatabase.close();
    }

    public void savePlaylist(Long playlistId, List<Long> trackIndexes)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        //@TODO not error safe.
        JSONArray jsonArray = new JSONArray(trackIndexes);

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TRACKS, jsonArray.toString());

        if(playlistId != null){
            contentValues.put(COLUMN_ID, playlistId);
        }
        int insert = (int) sqLiteDatabase.insertWithOnConflict(
                TABLE_PLAYLIST,
                null,
                contentValues,
                SQLiteDatabase.CONFLICT_IGNORE
        );
        if(insert == -1){
            sqLiteDatabase.update(
                    TABLE_PLAYLIST,
                    contentValues,
                    COLUMN_ID+"=?",
                    new String[]{playlistId.toString()}
            );
        }
        sqLiteDatabase.close();
    }
}