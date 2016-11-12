/**
 * 
 */
package com.wiseboys.mmhackathon.dao;

import java.util.List;

import com.wiseboys.mmhackathon.beans.ManoramaEdition;
import com.wiseboys.mmhackathon.beans.ManoramaSection;
import com.wiseboys.mmhackathon.beans.ManoramaArticle;
import com.wiseboys.mmhackathon.beans.ManoramaArticleDetails;

/**
 * All DAO Operations 
 * @author rajkl
 *
 */
public interface ManoramaAPIsDAO {
	public void truncateAllManoramaTables();
	public void saveEdition(List<ManoramaEdition> editions);
	public void saveSections(List<ManoramaSection> section);
	public void saveArticles(List<ManoramaArticle> articles);
	public void saveArticleDetails(ManoramaArticleDetails articleDetails);
}
