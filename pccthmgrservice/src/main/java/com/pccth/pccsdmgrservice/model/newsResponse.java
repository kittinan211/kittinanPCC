package com.pccth.pccsdmgrservice.model;

import java.util.Date;

public class newsResponse {
    private Long newsid;
    private String newsname;
    private String textnews;
    private String imgurl;
    private String Datenews;
    
    public newsResponse() {}
    
	public newsResponse(Long newsid, String newsname, String textnews, String imgurl, String datenews) {
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


	public String getDatenews() {
		return Datenews;
	}


	public void setDatenews(String datenews) {
		Datenews = datenews;
	}
    
    
    
}
