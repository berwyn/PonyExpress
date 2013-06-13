package org.codeweaver.ponyexpress.model.blogger;

import com.google.gson.JsonObject;

/**
 * Created by Berwyn Codeweaver on 19/05/13.
 */
public class Author {

	private final String	IMAGE_URL_METADATA	= "url";

	private String			id;
	private String			displayName;
	private String			url;
	private JsonObject		image;

	public String getAvatarUrl() {
		return image.get(IMAGE_URL_METADATA).getAsString();
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JsonObject getImage() {
        return image;
    }

    public void setImage(JsonObject image) {
        this.image = image;
    }
}
