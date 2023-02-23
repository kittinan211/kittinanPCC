package com.pccth.pccsdmgrservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class news implements Serializable {
	
    public news() {}
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(nullable = false, updatable = false)
	    private Long newsid;
	    private String newsname;
	    private String textnews;
	    private String imgurl;
	    private Date Datenews;
	    
		public news(Long newsid, String newsname, String textnews, String imgurl, Date datenews) {
			super();
			this.newsid = newsid;
			this.newsname = newsname;
			this.textnews = textnews;
			this.imgurl = imgurl;
			Datenews = datenews;
		}
		
		
		public Long getNewsid() {
			return newsid;
		}
		public void setNewsid(Long newsid) {
			this.newsid = newsid;
		}
		public String getNewsname() {
			return newsname;
		}
		public void setNewsname(String newsname) {
			this.newsname = newsname;
		}
		public String getTextnews() {
			return textnews;
		}
		public void setTextnews(String textnews) {
			this.textnews = textnews;
		}
		public String getImgurl() {
			return imgurl;
		}
		public void setImgurl(String imgurl) {
			this.imgurl = imgurl;
		}
		public Date getDatenews() {
			return Datenews;
		}
		public void setDatenews(Date datenews) {
			Datenews = datenews;
		}
	
	    
}
