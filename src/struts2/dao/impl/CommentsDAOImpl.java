package struts2.dao.impl;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import struts2.dao.CommentsDAO;
import struts2.db.DBConnect;
import struts2.vo.Comment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zengman on 2017/7/16.
 */
public class CommentsDAOImpl implements CommentsDAO{
    @Override
    public int getAllCommentsDB(ArrayList commentList) throws Exception {
        int flag = 0;
        String sql = "select * from CommentsInfo order by id desc";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try {
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            commentList.clear();//保证数据不会的叠加重复
            while(rs.next()){
                Comment comment = new Comment();
                comment.setId(rs.getInt(1));
                comment.setComment(rs.getString(2));
                comment.setUsername(rs.getString(3));
                comment.setThumbupNumber(rs.getInt(4));
                commentList.add(comment);
            }
            flag = 1;
        }catch (SQLException e){
            System.out.println(e.getMessage());

        }finally {
            dbc.close() ;
        }
        return flag;
    }

    @Override
    public int addCommentsDB(Comment comment) throws Exception {
        int flag =0;
        String sql = "insert into CommentsInfo (`comments`,`username`,`thumbup`) values(?,?,?)";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try {
            dbc =new  DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setString(1,comment.getComment());
            pstmt.setString(2,comment.getUsername());
            pstmt.setInt(3,0);
            pstmt.executeUpdate();
            System.out.println("add ok");
            flag = 1;
            pstmt.close() ;

        }catch (SQLException e){
            System.out.println(e.getMessage());

        }finally {
            dbc.close() ;
        }
        return flag;
    }

    @Override
    public int deleteCommentDB(int id) throws Exception {
        int flag=0;
        String sql = "delete from CommentsInfo where id = ?";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try {
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            pstmt.close() ;
            flag = 1;
        }catch (SQLException e){
            System.out.println(e.getMessage());

        }finally {
            dbc.close() ;
        }

        return flag;
    }

    @Override
    public int thumbUpAddDB(int id) throws Exception {
        int flag =0;
        System.out.println("id="+id+"add");
        String sql = "update CommentsInfo set thumbup=(thumbup+1) where id = ?";
        PreparedStatement pstmt = null ;
        DBConnect dbc = null;
        try{
            dbc = new DBConnect();
            pstmt = dbc.getConnection().prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            pstmt.close() ;
            flag = 1;
            System.out.println("add ok");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally {
            dbc.close() ;
        }

        return flag;
    }
}
