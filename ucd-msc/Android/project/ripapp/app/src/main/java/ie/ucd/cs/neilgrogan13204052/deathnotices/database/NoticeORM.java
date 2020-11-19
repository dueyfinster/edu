package ie.ucd.cs.neilgrogan13204052.deathnotices.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ie.ucd.cs.neilgrogan13204052.deathnotices.model.Notice;
import ie.ucd.cs.neilgrogan13204052.deathnotices.search.SearchType;

public class NoticeORM {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);

    private static final String TAG = "NoticeORM";

    private static final String TABLE_NAME = "notices";

    private static final String COMMA_SEP = ", ";

    private static final String COLUMN_ID_TYPE = "INTEGER PRIMARY KEY";
    private static final String COLUMN_ID = "id";

    private static final String COLUMN_FIRST_NAME_TYPE = "TEXT";
    private static final String COLUMN_FIRST_NAME = "first_name";

    private static final String COLUMN_LAST_NAME_TYPE = "TEXT";
    private static final String COLUMN_LAST_NAME = "second_name";

    private static final String COLUMN_ADDRESS_TYPE = "TEXT";
    private static final String COLUMN_ADDRESS = "address";

    private static final String COLUMN_LAT_TYPE = "TEXT";
    private static final String COLUMN_LAT = "lat";

    private static final String COLUMN_LON_TYPE = "TEXT";
    private static final String COLUMN_LON = "lon";

    private static final String COLUMN_TEXT_TYPE = "TEXT";
    private static final String COLUMN_TEXT = "text";

    private static final String COLUMN_DATE_DEATH_TYPE = "TEXT";
    private static final String COLUMN_DATE_DEATH = "date_death";

    private static final String COLUMN_DATE_NOTICE_TYPE = "TEXT";
    private static final String COLUMN_DATE_NOTICE = "date_notice";


    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " " + COLUMN_ID_TYPE + COMMA_SEP +
                COLUMN_FIRST_NAME  + " " + COLUMN_FIRST_NAME_TYPE + COMMA_SEP +
                COLUMN_LAST_NAME  + " " + COLUMN_LAST_NAME_TYPE + COMMA_SEP +
                COLUMN_ADDRESS  + " " + COLUMN_ADDRESS_TYPE + COMMA_SEP +
                COLUMN_LAT  + " " + COLUMN_LAT_TYPE + COMMA_SEP +
                COLUMN_LON  + " " + COLUMN_LON_TYPE + COMMA_SEP +
                COLUMN_TEXT  + " " + COLUMN_TEXT_TYPE + COMMA_SEP +
                COLUMN_DATE_DEATH + " " + COLUMN_DATE_DEATH_TYPE + COMMA_SEP +
                COLUMN_DATE_NOTICE + " " + COLUMN_DATE_NOTICE_TYPE +
            ")";

    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public static List<Notice> getDeathNotices(final Context context) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + NoticeORM.TABLE_NAME, null);

        Log.i(TAG, "Loaded " + cursor.getCount() + " Notices...");
        List<Notice> notices = new ArrayList<>();

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Notice notice = cursorToNotice(cursor);
                notices.add(notice);
                cursor.moveToNext();
            }
            Log.i(TAG, "Notices loaded successfully.");
        }

        database.close();

        return notices;
    }

    public static Notice findNoticeById(Context context, long postId) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase database = databaseWrapper.getReadableDatabase();

        Notice notice = null;
        if(database != null) {
            Log.i(TAG, "Loading Post["+postId+"]...");
            Cursor cursor = database.rawQuery("SELECT * FROM " + NoticeORM.TABLE_NAME + " WHERE " + NoticeORM.COLUMN_ID + " = " + postId, null);

            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                notice = cursorToNotice(cursor);
                Log.i(TAG, "Notice loaded successfully!");
            }

            //database.close();
        }

        return notice;
    }

    public static List<Notice> findNoticeByName(Context context, String query, SearchType searchType) {

        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteDatabase db = databaseWrapper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,null,getColumnType(searchType) + " LIKE '%" + query + "%'",null,null,null,null,null);

        List<Notice> notices = new ArrayList<>();

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Notice notice = cursorToNotice(cursor);
                notices.add(notice);
                cursor.moveToNext();
            }
            Log.i(TAG, "Notices loaded successfully.");
        }

        return notices;
    }



    /**
     * Populates a Notice object with data from a Cursor
     */
    private static Notice cursorToNotice(Cursor cursor) {
        Notice notice = new Notice();
        notice.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        notice.setFirst_name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)));
        notice.setLast_name(cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)));
        notice.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
        notice.setLat(cursor.getString(cursor.getColumnIndex(COLUMN_LAT)));
        notice.setLon(cursor.getString(cursor.getColumnIndex(COLUMN_LON)));
        notice.setText(cursor.getString(cursor.getColumnIndex(COLUMN_TEXT)));


        try {
            notice.setDate_of_death(dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_DEATH))));
            notice.setDate_published(dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_DATE_NOTICE))));
        } catch (ParseException ex) {
            Log.e(TAG, "Failed to parse dates for Notice " + notice.getId());
            notice.setDate_of_death(null);
            notice.setDate_published(null);
        }

        return notice;
    }


    public static void insertNotices(Context context, List<Notice> notices) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);


        SQLiteDatabase database = databaseWrapper.getWritableDatabase();
        for(Notice notice : notices){
            ContentValues values = noticeToContentValues(notice);
            long noticeId = database.insert(NoticeORM.TABLE_NAME, "null", values);
            Log.i(TAG, "Inserted new Death Notice with id: " + noticeId);
        }
        databaseWrapper.close();
    }



    /**
     * Packs a Notice object into a ContentValues map for use with SQL inserts.
     */
    private static ContentValues noticeToContentValues(Notice model) {
        ContentValues values = new ContentValues();
        values.put(NoticeORM.COLUMN_ID, model.getId());
        values.put(NoticeORM.COLUMN_FIRST_NAME, model.getFirst_name());
        values.put(NoticeORM.COLUMN_LAST_NAME, model.getLast_name());
        values.put(NoticeORM.COLUMN_ADDRESS, model.getAddress());
        values.put(NoticeORM.COLUMN_LAT, model.getLat());
        values.put(NoticeORM.COLUMN_LON, model.getLon());
        values.put(NoticeORM.COLUMN_TEXT, model.getText());
        values.put(NoticeORM.COLUMN_DATE_DEATH, dateFormat.format(model.getDate_of_death()));
        values.put(NoticeORM.COLUMN_DATE_NOTICE, dateFormat.format(model.getDate_published()));

        return values;
    }


    private static Cursor query(Context context, String selection, String[] selectionArgs, String[] columns) {
        DatabaseWrapper databaseWrapper = new DatabaseWrapper(context);
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(TABLE_NAME);

        Cursor cursor = builder.query(databaseWrapper.getReadableDatabase(),
                columns, selection, selectionArgs, null, null, null);

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }

    private static String getColumnType(SearchType searchType){
        switch(searchType){
            case NAME:
                return COLUMN_FIRST_NAME + " || " + COLUMN_LAST_NAME;
            case ADDRESS:
                return COLUMN_ADDRESS;
            default:
                throw new IllegalArgumentException("Wrong Value used");
        }

    }
}