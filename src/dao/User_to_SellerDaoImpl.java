package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.JdbcUtils_C3P0;
import model.User;
import model.user_to_seller;

public class User_to_SellerDaoImpl implements User_to_SellerDao{

	@Override
	public ArrayList<user_to_seller> queryPoints(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<user_to_seller> points = new ArrayList<>();
		String sql = "select seller_id,points,points_blocked from user_to_seller where u_id = ?";
		try {
			conn = JdbcUtils_C3P0.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
			    user_to_seller uts = new user_to_seller();
			    uts.setseller_id(rs.getInt(1));
			    uts.setpoints(rs.getInt(2));
			    uts.setpoints_blocked(rs.getInt(3));
			    uts.setu_id(id);
			    points.add(uts);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("查询积分失败");
		} finally {
			JdbcUtils_C3P0.release(conn, ps, null);
		}
		return points;
	}

}
