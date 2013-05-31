package org.codeweaver.ponyexpress;

/**
 * Created by loki on 20/05/13.
 */
public enum Source {

	EQUESTRIA_DAILY("Equestria Daily", "http://www.equestriadaily.com", R.drawable.ic_eqd, SourceType.BLOGGER);

	public static final int	STRING_RES	= R.array.news_sources;

	private String			displayName;
	private String			rootUrl;
	private int				resId;
	private long			id;
	private SourceType		type;

	private Source(String displayName, String rootUrl, int resId, SourceType type) {
		this.displayName = displayName;
        this.rootUrl = rootUrl;
		this.resId = resId;
		this.id = STRING_RES + resId;
		this.type = type;
	}

	public String getDisplayName() {
		return displayName;
	}

    public String getRootUrl() {
        return rootUrl;
    }

	public int getResId() {
		return resId;
	}

	public long getId() {
		return id;
	}

	public SourceType getType() {
		return type;
	}
}
