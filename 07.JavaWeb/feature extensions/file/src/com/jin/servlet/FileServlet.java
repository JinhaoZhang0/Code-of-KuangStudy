package com.jin.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断上传的文件是普通表单还是带文件的表单
        if (ServletFileUpload.isMultipartContent(req)) {
            return; //终止方法运行，说明这是一个普通的表单，直接返回
        }

        try {
        //创建上传文件的保存路径，建议在WEB-INF路径下，安全，用户无法直接访问的文件
        String uploadPath = this.getServletContext().getRealPath("/WEB_INF/uplaod");
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()) {
            uploadFile.mkdir(); //创建这个目录
        }

        //缓存，临时文件
        //临时路径，假如文件超过了预期的大小，我们就把他放到一个临时的文件中，过几天自动删除，或者提醒用户转存为永久
        String tmpPath = this.getServletContext().getRealPath("/WEB_INF/uplaod");
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdir(); //创建这个目录
        }

        //处理上传的文件，一般都需要通过流来获取，我们可以使用request.getInputStream()，原生态的文件上传流获取，十分麻烦
        //但是我们都建议使用Apache的文件上传组件来实现，common0-fileupload，它需要依赖于common-io组件

        /*
         *ServletFileUpload**负责处理上传的文件数据，并将表单中每个输入项封装成一个FileItem对象，
         * 在使用ServletFileUpload对象解析请求时需要DiskFileItemFactory对象。
         * 所以，我们需要在进行解析工作前构造好DiskFileItemFactory对象，
         * 通过ServletFileUpload对象的构造方法或setFileItemFactory()方法设置ServletFileUpload对象的fileItemFactory属性
         */
        //1.创建DiskFileItemFactory对象，处理文件上传路径或者大小限制的；
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2. 获取ServletFileUpload
        ServletFileUpload upload = new ServletFileUpload(factory);

        //3. 处理上传的文件
        //把前端请求解析，封装成一个FileItem对象，需要从ServletFileUpload对象中获取
            List<FileItem> fileItems = upload.parseRequest(req);
            //fileItem 每一个表单对象
            for (FileItem fileItem: fileItems) {
                //判断上传的文件是普通的表单还是带文件的表单
                if (fileItem.isFormField()) {
                    //getFileName指的是前端表单空间的name
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8"); //处理乱码
                    System.out.println(name+":"+value);
                } else { //文件
                    //================处理文件=================
                    String uploadFileName = fileItem.getName();
                    //可能存在文件名不合法的情况
                    if (uploadFileName==null || uploadFileName.trim().equals("")) {
                        continue;
                    }

                    //获得上传的文件名 /images/girl/paojie.jpg
                    String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/")+1);
                    //获得文件的后缀名
                    String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);

                    //可以使用UUID（唯一识别的通用码），博阿政文件名唯一；
                    //UUID.randomUUID(),随机生成一个唯一识别的通用码；

                    //网络传输中的东西，都需要序列化
                    //POJO，实体类，如果想要在多个电脑上运行，传输===>就需要把对象序列化了
                    // JNI = Java Native Interface
                    //implements Serializable : 标记接口，JVM ---> 本地方法栈 native --> c++
                    String uuidPath = UUID.randomUUID().toString();
                    //================存放地址=================
                    //存到哪？ uploadPath
                    //文件真实存在的路径 realPath
                    String realPath = uploadPath+"/"+uuidPath;
                    //给每个文件创建一个对应的文件夹
                    File realPathFile = new File(realPath);
                    if (!realPathFile.exists()) {
                        realPathFile.mkdir();
                    }

                    //================文件传输=================
                    //获得文件上传的流
                    InputStream inputStream = fileItem.getInputStream();
                    //创建一个文件的输入流
                    //realPath = 真实的文件夹
                    //差了一个文件，加上输出文件的名字+"/"+uuidFileName
                    FileOutputStream fos = new FileOutputStream(realPath+"/"+fileName);

                    //创建一个缓冲区
                    byte[] buffer = new byte[1024*1024];

                    //判断是否读取完毕
                    int len = 0;
                    //如果大于0说明还存在数据
                    while ((len=inputStream.read(buffer))>0){
                        fos.write(buffer,0,len);
                    }
                    //关闭流
                    fos.close();
                    inputStream.close();

//                    msg = "文件上传成功";
                    fileItem.delete(); //上传成功，清除临时文件
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
