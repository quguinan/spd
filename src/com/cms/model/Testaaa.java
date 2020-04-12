/**
** create by code gen .
**/
package com.cms.model;

import java.util.Date;
import java.math.BigDecimal;

import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Id;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.mapping.MappingInfo;
import my.dao.mapping.MappingInfoHolder;
import my.base.BasePO;



@Table("TEST")
@View("TEST_V")
@PK({  })
public class Testaaa extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final Testaaa INSTANCE = new Testaaa();
	
	@Column(value = "a", type = ColumnType.STRING)
	private String a ;
	
	@Column(value = "b", type = ColumnType.STRING)
	private String b ;
	
	@Column(value = "c", type = ColumnType.STRING)
	private String c ;
	
	@Column(value = "d", type = ColumnType.STRING)
	private String d ;
	
	@Column(value = "e", type = ColumnType.STRING)
	private String e ;
	
	@Column(value = "f", type = ColumnType.STRING)
	private String f ;
	
	@Column(value = "g", type = ColumnType.STRING)
	private String g ;
	
	@Column(value = "h", type = ColumnType.STRING)
	private String h ;
	
	@Column(value = "i", type = ColumnType.STRING)
	private String i ;
	
	@Column(value = "j", type = ColumnType.STRING)
	private String j ;
	
	@Column(value = "k", type = ColumnType.STRING)
	private String k ;
	
	@Column(value = "l", type = ColumnType.STRING)
	private String l ;
	
	@Column(value = "m", type = ColumnType.NUMBER)
	private BigDecimal m ;
	
	@Column(value = "n", type = ColumnType.STRING)
	private String n ;
	


	public String getA (){
		return a;
	}
		 
	public void setA (String a){
		this.a=a;
	}

	public String getB (){
		return b;
	}
		 
	public void setB (String b){
		this.b=b;
	}

	public String getC (){
		return c;
	}
		 
	public void setC (String c){
		this.c=c;
	}

	public String getD (){
		return d;
	}
		 
	public void setD (String d){
		this.d=d;
	}

	public String getE (){
		return e;
	}
		 
	public void setE (String e){
		this.e=e;
	}

	public String getF (){
		return f;
	}
		 
	public void setF (String f){
		this.f=f;
	}

	public String getG (){
		return g;
	}
		 
	public void setG (String g){
		this.g=g;
	}

	public String getH (){
		return h;
	}
		 
	public void setH (String h){
		this.h=h;
	}

	public String getI (){
		return i;
	}
		 
	public void setI (String i){
		this.i=i;
	}

	public String getJ (){
		return j;
	}
		 
	public void setJ (String j){
		this.j=j;
	}

	public String getK (){
		return k;
	}
		 
	public void setK (String k){
		this.k=k;
	}

	public String getL (){
		return l;
	}
		 
	public void setL (String l){
		this.l=l;
	}

	public BigDecimal getM (){
		return m;
	}
		 
	public void setM (BigDecimal m){
		this.m=m;
	}

	public String getN (){
		return n;
	}
		 
	public void setN (String n){
		this.n=n;
	}

}
