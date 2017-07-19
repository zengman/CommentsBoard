package struts2.action;

import com.opensymphony.xwork2.ActionContext;
import struts2.dao.CommentsDAO;
import struts2.dao.impl.CommentsDAOImpl;
import struts2.vo.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zengman on 2017/7/16.
 */
public class ShowCommentsAction {
    private ArrayList commentList = new ArrayList();

    public ArrayList getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList commentList) {
        this.commentList = commentList;
    }

    public String show() throws Exception{
        CommentsDAO dao = new CommentsDAOImpl();
        int flag = dao.getAllCommentsDB(commentList);
        if(flag ==1){
            ActionContext context= ActionContext.getContext();
            Map request = (Map)context.get("request");
            request.put("commentList", commentList);

        }else{
            System.out.println("show comments fail");
        }
        return "show";

    }

}
