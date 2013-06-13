package org.codeweaver.ponyexpress.model.blogger;

import java.util.Date;

/**
 * Created by Berwyn Codeweaver on 19/05/13.
 */
public class Blog {

	private long		id;
	private String		kind;
	private String		name;
	private String		description;
	private Date		published;
	private Date		updated;
	private String		url;
	private String		selfLink;
	private Blog.Post	posts;
	private Blog.Page	pages;
	private Blog.Locale	locale;
	private String		customMetaData;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSelfLink() {
		return selfLink;
	}

	public void setSelfLink(String selfLink) {
		this.selfLink = selfLink;
	}

	public Post getPosts() {
		return posts;
	}

	public void setPosts(Post posts) {
		this.posts = posts;
	}

	public Page getPages() {
		return pages;
	}

	public void setPages(Page pages) {
		this.pages = pages;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getCustomMetaData() {
		return customMetaData;
	}

	public void setCustomMetaData(String customMetaData) {
		this.customMetaData = customMetaData;
	}

	public class Post {

		private int		totalItems;
		private String	selfLink;

		public int getTotalItems() {
			return totalItems;
		}

		public void setTotalItems(int totalItems) {
			this.totalItems = totalItems;
		}

		public String getSelfLink() {
			return selfLink;
		}

		public void setSelfLink(String selfLink) {
			this.selfLink = selfLink;
		}
	}

	public class Page {

		private int		totalItems;
		private String	selfLink;

        public int getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(int totalItems) {
            this.totalItems = totalItems;
        }

        public String getSelfLink() {
            return selfLink;
        }

        public void setSelfLink(String selfLink) {
            this.selfLink = selfLink;
        }
    }

	public class Locale {

		private String	language;
		private String	country;
		private String	variant;

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getVariant() {
            return variant;
        }

        public void setVariant(String variant) {
            this.variant = variant;
        }
    }

}
