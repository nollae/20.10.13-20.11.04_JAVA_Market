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

	// 로그인 했을때 방번호 지정(가장 먼저 생성 되었던 방으로)
	public int login_room_num(String id) {
		return dao.login_room_num(id);
	}

	// get_pid(main.vo.getId()
	public int get_pid(String id) {
		return dao.get_pid(id);
	}

	// 채팅 리스트 받아오기
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

	/** 검색 데이터 유효성 체크 -기림 **/
	public boolean searchDataCheck(String pname) {
		return dao.searchDataCheck(pname);
	}

	/** 리뷰ID 데이터 유효성 체크 -기림 **/
	public boolean reviewDataCheck(String mid) {
		return dao.searchDataCheck(mid);
	}

	/** 전체 조회 리스트 - 기림 **/
	public ArrayList<ProductVO> search_list() {
		return dao.search_list();
	}

	/** 제품 이름 검색리스트 - 기림 **/
	public ArrayList<ProductVO> search_list(String pname) {
		return dao.search_list(pname);
	}

	/** 리뷰 정보 저장 - 기림 **/
	public boolean review_insert(String comm, ReviewVO rvo) {
		return dao.review_insert(comm, rvo);
	}

	/** 리뷰 row,pid 일치 - 기림 **/
	public boolean product_row(ProductVO vo) {
		return dao.product_row(vo);
	}

	/** 리뷰 출력 - 기림 **/
	public ArrayList<ReviewVO> review_list(String id) {
		return dao.review_list(id);
	}

	/** 구매후 물품삭제 -기림 **/
	public boolean delete_review(String pname) {
		return dao.delete_review(pname);
	}

	/** 삭제 리스트 - 민석 **/
	public ArrayList<ProductVO> delete_list(MemberVO mvo) {
		return dao.delete_select(mvo);
	}

	/** 물품 정보 삭제 검색 - 민석 **/
	public boolean delselect(String pname, MemberVO mvo) {
		return dao.delselect(pname, mvo);
	}

	/** 물품 정보 삭제 - 민석 **/
	public boolean delete(String pname) {
		return dao.delete(pname);
	}

	/** 물품 정보 출력 -영화씨 select select1로 수정 **/
	public ProductVO selectProduct(String pid) {
		return dao.select1(pid);
	}

	/** 물품번호 검색 -영화씨 */
	public int SearchPid(String pid) {
		return dao.search(pid);
	}

	/** 물품정보 수정 -영화 */
	public boolean update_pr(ProductVO pvo, MemberVO mvo) {
		return dao.update_pr(pvo, mvo);
	}

	/** 아이디 중복체크 -민석 */
	public boolean idCheck(String mid) {
		return dao.idCheck(mid);
	}

	/** 이메일 중복체크 -민석 */
	public boolean emailCheck(String memail) {
		return dao.emailCheck(memail);
	}

	/** 로그인 체크 -민석 */
	public boolean loginCheck(String mid, String mpass) {
		return dao.loginCheck(mid, mpass);
	}

	/** 현재 로그인 아이디 */
	public boolean loginIng(String mid) {
		return dao.loginIng(mid);
	}

	/** 회원 정보 찾기 */
	public int searchMember(String mid) {
		return dao.searchMember(mid);
	}

	/** 회원 조회 */
	public MemberVO selectMember(String mpass) {
		return dao.selectMember(mpass);
	}

	public boolean update_info(MemberVO mvo) {
		return dao.update_info(mvo);
	}

} // class
