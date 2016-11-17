package dao;

public interface PlatformDao {

	public Boolean removeExchange(String request_id, String user_from);
	public Boolean declineExchange(String request_id, String user_to);
	public Boolean removeOffer(String offer_id, String user_id);
}
