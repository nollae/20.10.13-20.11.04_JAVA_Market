package market;

//backup
import java.util.ArrayList;

public class MarketMgmSystem {
	// Field
	MarketDAO dao;

	// Constructor
	public MarketMgmSystem() {
		dao = new MarketDAO();
	}

	// get_pname
	public String get_pname(String id) {
		return dao.get_pname(id);
	}

	// �α��� ������ ���ȣ ����(���� ���� ���� �Ǿ��� ������)
	public int login_room_num(String id) {
		return dao.login_room_num(id);
	}

	// get_pid(main.vo.getId()
	public int get_pid(String id) {
		return dao.get_pid(id);
	}

	// ä�� ����Ʈ �޾ƿ���
	public ArrayList<String> chat_list(String id) {
		return dao.chat_list(id);
	}

	public boolean login_state(MemberVO vo, int login_state) {
		return dao.login_state(vo, login_state);
	}

	public boolean server_state(MemberVO vo, int server_state) {
		return dao.server_state(vo, server_state);
	}

	public boolean SellCkeck(MemberVO vo) {
		return dao.SellCkeck(vo);
	}

	public boolean join(MemberVO vo) {
		return dao.join(vo);
	}

	public boolean register(ProductVO vo) {
		return dao.insert(vo);
	}

	/** �˻� ������ ��ȿ�� üũ -�⸲ **/
	public boolean searchDataCheck(String pname) {
		return dao.searchDataCheck(pname);
	}

	/** ����ID ������ ��ȿ�� üũ -�⸲ **/
	public boolean reviewDataCheck(String mid) {
		return dao.searchDataCheck(mid);
	}

	/** ��ü ��ȸ ����Ʈ - �⸲ **/
	public ArrayList<ProductVO> search_list() {
		return dao.search_list();
	}

	/** ��ǰ �̸� �˻�����Ʈ - �⸲ **/
	public ArrayList<ProductVO> search_list(String pname) {
		return dao.search_list(pname);
	}

	/** ���� ���� ���� - �⸲ **/
	public boolean review_insert(String comm, ReviewVO rvo) {
		return dao.review_insert(comm, rvo);
	}

	/** ���� row,pid ��ġ - �⸲ **/
	public boolean product_row(ProductVO vo) {
		return dao.product_row(vo);
	}

	/** ���� ��� - �⸲ **/
	public ArrayList<ReviewVO> review_list(String id) {
		return dao.review_list(id);
	}

	/** ������ ��ǰ���� -�⸲ **/
	public boolean delete_review(String pname) {
		return dao.delete_review(pname);
	}

	/** ���� ����Ʈ - �μ� **/
	public ArrayList<ProductVO> delete_list(MemberVO mvo) {
		return dao.delete_select(mvo);
	}

	/** ��ǰ ���� ���� �˻� - �μ� **/
	public boolean delselect(String pname, MemberVO mvo) {
		return dao.delselect(pname, mvo);
	}

	/** ��ǰ ���� ���� - �μ� **/
	public boolean delete(String pname) {
		return dao.delete(pname);
	}

	/** ��ǰ ���� ��� -��ȭ�� select select1�� ���� **/
	public ProductVO selectProduct(String pid) {
		return dao.select1(pid);
	}

	/** ��ǰ��ȣ �˻� -��ȭ�� */
	public int SearchPid(String pid) {
		return dao.search(pid);
	}

	/** ��ǰ���� ���� -��ȭ */
	public boolean update_pr(ProductVO pvo, MemberVO mvo) {
		return dao.update_pr(pvo, mvo);
	}

	/** ���̵� �ߺ�üũ -�μ� */
	public boolean idCheck(String mid) {
		return dao.idCheck(mid);
	}

	/** �̸��� �ߺ�üũ -�μ� */
	public boolean emailCheck(String memail) {
		return dao.emailCheck(memail);
	}

	/** �α��� üũ -�μ� */
	public boolean loginCheck(String mid, String mpass) {
		return dao.loginCheck(mid, mpass);
	}

	/** ���� �α��� ���̵� */
	public boolean loginIng(String mid) {
		return dao.loginIng(mid);
	}

	/** ȸ�� ���� ã�� */
	public int searchMember(String mid) {
		return dao.searchMember(mid);
	}

	/** ȸ�� ��ȸ */
	public MemberVO selectMember(String mpass) {
		return dao.selectMember(mpass);
	}

	public boolean update_info(MemberVO mvo) {
		return dao.update_info(mvo);
	}

} // class
