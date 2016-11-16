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
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
        String newFileName = /*UUID.randomUUID() +*/ fileName;

        // 获得项目的路径
        ServletContext sc = request.getSession().getServletContext();
        // 上传位置
        String path = sc.getRealPath("portraits") + "/";        // 设定文件保存的目录

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
        model.addAttribute("fileName", newFileName);
        return "fileUpload";
    }

    /**
     * 使用SpringMVC提供的方法上传文件
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("onefile2")
    public String onFileUpload2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 使用当前的上下文初始化多部分解析器
        CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getServletContext());

        if(cmr.isMultipart(request)){
            // 将request变成多部分request
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
            // 从mRequest获取所有文件名
            Iterator<String> files = mRequest.getFileNames();
            // 一次遍历所有文件
            while (files.hasNext()){
                MultipartFile mFile = mRequest.getFile(files.next());
                if(mFile != null){
//                    String fileName = UUID.randomUUID() + mFile.getOriginalFilename();

                    // 获得项目的路径
                    ServletContext sc = request.getSession().getServletContext();
                    // 上传位置
                    String path = sc.getRealPath("portraits") + "/";
                    path = path + mFile.getOriginalFilename();

                    File localFile = new File(path);
                    // 上传
                    mFile.transferTo(localFile);
                    request.setAttribute("fileName", mFile.getOriginalFilename());
                }
            }
        }
        return "fileUpload";
    }

    @RequestMapping("/listFile")
    public String listFile(HttpServletRequest request, HttpServletResponse response) {
        // 获取上传文件的目录
        ServletContext sc = request.getSession().getServletContext();
        // 上传位置
        String uploadFilePath = sc.getRealPath("portraits") + "/";
        // 存储要下载的文件名
        Map<String, String> fileNameMap = new HashMap<String, String>();
        // 递归遍历filePath目录下的所有文件和目录，将文件的文件名存储到map集合中
        listfile(new File(uploadFilePath), fileNameMap);
        // 将Map集合发送到listfile.jsp页面进行显示
        request.setAttribute("fileNameMap", fileNameMap);
        return "listFile";
    }

    public void listfile(File file, Map<String, String> map){
        // 如果file代表的不是一个文件，而是一个目录
        if(!file.isFile()){
            // 列出该目录下的所有文件和目录
            File files[] = file.listFiles();
            // 遍历files数组
            for(File f : files){
                // 递归
                listfile(f, map);
            }
        } else {
            /**
             * 处理文件名
             */
            String fileName = file.getName();
            map.put(file.getName(), fileName);
        }
    }

    @RequestMapping("/downFile")
    public void downFile(HttpServletRequest request, HttpServletResponse response) {
        // 得到要下载的文件名
        String fileName = request.getParameter("filename");
        try {
            fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
            // 获取上传文件的目录
            ServletContext sc = request.getSession().getServletContext();
            // 上传位置
            String fileSavePath = sc.getRealPath("portraits");
            System.out.println(fileSavePath + "\\" + fileName);
            // 得到要下载的文件
            File file = new File(fileSavePath + "\\" + fileName);

            // 如果文件不存在
            if(!file.exists()) {
                request.setAttribute("message", "您要下载的资源已被删除！");
                System.out.println("要下载的资源不存在");
            }
            // 设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachement;filename="
                + URLEncoder.encode(fileName, "UTF-8"));
            // 读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(fileSavePath + "\\" + fileName);
            // 创建输出流
            OutputStream out = response.getOutputStream();
            // 创建缓冲区
            byte buffer[] = new byte[1024];

            int len = 0;
            // 循环将输入流中的内容读取到缓冲区当中
            while ((len = in.read(buffer)) > 0) {
                // 输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            // 关闭文件输入流
            in.close();
            // 关闭输出流
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
