package com.en.custom.model.dao;

import static com.en.common.Template.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.en.custom.model.vo.CustomComment;
import com.en.custom.model.vo.CustomOrder;
import com.en.custom.model.vo.CustomPost;

public class CustomDao {
	
	private Properties prop=new Properties();
	
	public CustomDao() {
		String path=CustomDao.class.getResource("/sql/custom/custom_sql.properties").getPath();
		try {
		prop.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public List<CustomPost> selectCustom(Connection conn,String type,String key){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<CustomPost> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectCustom").replaceAll("#TYPE", type));
			pstmt.setString(1, key);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CustomPost cp=new CustomPost();
				cp.setcIdx(rs.getInt("C_IDX"));
				cp.setTitle(rs.getString("TITLE"));
				cp.setContent(rs.getString("CONTENT"));
				cp.setWriteDate(rs.getDate("WRITE_DATE"));
				cp.setLikeCount(rs.getInt("LIKE_COUNT"));
				cp.setViewCount(rs.getInt("VIEW_COUNT"));
				cp.setCustomNo(rs.getInt("CUSTOM_NO"));
				cp.setMemberId(rs.getString("MEMBER_ID"));
				cp.setcImage(rs.getString("C_IMAGE"));
				list.add(cp);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
		
		
	}
	
	public int selectCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int total=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				total=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return total;
	}
	
	public int selectCount(Connection conn,String type,String key) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int total=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("selectCount2").replaceAll("@TYPE", type));
			pstmt.setNString(1, key);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				total=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return total;
	}
	
	public List<CustomPost> customList(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<CustomPost> list=new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("customList"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CustomPost cp=new CustomPost();
				cp.setcIdx(rs.getInt("C_IDX"));
				cp.setTitle(rs.getString("TITLE"));
				cp.setContent(rs.getString("CONTENT"));
				cp.setWriteDate(rs.getDate("WRITE_DATE"));
				cp.setLikeCount(rs.getInt("LIKE_COUNT"));
				cp.setViewCount(rs.getInt("VIEW_COUNT"));
				cp.setCustomNo(rs.getInt("CUSTOM_NO"));
				cp.setMemberId(rs.getString("MEMBER_ID"));
				cp.setcImage(rs.getNString("C_IMAGE"));
				list.add(cp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	
	   public int insertPost(Connection conn, CustomPost cp) {
		      PreparedStatement pstmt=null;
		      int result=0;
		      
		      try {
		         pstmt=conn.prepareStatement(prop.getProperty("insertPost"));
		         pstmt.setNString(1,cp.getTitle());
		         pstmt.setNString(2,cp.getContent());
		         pstmt.setInt(3, cp.getCustomNo());
		         pstmt.setNString(4,cp.getMemberId());
		         pstmt.setNString(5, cp.getcImage());
		         result=pstmt.executeUpdate();
		      }catch(SQLException e) {
		         e.printStackTrace();
		      }finally {
		         close(pstmt);
		         
		      }return result;
		      
		   }
	   
	   public List<CustomPost> customSort(Connection conn,String sort){
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		   List<CustomPost> list=new ArrayList();
		   try {
			   pstmt=conn.prepareStatement(prop.getProperty("customSort").replaceAll("@SORT", sort));
			   rs=pstmt.executeQuery();
			   while(rs.next()) {
				   CustomPost cp=new CustomPost();
				   cp.setcIdx(rs.getInt("C_IDX"));
					cp.setTitle(rs.getString("TITLE"));
					cp.setContent(rs.getString("CONTENT"));
					cp.setWriteDate(rs.getDate("WRITE_DATE"));
					cp.setLikeCount(rs.getInt("LIKE_COUNT"));
					cp.setViewCount(rs.getInt("VIEW_COUNT"));
					cp.setCustomNo(rs.getInt("CUSTOM_NO"));
					cp.setMemberId(rs.getString("MEMBER_ID"));
					cp.setcImage(rs.getString("C_IMAGE"));
					list.add(cp);
			   }
		   }catch(SQLException e){
			   e.printStackTrace();
		   }finally {
			   close(rs);
			   close(pstmt);
		   }return list;
		   
		   
	   }
	   
	   public int likeCount(Connection conn, int cIdx) {
		   PreparedStatement pstmt=null;
		   int result=0;
		   try {
			   pstmt=conn.prepareStatement(prop.getProperty("likeCount"));
			   pstmt.setInt(1, cIdx);
			   result=pstmt.executeUpdate();
		   }catch(SQLException e) {
			   e.printStackTrace();
		   }finally {
			   close(pstmt);
		   }return result;
	   }
	   
	   public int likeCount2(Connection conn, int cIdx) {
		   PreparedStatement pstmt=null;
		   int result=0;
		   try {
			   pstmt=conn.prepareStatement(prop.getProperty("likeCount2"));
			   pstmt.setInt(1, cIdx);
			   result=pstmt.executeUpdate();
		   }catch(SQLException e) {
			   e.printStackTrace();
		   }finally {
			   close(pstmt);
		   }return result;
	   }
	   
	   public int searchCount(Connection conn,int cIdx) {
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		   int lc=0;
		   try {
			   pstmt=conn.prepareStatement(prop.getProperty("searchCount"));
			   pstmt.setInt(1, cIdx);
			   rs=pstmt.executeQuery();
			   if(rs.next()) {
				   lc=rs.getInt(1);
			   }
		   }catch(SQLException e) {
			   e.printStackTrace();
		   }finally {
			   close(rs);
			   close(pstmt);
		   }return lc;
	   }
	   
	   public List<CustomOrder> memberCustom(Connection conn,String userId){
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		   List<CustomOrder> list=new ArrayList();
		   try {
			   pstmt=conn.prepareStatement(prop.getProperty("memberCustom"));
			   pstmt.setString(1, userId);
			   rs=pstmt.executeQuery();
			   while(rs.next()) {
				   CustomOrder co=new CustomOrder();
				   co.setCustomNo(rs.getInt("CUSTOM_NO"));
				   co.setCustomPrice(rs.getInt("CUSTOM_PRICE"));
				   co.setCustomName(rs.getString("CUSTOM_NAME"));
				   co.setCustomType(rs.getNString("CUSTOM_TYPE"));
				   co.setCustomComment(rs.getString("CUSTOM_COMMENT"));
				   co.setMemberId(rs.getNString("MEMBER_ID"));
				   list.add(co);
			   }
			   
		   }catch(SQLException e) {
			   e.printStackTrace();
		   }finally {
			   close(rs);
			   close(pstmt);
		   }return list;
	   }
	   
	   public CustomOrder memberCustom2(Connection conn,int val){
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		   CustomOrder co=null;
		   try {
			   pstmt=conn.prepareStatement(prop.getProperty("memberCustom2"));
			   pstmt.setInt(1, val);
			   rs=pstmt.executeQuery();
			   if(rs.next()) {
				   co=new CustomOrder();
				   co.setCustomNo(rs.getInt("CUSTOM_NO"));
				   co.setCustomPrice(rs.getInt("CUSTOM_PRICE"));
				   co.setCustomName(rs.getString("CUSTOM_NAME"));
				   co.setCustomType(rs.getNString("CUSTOM_TYPE"));
				   co.setCustomComment(rs.getString("CUSTOM_COMMENT"));
				   co.setMemberId(rs.getNString("MEMBER_ID"));
			   }
			   
		   }catch(SQLException e) {
			   e.printStackTrace();
		   }finally {
			   close(rs);
			   close(pstmt);
		   }return co;
	   }
	   
	   public CustomPost selectCustomOne(Connection conn,int no) {
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		   CustomPost cp=null;
		   try {
			   pstmt=conn.prepareStatement(prop.getProperty("selectCustomOne"));
			   pstmt.setInt(1, no);
			   rs=pstmt.executeQuery();
			   if(rs.next()) {
				   cp=new CustomPost();
				   cp.setcIdx(rs.getInt("C_IDX"));
					cp.setTitle(rs.getString("TITLE"));
					cp.setContent(rs.getString("CONTENT"));
					cp.setWriteDate(rs.getDate("WRITE_DATE"));
					cp.setLikeCount(rs.getInt("LIKE_COUNT"));
					cp.setViewCount(rs.getInt("VIEW_COUNT"));
					cp.setCustomNo(rs.getInt("CUSTOM_NO"));
					cp.setMemberId(rs.getString("MEMBER_ID"));
					cp.setcImage(rs.getString("C_IMAGE"));
			   }
		   }catch(SQLException e) {
			   e.printStackTrace();
		   }finally {
			   close(rs);
			   close(pstmt);
		   }return cp;
		   
	   }

	public List<CustomComment> commentList(Connection conn, int no){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<CustomComment> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("commentList"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CustomComment cc=new CustomComment();
				cc.setCustomCommentNo(rs.getInt("custom_comment_no"));
				cc.setCustomCommentLevel(rs.getInt("custom_comment_level"));
				cc.setCustomCommentWriter(rs.getNString("custom_comment_writer"));
				cc.setCustomCommentContent(rs.getNString("custom_comment_content"));
				cc.setCustomRef(rs.getInt("custom_ref"));
				cc.setCustomCommentRef(rs.getInt("custom_comment_ref"));
//				cc.setCustomCommentDate(rs.getDate("custom_comment_date"));
				String date=rs.getString("custom_comment_date");
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date to=sdf.parse(date);
				cc.setCustomCommentDate(to);
//				System.out.println(to);
//				System.out.println(date+"===============================");
				list.add(cc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ParseException e){
			e.printStackTrace();
		}finally {
		
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int insertCommnet(Connection conn, CustomComment cc) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("insertComment"));
			pstmt.setInt(1, cc.getCustomCommentLevel());
			pstmt.setNString(2, cc.getCustomCommentWriter());
			pstmt.setNString(3, cc.getCustomCommentContent());
			pstmt.setInt(4,cc.getCustomRef());
			pstmt.setNString(5, cc.getCustomCommentRef()==0?null:String.valueOf(cc.getCustomCommentRef()));
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int viewCount(Connection conn,int no) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("viewCount"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int commentCount(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int cCount=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("commentCount"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cCount=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return cCount;
	}

	public int searchCustomCount(Connection conn, String memberId) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("SEARCHCUSTOMCOUNT"));
			pstmt.setString(1, memberId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}   
	
	public int insertCustom(Connection conn, int price,String pName,String content,String memberId) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("INSERTCUSTOM"));
			pstmt.setInt(1, price);
			pstmt.setNString(2, pName);
			pstmt.setNString(3, content);
			pstmt.setString(4,memberId);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public int searchCustomNo(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("SEARCHCUSTOMNO"));
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	


	public String postList(Connection conn, int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String c=null;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("postList"));
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				c=rs.getNString("C_IMAGE");
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return c;
	}

	public int updatePost(Connection conn, CustomPost c) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("updatePost"));
			pstmt.setString(1, c.getTitle());
			pstmt.setString(2,c.getContent());
			pstmt.setString(3, c.getcImage());
			pstmt.setInt(4,c.getcIdx());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public List<CustomPost> myPage(Connection conn, String id){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CustomPost> list = new ArrayList();
		try {
			pstmt=conn.prepareStatement(prop.getProperty("mypage"));
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
			CustomPost cp = new CustomPost();
			cp.setcIdx(rs.getInt("c_idx"));
			cp.setTitle(rs.getString("title"));
			cp.setContent(rs.getString("content"));
			cp.setMemberId(rs.getString("member_id"));
			cp.setWriteDate(rs.getDate("write_date"));
			cp.setLikeCount(rs.getInt("like_count"));
			list.add(cp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	   
	public List<CustomPost> customList(Connection conn,int cPage,int numPerPage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<CustomPost> list=new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(prop.getProperty("adminCustomList"));
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CustomPost cp=new CustomPost();
				cp.setcIdx(rs.getInt("C_IDX"));
				cp.setTitle(rs.getString("TITLE"));
				cp.setContent(rs.getString("CONTENT"));
				cp.setWriteDate(rs.getDate("WRITE_DATE"));
				cp.setLikeCount(rs.getInt("LIKE_COUNT"));
				cp.setViewCount(rs.getInt("VIEW_COUNT"));
				cp.setCustomNo(rs.getInt("CUSTOM_NO"));
				cp.setMemberId(rs.getString("MEMBER_ID"));
				cp.setcImage(rs.getNString("C_IMAGE"));
				list.add(cp);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}

	public int deletePost(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(prop.getProperty("deletePost"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

}






