package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.apartogether.model.bean.Store;
import com.apartogether.utility.Paging;

public class StoreDao extends SuperDao {

	public List<Store> selectAll(Paging pageInfo) throws Exception {
		List<Store> storeBean = null;
		
		String sql = "select * from store";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = super.getConnection();
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			
			
		}
		
		return storeBean;
	}
	
}
