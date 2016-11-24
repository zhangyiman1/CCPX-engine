package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Record;
import model.User;
import utils.JdbcUtils_C3P0;

public class RecordDaoImpl implements RecordDao{

	@Override
	public ArrayList<Record> getReferenceRecords(int seller_id_from,int seller_id_to) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Record> list=new ArrayList<Record>();
		
		//通过卖家获取信息的对称性
		String sql = "select  * from request where ((seller_id_from=? and seller_id_to=?) or (seller_id_from=? and seller_id_to=?))"
				+ " and status=? order by update_time desc limit 10";
		
		try {
			conn = JdbcUtils_C3P0.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1,seller_id_from);
			ps.setInt(2, seller_id_to);
			ps.setInt(3,seller_id_to);
			ps.setInt(4, seller_id_from);
			ps.setString(5, "1");//设置成功的状态值
			rs = ps.executeQuery();
			while(rs.next()&&list.size()<10){
				Record record = new Record();
				record.setR_id( rs.getInt(1));
				record.setU_id_from(rs.getInt(2));
				record.setU_id_to(rs.getInt(3));
				record.setSeller_id_from(rs.getInt(4));
				record.setSeller_id_to(rs.getInt(5));
				record.setPoints_from(rs.getInt(6));
				record.setPoints_to(rs.getInt(7));
				record.setOffer_id(rs.getInt(8));
				record.setStatus(rs.getString(9));
				record.setUpdate_time(rs.getDate(10));
				list.add(record);
			}
//			for(Record record2:list){
//				System.out.println(record2.getR_id());
//			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("获取参考记录信息失败");
		} finally {
			JdbcUtils_C3P0.release(conn, ps, null);
		}
		//System.out.println(list.size());
		return (ArrayList<Record>) list;
		
	}
	

}
