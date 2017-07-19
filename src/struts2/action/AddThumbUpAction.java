package struts2.action;

import com.opensymphony.xwork2.ActionSupport;
import struts2.dao.CommentsDAO;
import struts2.dao.impl.CommentsDAOImpl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by zengman on 2017/7/19.
 */
public class AddThumbUpAction extends ActionSupport{
    private int id;

    public int getId() {
        return id;
    }
    private InputStream stream;

    public InputStream getStream() {
        return stream;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String execute() throws Exception{
        CommentsDAO dao = new CommentsDAOImpl();
        int flag = dao.thumbUpAddDB(id);
        if(flag ==0){
            System.out.println("thumbup add fail");
        }else{
            System.out.println("add success");
        }
        stream = new ByteArrayInputStream("thumb up ok".getBytes(StandardCharsets.UTF_8));
        return SUCCESS;
    }
}
