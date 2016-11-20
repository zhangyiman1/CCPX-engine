package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.user_to_seller;

public interface User_to_SellerDao {

	public ArrayList<user_to_seller> queryPoints(int id) throws SQLException;
}
