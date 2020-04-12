package com.cms.controller.biz.goods.supply.supply;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cms.bean.GridDataModel;
import com.cms.form.SuSupplyLicenseForm;
import com.cms.model.biz.supply.SuSupply;
import com.cms.model.biz.supply.SuSupplyLicense;
import com.cms.service.biz.supply.ISuSupplyLicenseService;
import com.cms.service.biz.supply.ISuSupplyService;
import com.cms.util.CapturePdfFirstPageUtil;
import com.cms.util.DeleteFolderFileUtil;
import com.cms.util.ImageZipUtil;
import com.cms.util.RandomUtil;

import my.dao.pool.DBManager;
import my.web.BaseController;
import net.sf.json.JSONObject;
/**
 * 
 * @author qgn
 *
 */
@Controller
@RequestMapping("/cms/biz/supply")
public class SuSupplyLicenseController extends BaseController {
	@Autowired
	private ISuSupplyLicenseService supplyLicenseService ;
	
	/**
	 * 将表单数据对象存入数据模型  
	 * @return 
	 */
	@ModelAttribute("suSupplyLicenseForm")
	public SuSupplyLicenseForm getSuSupplyLicenseForm(){
		return new SuSupplyLicenseForm();
	}

	
	@RequestMapping("supplyLicense")
	public String list(Model model, HttpSession session) {
		String menuid = param("menuid", "");
		model.addAttribute("menuid", menuid);
		model.addAttribute("user", session.getAttribute("user"));
		return "cms/biz/goods/supplier/supply/supplyLicense";
	}
	
	
	@ResponseBody
	@RequestMapping("supplyLicense/gridData")
	public GridDataModel<SuSupplyLicense> gridData() {
		String licensetypeid=param("licensetypeid","");
		GridDataModel<SuSupplyLicense> gridDataModel=supplyLicenseService.getGridData(licensetypeid);
		return gridDataModel;
	}
	
	@ResponseBody
	@RequestMapping("supplyLicense/gridDataBySupplyidLicensetypeid")
	public JSONObject gridDataBySupplyidLicensetypeid(Model model) {
		//HashMap<String, Object> result = new HashMap<String, Object>();
		String licensetypeid=param("licensetypeid","");
		String supplyid=param("supplyid","");
		SuSupplyLicense suSupplyLicense=supplyLicenseService.getBySupplyidLicensetypeid(supplyid, licensetypeid);
		if(suSupplyLicense==null){//如果空,则根据orgid, supplyid, licensetypeid创建一个新的证照
			suSupplyLicense=supplyLicenseService.newSuSupplyLicense( supplyid, licensetypeid);
		}
		return JSONObject.fromObject(suSupplyLicense);
		
	}
	
	@ResponseBody
	@RequestMapping("supplyLicense/save")
	public JSONObject save(
			@ModelAttribute("suSupplyLicenseForm") SuSupplyLicenseForm suSupplyLicenseForm,
			@RequestParam(value = "file_upload", required = false) MultipartFile file_upload,
			HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		/*
		 * 先处理图片,在处理数据
		 */
		long fileUploadSize=file_upload.getSize(); //用来判断是否上传了图片
		String path ="";//文件保存真实路径
        String fileOriginalName="";  //图片原始名称
        String prefix="";//图片后缀名
        String fileName=""; //生成实际文件名
        String fileNameFull=""; //生成实际文件名(后缀)
        String fileNameFullView=""; //生成预览文件名(后缀)
        String fileUrl="";//文件访问的虚拟路径
        String fileViewUrl="";//文件预览的虚拟路径
		try {
			/*Format format = new SimpleDateFormat("yyyyMMdd");
	        String today=format.format(new Date());*/
			/************************************
			 * 1处理图像文件.
			 ************************************/
			if (fileUploadSize>0) {
				path = "d:\\uploadfiles\\spd\\"+suSupplyLicenseForm.getLicenseid()+"\\";//文件保存真实路径 //windows
				//path = "/usr/local/tomcat/uploadfiles/house/"+housePicForm.getHid();//文件保存真实路径 //unix
		        fileOriginalName = file_upload.getOriginalFilename();  //图片原始名称
		        System.out.println(file_upload.getName());
		        System.out.println(file_upload.getContentType());
		        prefix=fileOriginalName.substring(fileOriginalName.lastIndexOf(".")+1);//图片后缀名
		        fileName = System.currentTimeMillis()+RandomUtil.generateString(6); //生成实际文件名(随机)
		        fileNameFull = fileName+"."+prefix; //生成实际文件名(后缀)
		        
		        fileUrl="/uploadfiles/"+suSupplyLicenseForm.getLicenseid()+"/"+fileNameFull;//文件访问的虚拟路径
		        File targetFile = new File(path, fileNameFull); 
		        if(!targetFile.exists()){  
		        	//文件不存在,则创建文件 
		            targetFile.mkdirs(); 
		        }  
		        //保存文件 
		        file_upload.transferTo(targetFile);  
		        //压缩文件 按照宽度等比例压缩
//		        ImageZipUtil.zipImageFile(targetFile,targetFile,800,0,0.7f); 
		        //上传原图完后,处理预览路径,如果是pdf,则生成一个同名jpg的预览图片,如归是jpg,则使用原图预览
		        if("pdf".equals(prefix)){//如果后缀名是pdf,则生成一个预览图后缀是jpg,但是链接后缀依然是pdf
		        	fileNameFullView=fileName+".jpg";
		        	CapturePdfFirstPageUtil.generateBookIamge( path+fileNameFull, path+fileNameFullView ) ;//生成pdf第一页的jpg
		        }else{
		        	fileNameFullView=fileNameFull;
		        }
		        fileViewUrl="/uploadfiles/"+suSupplyLicenseForm.getLicenseid()+"/"+fileNameFullView;//文件预览的虚拟路径
			}
			/************************************
			 * 2处理表数据
			 ************************************/
			SuSupplyLicense suSupplyLicense=supplyLicenseService.getById(suSupplyLicenseForm.getLicenseid());
			BeanUtils.copyProperties(suSupplyLicenseForm, suSupplyLicense);
			suSupplyLicense.setPicurl(fileUrl);
			suSupplyLicense.setViewurl(fileViewUrl);
			suSupplyLicense.update();
			DBManager.commitAll();
			/************************************
			 * 3提交
			 ************************************/
			result.put("success", true);
			result.put("msg","保存成功!");
			result.put("fileUrl",fileUrl);
		} catch (Exception e) {
			DBManager.rollbackAll();
			e.printStackTrace();
			result.put("success", false);
			result.put("msg","保存失败!");
		}
		
		return JSONObject.fromObject(result);
	}
	
}
