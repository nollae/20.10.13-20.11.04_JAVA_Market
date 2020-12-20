
package market;

import java.awt.print.Printable;
//backup
import java.util.ArrayList;

class MarketDAO extends DBConn {

	/**
	 * login_state
	 */
	public boolean login_state(MemberVO vo, int login_state) {
		boolean result = false;

		try {
			String sql = "update market_member set login_state=? where mid=?";
			getPreparedStatement(sql);
			pstmt.setInt(1, login_state);
			pstmt.setString(2, vo.id);
			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * dao.get_pid(id)
	 */
	public int get_pid(String id) {
		int room_num = -1;
		try {
			String sql = "select pid from product where mid=? ";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				room_num = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return room_num;
	}

	/**
	 * get_pname
	 */
	public String get_pname(String id) {
		String pname = "";
		try {
			String sql = "select pname from product where pid=?  ";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pname = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pname;
	}

	/**
	 * login_room_num
	 */
	public int login_room_num(String id) {
		int room_num = 0;

		try {
			String sql = "select pid from product where mid=? order by pid ";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				room_num = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return room_num;
	}

	/**
	 * chat_list
	 */
	public ArrayList<String> chat_list(String id) {
		ArrayList<String> list = new ArrayList<String>();

		try {
			String sql = "select pid from product where mid=? order by pid ";
			getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * server_state
	 */
	public boolean server_state(MemberVO vo, int server_state) {
		boolean result = false;

		try {
			String sql = "update market_member set server_state=? where mid=?";
			getPreparedStatement(sql);
			pstmt.setInt(1, server_state);
			pstmt.setString(2, vo.id);
			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * SellCkeck
	 */
	public boolean SellCkeck(MemberVO vo) {
		boolean result = false;

		try {
			String sql = "select  count(*) from product  where mid =? ";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getInt(1) >= 1) {
					result = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * join
	 */
	public boolean join(MemberVO vo) {
		boolean result = false;

		try {
			String sql = "insert into market_member values(?,?,?,?,?,?,sysdate,0,0)";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getAddr());
			pstmt.setString(5, vo.getPhone());
			pstmt.setString(6, vo.getEmail());
			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * insert
	 */
	public boolean insert(ProductVO vo) {
		boolean result = false;
		try {
			String sql = "insert into product values(SEQ_PID.NEXTVAL,?,?,?,?,?,?,?,?,sysdate,999)";
			getPreparedStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getPname());
			pstmt.setInt(3, vo.getPrice());
			pstmt.setString(4, vo.getPphone());
			pstmt.setString(5, vo.getState());
			pstmt.setString(6, vo.getMethod());
			pstmt.setString(7, vo.getArea());
			pstmt.setString(8, vo.getExplain());

			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * search ������ ��ȿ�� üũ - �⸲
	 */
	public boolean searchDataCheck(String pname) {
		boolean result = false;

		try {
			String sql = "select count(*) from product where pname = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, pname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) != 0)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * review ������ ��ȿ�� üũ -�⸲
	 */
	public boolean reviewDataCheck(String mid) {
		boolean result = false;

		try {
			String sql = "select count(*) from review where mid = ?";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) != 0)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * search_list - �⸲
	 */
	public ArrayList<ProductVO> search_list() {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();

		try {
			String sql = " select pid, mid, pname, price, pphone, state, method, area, explain, to_char(pdate,'yyyy-mm-dd') from product order by pid desc";
			getPreparedStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setPid(rs.getString(1));
				vo.setMid(rs.getString(2));
				vo.setPname(rs.getString(3));
				vo.setPrice(rs.getInt(4));
				vo.setPphone(rs.getString(5));
				vo.setState(rs.getString(6));
				vo.setMethod(rs.getString(7));
				vo.setArea(rs.getString(8));
				vo.setExplain(rs.getString(9));
				vo.setPdate(rs.getString(10));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * delete_select - �μ�
	 */
	public ArrayList<ProductVO> delete_select(MemberVO mvo) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();

		try {
			String sql = " select pid, pname, price, pphone, state, method, area, explain, to_char(pdate,'yyyy-mm-dd')"
					+ " from (select pid, pname, price, pphone, state, method, area, explain, pdate from product p, market_member m "
					+ " where m.mid=p.mid and m.mid=? order by pid desc)";
			getPreparedStatement(sql);
			pstmt.setString(1, mvo.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setPid(rs.getString(1));
				vo.setPname(rs.getString(2));
				vo.setPrice(rs.getInt(3));
				vo.setPphone(rs.getString(4));
				vo.setState(rs.getString(5));
				vo.setMethod(rs.getString(6));
				vo.setArea(rs.getString(7));
				vo.setExplain(rs.getString(8));
				vo.setPdate(rs.getString(9));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * search_list(String name) - �⸲
	 */
	public ArrayList<ProductVO> search_list(String pname) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();

		try {
			String sql = "select pid,mid,pname, price, pphone, state, method, area, explain, to_char(pdate,'yyyy-mm-dd') from product where pname like '%' || ? || '%' order by pid desc";
			getPreparedStatement(sql);
			pstmt.setString(1, pname);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setPid(rs.getString(1));
				vo.setMid(rs.getString(2));
				vo.setPname(rs.getString(3));
				vo.setPrice(rs.getInt(4));
				vo.setPphone(rs.getString(5));
				vo.setState(rs.getString(6));
				vo.setMethod(rs.getString(7));
				vo.setArea(rs.getString(8));
				vo.setExplain(rs.getString(9));
				vo.setPdate(rs.getString(10));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * review_insert -�⸲
	 */
	public boolean review_insert(String comm, ReviewVO rvo) {
		boolean result = false;
		try {
			String sql = "insert into review values(?,?,?,sysdate)";
			getPreparedStatement(sql);

			pstmt.setString(1, rvo.getMid());
			pstmt.setString(2, rvo.getPid());
			pstmt.setString(3, comm);

			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * product_row -�⸲
	 */
	public boolean product_row(ProductVO vo) {
		boolean result = false;

		try {
			String sql = " update product set t_row =? where pid = ? ";
			getPreparedStatement(sql);

			pstmt.setString(1, vo.getPid());
			pstmt.setString(2, vo.getPid());

			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * review_list - �⸲
	 */
	public ArrayList<ReviewVO> review_list(String id) {
		ArrayList<ReviewVO> rlist = new ArrayList<ReviewVO>();
		try {
			String sql = " select mid,pid,evaluation,to_char(rdate,'yyyy-mm-dd') from review where mid = ? order by rdate desc";
			getPreparedStatement(sql);

			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReviewVO vo = new ReviewVO();
				vo.setMid(rs.getString(1));
				vo.setPid(rs.getString(2));
				vo.setEvaluation(rs.getString(3));
				vo.setRdate(rs.getString(4));
				rlist.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlist;
	}

	/**
	 * delete_review -�⸲
	 */
	public boolean delete_review(String pname) {
		boolean result = false;

		try {
			String sql = "delete from product where pid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, pname);
			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * delete select -�μ�
	 */
	public boolean delselect(String pname, MemberVO mvo) {
		boolean result = false;

		try {
			String sql = "select count(*) from product where pid=? and mid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, pname);
			pstmt.setString(2, mvo.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) != 0)
					result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * delete -�μ�
	 */
	public boolean delete(String pname) {
		boolean result = false;

		try {
			String sql = "delete from product where pid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, pname);
			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/** �ϳ��� ��ǰ ��ȸ -��ȭ�� select select1�� ���� */
	public ProductVO select1(String pid) {
		ProductVO pvo = new ProductVO();

		try {
			String sql = "select pname, price, pphone, state, method, area, explain from product where pid=?";
			getPreparedStatement(sql);

			pstmt.setString(1, pid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pvo.setPname(rs.getString(1));
				pvo.setPrice(rs.getInt(2));
				pvo.setPphone(rs.getString(3));
				pvo.setState(rs.getString(4));
				pvo.setMethod(rs.getString(5));
				pvo.setArea(rs.getString(6));
				pvo.setExplain(rs.getString(7));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pvo;
	}

	/** ��ǰ��ȣ �˻� */
	public int search(String pid) {
		int result = 0;

		try {
			String sql = "select count(*) from product where pid=?";
			getPreparedStatement(sql);

			pstmt.setString(1, pid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/** ��ǰ���� ���� */
	public boolean update_pr(ProductVO pvo, MemberVO mvo) {
		boolean result = false;

		try {
			String sql = "update product set pname=?, price=?, pphone=?, state=?, method=?, area=?, explain=? where pid=? and mid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, pvo.getPname());
			pstmt.setInt(2, pvo.getPrice());
			pstmt.setString(3, pvo.getPphone());
			pstmt.setString(4, pvo.getState());
			pstmt.setString(5, pvo.getMethod());
			pstmt.setString(6, pvo.getArea());
			pstmt.setString(7, pvo.getExplain());
			pstmt.setString(8, pvo.getPid());
			pstmt.setString(9, mvo.getId());

			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * ���̵� �ߺ� Ȯ��
	 */
	public boolean idCheck(String mid) {
		boolean result = false;

		try {
			String sql = "select count(mid) from market_member where mid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) != 0)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * �̸��� �ߺ� Ȯ��
	 */
	public boolean emailCheck(String memail) {
		boolean result = false;

		try {
			String sql = "select count(memail) from market_member where memail=?";
			getPreparedStatement(sql);
			pstmt.setString(1, memail);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) != 0)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * �α��� üũ
	 */
	public boolean loginCheck(String mid, String mpass) {
		boolean result = false;

		try {
			String sql = "select count(*) from market_member where mid=? and mpass=?";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, mpass);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) != 0)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/** ���� �α��� ���̵� */
	public boolean loginIng(String mid) {
		boolean result = false;

		try {
			String sql = "select count(*) from market_member where mid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) != 0)
					result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/** �н������ ��ġ�ϴ� ��� ã�� */
	public int searchMember(String mpass) {
		int result = 0;

		try {
			String sql = "select count(*) from market_member where mid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, mpass);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/** ȸ�� ��ȸ */
	public MemberVO selectMember(String mid) {
		MemberVO mvo = new MemberVO();

		try {
			String sql = "select mpass, mname, maddr, mphone, memail from market_member where mid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				mvo.setPass(rs.getString(1));
				mvo.setName(rs.getString(2));
				mvo.setAddr(rs.getString(3));
				mvo.setPhone(rs.getString(4));
				mvo.setEmail(rs.getString(5));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mvo;
	}

	public boolean update_info(MemberVO mvo) {
		boolean result = false;

		try {
			String sql = "update market_member set mpass=?, mname=?, maddr=?, mphone=?, memail=? where mid=?";
			getPreparedStatement(sql);
			pstmt.setString(1, mvo.getPass());
			pstmt.setString(2, mvo.getName());
			pstmt.setString(3, mvo.getAddr());
			pstmt.setString(4, mvo.getPhone());
			pstmt.setString(5, mvo.getEmail());
			pstmt.setString(6, mvo.getId());
			int count = pstmt.executeUpdate();
			if (count != 0)
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
