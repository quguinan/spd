package com.cms.bean;

public class ColumnsEasyuiBean {
	private String title ;//string 列标题文本。 undefined 
	private String field ;//string 列字段名称。 undefined 
	private Integer width ;//number 列的宽度。如果没有定义，宽度将自动扩充以适应其内容。 undefined 
//	private Integer rowspan ;//number 指明将占用多少行单元格（合并行）。 undefined 
//	private Integer colspan ;//number 指明将占用多少列单元格（合并列）。 undefined 
	private String align ;//string 指明如何对齐列数据。可以使用的值有：'left','right','center'。 undefined 
	private String halign ;//string 指明如何对齐列标题。可以使用的值有：'left','right','center'。
	private Boolean sortable ;//Boolean 如果为true，则允许列使用排序。 undefined 
	private String order ;//string 默认排序数序，只能是'asc'或'desc'
//	private Boolean resizable ;//Boolean 如果为true，允许列改变大小。 undefined 
//	private Boolean fixed ;//Boolean 如果为true，在"fitColumns"设置为true的时候阻止其自适应宽度。 undefined 
	private Boolean hidden ;//Boolean 如果为true，则隐藏列。 undefined 
//	private Boolean checkbox ;//Boolean 如果为true，则显示复选框。该复选框列固定宽度。 undefined 
	//formatter ;//function 单元格formatter(格式化器)函数，
	//styler ;//function 单元格styler(样式)函数，返回如'background:red'这样的自定义单元格样式字符串。该函数带3个参数：
	//sorter ;//function 用来做本地排序的自定义字段排序函数，带2个参数：
	//editor ;//string,object 指明编辑类型。当字符串指明编辑类型的时候，对象包含2个属性：
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title.toLowerCase();
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field.toLowerCase();
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public String getHalign() {
		return halign;
	}
	public void setHalign(String halign) {
		this.halign = halign;
	}
	public Boolean isHidden() {
		return hidden;
	}
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Boolean isSortable() {
		return sortable;
	}
	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
}
