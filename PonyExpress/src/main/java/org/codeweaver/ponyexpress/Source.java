package org.codeweaver.ponyexpress;

/**
 * Created by loki on 20/05/13.
 */
public enum Source {

    EQUESTRIA_DAILY(R.drawable.ic_eqd)
    ;

    public static final int STRING_RES = R.array.news_sources;

    private int resId;
    private long id;

    private Source(int resId) {
        this.resId = resId;
        this.id = STRING_RES + resId;
    }

    public int getResId() {
        return resId;
    }

    public long getId() {
        return id;
    }
}
