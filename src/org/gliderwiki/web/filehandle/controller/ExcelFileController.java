/**
 * @FileName  : FileController.java
 * @Project   : NightHawk
 * @Date      : 2012. 2. 9. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 파일 업로드용 임시 테스트 파일 이므로 추후 삭제하도록 한다. 
 */
package org.gliderwiki.web.filehandle.controller;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gliderwiki.admin.service.AdminGroupService;
import org.gliderwiki.admin.service.AdminUserService;
import org.gliderwiki.framework.exception.FilePermitMsgException;
import org.gliderwiki.framework.util.FileUploader;
import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.vo.TempUploadVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;





@Controller
public class ExcelFileController extends MultiActionController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("#{config['file.maxSize']}")
	String uploadMaxSize;
	
	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private AdminGroupService adminGroupService;
	/**
	 * 파일임시저장 테스트 
	 * @param fileVo
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping("/excelUpload")
	public ModelAndView excelUpload(@ModelAttribute("frmFile") TempUploadVo fileVo, 
			HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView)  throws Throwable {
		
		logger.debug("##root path : " + request.getServletContext().getRealPath("/"));
		logger.debug("##request.getSession() : " + request.getSession().getServletContext().getRealPath("/resource/temp/"));
		String svcPath = request.getSession().getServletContext().getRealPath("/resource/temp/");
		String dataType = request.getParameter("dataType");
		logger.debug("## dataType : " + dataType);
		
		
		
		//업로드 날짜 및 파일명 구성
		String today = org.gliderwiki.framework.util.DateUtil.getToday();
		
		//TODOLIST : 세션에서 가져온다. 일단 테스트용으로 사용자 순번 1을 통해 등록한다. 
		String weUserIdx = "1";
		String weUserId = "cafeciel";
		
		double maxSize = Double.parseDouble(uploadMaxSize);
				
		TempUploadVo tempFile = null;
		Map<String, String> param = new HashMap<String, String>();
		
		// 파일 객체, 사용자 아이디, 오늘날짜, 파일 업로드 사이즈
		try {
			tempFile = FileUploader.uploadTempFile(fileVo.getFile(), svcPath, weUserIdx, today, maxSize); 			
		} catch (FilePermitMsgException e) {
			logger.debug("###익셉션 메시지 : " +e.getCustomMsg());
			logger.debug("###익셉션 메시지 : " +e.toString());
			param.put("result", "-1");
	    	param.put("msg", "파일 업로드에 실패 하였습니다.");    	
	    	
	    	return new ModelAndView("json_").addObject("param", param);
		} catch (Exception ee) {
			logger.debug("**************************예상치 않은 Exception 발생!!!!");
			ee.printStackTrace();
		}
		
		logger.debug("tempFile : " + tempFile.toString());
	
		if(tempFile.isUploaded()) {
			// DB에 인서트 
			WeFile weFile = new WeFile(); 
			
			weFile.setWe_file_real_name(tempFile.getFileRealName());
			weFile.setWe_file_save_name(tempFile.getFileSaveName());
			weFile.setWe_file_save_path(tempFile.getFilePath());
			weFile.setWe_file_size(tempFile.getFileSize()+"");
			weFile.setWe_file_type(tempFile.getFileType());
			weFile.setWe_thumb_yn(tempFile.getThumbYn());
			weFile.setWe_thumb_name(tempFile.getThumbName());
			weFile.setWe_thumb_path(tempFile.getThumbPath());
			weFile.setWe_user_idx(Integer.parseInt(weUserIdx));
			weFile.setWe_ins_date(org.gliderwiki.framework.util.DateUtil.getTodayTime());
			weFile.setWe_ins_user(weUserIdx);
				
			param.put("result", "1");
	    	param.put("msg", "성공");    	
	    	param.put("realFileName", weFile.getWe_file_real_name());    	
	    	param.put("saveFileName", weFile.getWe_file_save_name());
	    	param.put("filePath", weFile.getWe_file_save_path());
	    	param.put("fileSize", weFile.getWe_file_size()+"");
	    	param.put("tmpsrc", svcPath);
	    	param.put("fileIndex", weFile.getWe_file_idx()+"");		    	
	    	
			logger.debug("****** getWe_file_real_name : " +request.getServletContext().getRealPath("/"));
			logger.debug("****** svcPath : " +svcPath);
			logger.debug("****** getWe_file_real_name : " +weFile.getWe_file_save_name());
			logger.debug("****** getWe_file_save_path : " +weFile.getWe_file_save_path());
			
	    	Map <String, String> map = new HashMap <String, String>();
	    	map.put("dataType", dataType);
	    	map.put("rootPath", svcPath);
	    	map.put("savePath", weFile.getWe_file_save_path());
	    	map.put("fileName", weFile.getWe_file_save_name());
	    	
	    	//엑셀 확장자별 나누기
	    	int result = this.excelTypeDivide(map);
	    	
	    	logger.debug("######### excelUpload  : "  + result);
	    	
	    	if (result == 0) {
				param.put("result", "-1");
		    	param.put("msg", "데이터 일괄처리에 실패하였습니다.");    	
		    	param.put("realFileName", tempFile.getFileRealName());    
	    	}else if (result == 999999999) {
	    		param.put("result", "-1");
		    	param.put("msg", "파일안에 데이터가 존재하지 않습니다.");    	
		    	param.put("realFileName", tempFile.getFileRealName()); 
	    	}
	    	
		} else {
			param.put("result", "-1");
	    	param.put("msg", "파일 업로드에 실패 하였습니다.");    	
	    	param.put("realFileName", tempFile.getFileRealName());    	
		}
		
		logger.debug("param : "  + param.toString());
		
		return new ModelAndView("json_").addObject("param", param);
	}
	
	//엑셀 확장자별 나누기
    public int excelTypeDivide( Map <String, String> map ) throws Exception{
    	int result = 0;
    	try {
	    	String dataType = map.get("dataType");
	        String filePath =map.get("rootPath") + map.get("savePath") + map.get("fileName");

	        //확장자가 .xlsx 이면 readExcel2007 .xls 이면 readExcel
	        if( filePath.indexOf(".xlsx") > -1 ) 
	        	result = readExcel( dataType, filePath ,"xlsx" );
	        else if( filePath.indexOf(".xls") > -1 ) 
	        	result = readExcel( dataType, filePath ,"xls");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
 
        return result;
    }


    @SuppressWarnings("rawtypes")
	public int readExcel( String dataType, String filePath , String fileType) throws IOException {
        // check file
    	int result = 0;
        File file = new File( filePath );
        if( !file.exists() || !file.isFile() || !file.canRead() ) {
            throw new IOException( filePath );
        }
        
        Workbook  wb = null;
        Map <Integer, Map> maps = new HashMap<Integer, Map>();
        Map <Integer, String> map = null;new HashMap<Integer, String>();
        
        if (fileType.equals("xlsx"))
        	wb = new XSSFWorkbook( new FileInputStream(file) );
        else if (fileType.equals("xls"))
        	wb =  new HSSFWorkbook(new FileInputStream(file));
        
        try {

//            for( int i=0; i<wb.getNumberOfSheets(); i++ ) {
            	Sheet sheet = wb.getSheetAt(0);
               // for( Row row : wb.getSheetAt(i) ) {
            	logger.debug("@@@@ sheet.getPhysicalNumberOfRows() : " + sheet.getPhysicalNumberOfRows());
            	if (sheet.getPhysicalNumberOfRows() <= 1) {
            		return 999999999; // 엑셀에 데이터가 없는 경우
            	} 
            	for (int j=0; j<sheet.getPhysicalNumberOfRows();j++) {
               //     for( Cell cell : row ) {
            		Row row = sheet.getRow(j);
            		map = new HashMap<Integer, String>();
            		
            		logger.debug("@@@@ row.getPhysicalNumberOfCells() : " + row.getPhysicalNumberOfCells());
            		for(int l=0; l < row.getPhysicalNumberOfCells(); l++){
                    	Cell cell = row.getCell(l);
            			String data="";
                    	
            			//각 cell에 입력된 데이터 타입별 처리
                        switch( cell.getCellType() ) {
                            case XSSFCell.CELL_TYPE_STRING:
                            	data = cell.getRichStringCellValue().getString();

                                break;
                            case XSSFCell.CELL_TYPE_NUMERIC:
                                if( HSSFDateUtil.isCellDateFormatted(cell) ) {
                                   SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                                   data = formatter.format(cell.getDateCellValue());

                                }
                                else {
                                   data = cell.getNumericCellValue()+ "";

                                }
                                break;
                            case XSSFCell.CELL_TYPE_FORMULA:
                            	data =  cell.getCellFormula();  

                            	break;
                            case XSSFCell.CELL_TYPE_BOOLEAN:
                            	data =  cell.getBooleanCellValue() +"";  

                            	break;
                            case XSSFCell.CELL_TYPE_ERROR:
                            	data =  cell.getErrorCellValue()+ "";  

                            	break;
                            case XSSFCell.CELL_TYPE_BLANK: break;
                            default: break;
                        }
                        map.put(cell.getColumnIndex(), data);
                    }
                    maps.put(j, map);
                   
                }
//            }

            for(int g =0; g < maps.size(); g++) {
            	Map <Integer, String> tempMap = maps.get(g);
            	logger.debug(tempMap.get(1) + " ;;;;;;;; "+ tempMap.get(2) + " ;;;;;;;;; " + tempMap.get(3));
            }
            
            result =  dataInsertDivide(dataType, maps);	
        } catch( Exception ex ) {
            ex.printStackTrace();
        }finally {
        	file.delete();	
        }
        return result;
    }

    public int dataInsertDivide(String dataType, Map<Integer, Map> maps) throws Exception{
    	int result = 0;
		try {
	    	if (dataType.equals("member")) {
				result = adminUserService.insertUser(maps);
	    	}else if(dataType.equals("group")){
	    		result = adminGroupService.insertGroupInfo(maps);
	    	}else if(dataType.equals("groupmember")){
	    		result = adminGroupService.insertGroupUser(maps);
	    	}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug(":::::: dataInsertDivide ::::result ::::::" + result );
    	return result;
    }

}

