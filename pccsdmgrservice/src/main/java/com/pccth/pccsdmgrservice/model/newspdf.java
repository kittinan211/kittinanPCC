package com.pccth.pccsdmgrservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class newspdf {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(nullable = false, updatable = false)
	 private Long idnewspdf;
	 private String namepdf;
	 private String news_newsid;
	 
	 
	public newspdf(Long idnewspdf, String namepdf, String news_newsid) {
		super();
		this.idnewspdf = idnewspdf;
		this.namepdf = namepdf;
		this.news_newsid = news_newsid;
	}


	public Long getIdnewspdf() {
		return idnewspdf;
	}


	public void setIdnewspdf(Long idnewspdf) {
		this.idnewspdf = idnewspdf;
	}


	public String getNamepdf() {
		return namepdf;
	}


	public void setNamepdf(String namepdf) {
		this.namepdf = namepdf;
	}


	public String getNews_newsid() {
		return news_newsid;
	}


	public void setNews_newsid(String news_newsid) {
		this.news_newsid = news_newsid;
	}
	 
	 
}
