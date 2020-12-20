package market;

public class MemberVO {
	String id, pass, name, addr, phone, email;
	int login_state, server_state;

	public int getLogin_state() {
		return login_state;
	}

	public void setLogin_state(int login_state) {
		this.login_state = login_state;
	}

	public int getServer_state() {
		return server_state;
	}

	public void setServer_state(int server_state) {
		this.server_state = server_state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}