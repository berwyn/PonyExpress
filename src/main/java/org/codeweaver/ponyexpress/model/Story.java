package org.codeweaver.ponyexpress.model;

import java.util.Date;

/**
 * Created by Berwyn Codeweaver on 13/06/13.
 */
public interface Story {

    public long getID();
    public String getContent();
    public String getTitle();
    public Date getPublished();

}
