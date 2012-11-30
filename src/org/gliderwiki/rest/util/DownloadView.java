package org.gliderwiki.rest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        
        File file = (File)model.get("downloadFile");
        response.setContentType(this.getContentType());
        response.setContentLength((int)file.length());
        response.setHeader("Content-Disposition", "attachment; fileName=\""+file.getName()+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        ServletOutputStream out = response.getOutputStream();
        
        FileInputStream fls = null;
        
        try { 
            fls = new FileInputStream(file);
            FileCopyUtils.copy(fls, out);
            
        } finally { 
            if(fls != null) { 
                try { 
                    fls.close();
                } catch(IOException ex) { 
                    
                }
            }
        }
        out.flush();
    } 
}

