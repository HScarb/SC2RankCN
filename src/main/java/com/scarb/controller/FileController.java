package com.scarb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartResolutionDelegate;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.UUID;

/**
 * Created by Scarb on 11/15/2016.
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/toFile")
    public String toFileUpload(){
        return "fileUpload";
    }

    @RequestMapping("/toFile2")
    public String toFileUpload2(){
        return "fileUpload2";
    }

    /**
     * 方法一上传文件
     * @param file
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/onefile")
    public String oneFileUpload(
            @RequestParam("file") CommonsMultipartFile file,
            HttpServletRequest request, ModelMap model
            ){
        // 获得原始文件名
        String fileName = file.getOriginalFilename();
        System.out.println("原始文件名:" + fileName);

        // 新文件名
        String newFileName = UUID.randomUUID() + fileName;

        // 获得项目的路径
        ServletContext sc = request.getSession().getServletContext();
        // 上传位置
        String path = sc.getRealPath("/portrait") + "/";        // 设定文件保存的目录

        File f = new File(path);

        // 文件目录不存在，创建目录
        if(!f.exists())
            f.mkdirs();
        // 如果上传的文件不为空
        if(!file.isEmpty()){
            try {
                FileOutputStream fos = new FileOutputStream(path + newFileName);
                InputStream in = file.getInputStream();
                int b = 0;
                while ((b = in.read()) != -1){
                    fos.write(b);
                }
                fos.close();
                in.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("上传图片到:" + path + newFileName);
        // 保存文件地址，用于JSP页面回显
        model.addAttribute("fileUrl", path + newFileName);
        return "fileUpload";
    }

    @RequestMapping("onefile2")
    public String onFileUpload2(HttpServletRequest request,
                                HttpServletResponse response) throws Exception{
        CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getServletContext());
        if(cmr.isMultipart(request)){
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
            Iterator<String> files = mRequest.getFileNames();
            while (files.hasNext()){
                MultipartFile mFile = mRequest.getFile(files.next());
                if(mFile != null){
                    String fileName = UUID.randomUUID() + mFile.getOriginalFilename();
                    String path = "F:/upload/" + fileName;

                    File localFile = new File(path);
                    mFile.transferTo(localFile);
                    request.setAttribute("fileUrl", path);
                }
            }
        }
        return "fileUpload";
    }
}
