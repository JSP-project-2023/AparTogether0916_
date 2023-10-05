package com.apartogether.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException; /*pk값 중복처리일 경우 Exception 처리 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apartogether.model.bean.Member;
import com.apartogether.model.bean.Vote;
import com.apartogether.utility.MyUtility;
import com.apartogether.utility.Paging;
import com.apartogether.utility.PagingMember;
import com.apartogether.utility.PagingVote;

public class MemberDao extends SuperDao {

	public int UpdateData(Member bean) throws Exception{
    // 회원정보 수정에 사용합니다. MemberUpdateController
		System.out.println("상품 수정 빈 :\n" + bean);
		PreparedStatement pstmt = null;
		String sql = " update members set mtype = ? , name = ? ,  phone = ? , birth = ? , gender = ? , nickname = ? , address = ? , profile = ? , passwordanswer = ? , passwordquest = ?, password = ? " ;
		sql += " where id = ? " ;
		int cnt = -1 ;

		conn = super.getConnection();
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, bean.getMtype());
		pstmt.setString(2, bean.getName());
		pstmt.setString(3, bean.getPhone());
		pstmt.setString(4, bean.getBirth());
		pstmt.setString(5, bean.getGender());
		pstmt.setString(6, bean.getNickname());
		pstmt.setString(7, bean.getAddress());
		pstmt.setString(8, bean.getProfile());
		pstmt.setString(9, bean.getPasswordanswer());
		pstmt.setString(10, bean.getPasswordquest());
		pstmt.setString(11, bean.getPassword());
		pstmt.setString(12, bean.getId());
		cnt = pstmt.executeUpdate();
		conn.commit();

		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}

		return cnt;
	}

	public int deleteData(String id)  throws Exception{
		// MemberDeleteController.doGet에서 사용합니다.
		// id 회원이 탈퇴합니다.
		int cnt = -1 ;
		String sql = "" ;
		Member bean = this.getDataByPrimaryKey(id);
		PreparedStatement pstmt = null;

		conn = super.getConnection();
		conn.setAutoCommit(false);

		// 회원 테이블의 id 행을 삭제
		sql = " delete from members where id = ? " ;
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		cnt = pstmt.executeUpdate();

		if(pstmt!=null) {pstmt.close();}

		conn.commit();
		if(conn!=null) {conn.close();}
		return cnt ;
	}
<<<<<<< HEAD
  
	/* [selectAll(pageinfo를 위함)] TopN 구문을 사용하여 페이징 처리된 게시물 목록을 반환합니다. */
=======
>>>>>>> origin/sup_new_branch02
	public List<Member> selectAll(Paging pageInfo) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select id, mtype, name, password, phone, birth, gender, nickname, address, profile, passwordanswer, passwordquest ";
		sql += " from ";
		sql += " (select id, mtype, name, password, phone, birth, gender, nickname, address, profile, passwordanswer, passwordquest, rank() over(order by id asc) ";
		sql += " from members) ";
		sql += " where ranking between ? and ? ";

		conn = super.getConnection();

		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());

		rs = pstmt.executeQuery();

		List<Member> lists = new ArrayList<Member>();

		while (rs.next()) {
			lists.add(getBeanData(rs));
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return lists;
	}
	/* [selectAll(pageinfo를 위함)] TopN 구문을 사용하여 페이징 처리된 게시물 목록을 반환합니다. */
	public List<Member> selectAll(PagingMember pageInfo) throws Exception {
		// <회원목록> 페이지에서 사용합니다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select id, mtype, name, password, phone, birth, gender, nickname, address, profile, passwordanswer, passwordquest ";
		sql += " from ";
		sql += " (select id, mtype, name, password, phone, birth, gender, nickname, address, profile, passwordanswer, passwordquest, rank() over(order by id asc) as ranking ";  
		sql += " from members" ;
		
		String mode = pageInfo.getMode();
		String keywordmtype = pageInfo.getKeywordmtype();
		String keywordgender = pageInfo.getKeywordgender();
		String keyword = pageInfo.getKeyword();
		// [ST] 검색옵션(mode)에 따른 sql문장 처리 : 반드시 GetTotalRecordCount()과 같게 맞춰주세요.
		if( mode == null || mode.equals("all") ) {
		}else if(mode.equals("mtype")){ // 회원유형으로 검색
			if(keywordmtype == null || keywordmtype.equals("all") ) {
			}else {
				sql += " where " + mode + " = '" + keywordmtype + "'" ;
			}
		}else if(mode.equals("id") || mode.equals("name") || mode.equals("nickname") 
				|| mode.equals("address")) { // 아이디,이름,닉네임,주소로 검색
			if(keyword == null || keyword.equals("") ) {
			}else {
				sql += " where " + mode + " like '%" + keyword + "%' " ;
			}
		}else if(mode.equals("gender")) { // 성별로 검색
			if(keywordgender == null || keywordgender.equals("all")) {
			}else {
				sql += " where " + mode + " = '" + keywordgender + "' " ;
			}
		}
		// [ED] 검색옵션(mode)에 따른 sql문장 처리 : 반드시 GetTotalRecordCount()과 같게 맞춰주세요.
		sql += " ) ";
		sql += " where ranking between ? and ? ";
		
		conn = super.getConnection();

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pageInfo.getKeywordmtype());
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());

		rs = pstmt.executeQuery();

		List<Member> lists = new ArrayList<Member>();
		

		while (rs.next()) {
			lists.add(getBeanData(rs));
		}
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return lists;
	}

	/* [GetTotalRecordCount] 테이블의 총 행개수를 구합니다. */
	public int GetTotalRecordCount() throws Exception {

		String sql = " select count(*) as cnt from members ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);

		rs = pstmt.executeQuery();

		int cnt = -1;

		if (rs.next()) {
			cnt = rs.getInt("cnt");
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return cnt;
	}
	
	public int GetTotalRecordCount(String mode, String keywordmtype, String keywordgender, String keyword) throws Exception {

		String sql = " select count(*) as cnt from members ";
		
		// [ST] 검색옵션(mode)에 따른 sql문장 처리 : 반드시 sellectAll()과 같게 맞춰주세요.
		if( mode == null || mode.equals("all") ) {
		}else if(mode.equals("mtype")){ // 회원유형으로 검색
			if(keywordmtype == null || keywordmtype.equals("all") ) {
			}else {
				sql += " where " + mode + " = '" + keywordmtype + "'" ;
			}
		}else if(mode.equals("id") || mode.equals("name") || mode.equals("nickname") 
				|| mode.equals("address")) { // 아이디,이름,닉네임,주소로 검색
			if(keyword == null || keyword.equals("") ) {
			}else {
				sql += " where " + mode + " like '%" + keyword + "%' " ;
			}
		}else if(mode.equals("gender")) { // 성별로 검색
			if(keywordgender == null || keywordgender.equals("all") ) {
			}else {
				sql += " where " + mode + " = '" + keywordgender + "' " ;
			}
		}
		// [ED] 검색옵션(mode)에 따른 sql문장 처리 : 반드시 sellectAll()과 같게 맞춰주세요.
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);

		rs = pstmt.executeQuery();

		int cnt = -1;

		if (rs.next()) {
			cnt = rs.getInt("cnt");
		}

		if (rs != null) {rs.close();}
		if (pstmt != null) {pstmt.close();}
		if (conn != null) {conn.close();}

		return cnt;
	}

	/* [getDataByPrimaryKey] id값으로만 member 조회 */
	public Member getDataByPrimaryKey(String id) throws Exception {
		// 기본 키 정보를 이용하여 Bean 객체를 구합니다.
		String sql = " select * from members ";
		sql += " where id = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);

		rs = pstmt.executeQuery();

		Member bean = null;

		if (rs.next()) {
			bean = this.getBeanData(rs);
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return bean;
	}
	
	
	
	/* [getDataByPk] id,pw값으로 member 조회 */
	public Member getDataByPk(String id, String password) throws Exception {		
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = " select * from members" ;
		sql += " where id = ? and password = ?" ;
		
		conn = super.getConnection() ; // 단계 02		
		pstmt = conn.prepareStatement(sql) ; // 단계 03
		
		pstmt.setString(1, id);
		pstmt.setString(2, password);

		rs = pstmt.executeQuery(); // 단계 04-01

		Member bean = null;
		if (rs.next()) { // 1건이 조회됨
			bean = getBeanData(rs);
		}

		// 단계 05
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return bean;
	}

	/* [findID] name, phone, birth값으로 member(id) 조회 */
	public Member findId(String name, String phone, String birth) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select * from members ";
		sql += " where name = ? and phone = ? and birth = ? ";

		conn = super.getConnection(); // 단계 02
		pstmt = conn.prepareStatement(sql); // 단계 03

		pstmt.setString(1, name);
		pstmt.setString(2, phone);
		pstmt.setString(3, birth);

		rs = pstmt.executeQuery(); // 단계 04-01

		Member bean = null;
		if (rs.next()) { // 1건이 조회됨
			bean = getBeanData(rs);
		}

		// 단계 05
		if (rs != null) {rs.close();}
		if (pstmt != null) {pstmt.close();}
		if (conn != null) {conn.close();}

		return bean;
	}

	/* #수정예정 [findPassword] name, passwordanswer, passwordquest값으로 member(password) 조회 */
	public Member findPassword(String name, String id, String passwordanswer, String passwordquest) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " select * from members ";
		sql += " where name = ? and id = ? and passwordanswer = ? and passwordquest = ? ";

		conn = super.getConnection(); // 단계 02
		pstmt = conn.prepareStatement(sql); // 단계 03

		pstmt.setString(1, name);
		pstmt.setString(2, id);
		pstmt.setString(3, passwordanswer);
		pstmt.setString(4, passwordquest);

		rs = pstmt.executeQuery(); // 단계 04-01

		Member bean = null;
		if (rs.next()) { // 1건이 조회됨
			bean = getBeanData(rs);
		}

		// 단계 05
		if (rs != null) {rs.close();}
		if (pstmt != null) {pstmt.close();}
		if (conn != null) {conn.close();}

		return bean;
	}

	/* [getBeanData] ResultSet의 데이터를 읽어서 Bean에 기록한 다음, 반환해 줍니다. */
	private Member getBeanData(ResultSet rs) throws Exception{
		Member bean = new Member()  ;				/* DB순으로 생성 */
													
		bean.setId(rs.getString("id"));				/* 멤버 아이디 */
		bean.setMtype(rs.getString("mtype")); 		/* admin / user / biz | 3종류 */
		bean.setName(rs.getString("name"));			/* 이름 */
		bean.setPassword(rs.getString("password"));	/* 패스워드 */
		bean.setPhone(rs.getString("phone"));		/* 휴대폰번호(String형식) */
		bean.setBirth(String.valueOf(rs.getDate("birth")));
		bean.setGender(rs.getString("gender")); /* male / female | 성별 */
		bean.setNickname(rs.getString("nickname")); /* 닉네임 */
		bean.setAddress(rs.getString("address")); /* 주소 */
		bean.setProfile(rs.getString("profile")); /* 프로필 이미지 */
		bean.setPasswordanswer(rs.getString("passwordanswer")); /* 패스워드 질문, 답변 */
		bean.setPasswordquest(rs.getString("passwordquest")); /* 패스워드 질문, 질문란 */

		return bean;
	}
	
	/* [InsertData] Member bean에 기록한 다음, 반환해 줍니다. */
	public int InsertData(Member bean) throws Exception {
		System.out.println("MD.InsertData : " + bean);

		// Bean 객체 정보를 이용하여 데이터 베이스에 추가합니다.
		String sql = " insert into members(id, mtype, name, password, phone, birth, gender, nickname, address, profile, passwordanswer, passwordquest) " ;
		sql += " values(					?,	   ?,	 ?,	       ?,	  ?,     ?,	     ?, 	   ?,	    ?,	     ?,              ?,             ?) " ;
		int cnt = -1 ;
		PreparedStatement pstmt = null ;
		
		conn = super.getConnection() ;
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getId());
		pstmt.setString(2, bean.getMtype());
		pstmt.setString(3, bean.getName());
		pstmt.setString(4, bean.getPassword());
		pstmt.setString(5, bean.getPhone());
		pstmt.setString(6, bean.getBirth());
		pstmt.setString(7, bean.getGender());
		pstmt.setString(8, bean.getNickname());
		pstmt.setString(9, bean.getAddress());
		pstmt.setString(10, bean.getProfile());
		pstmt.setString(11, bean.getPasswordanswer());
		pstmt.setString(12, bean.getPasswordquest());

		cnt = pstmt.executeUpdate() ;

		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt ;
	}
	
	
	/* [selectAll] member List객체에 담아서 '모든 정보' 조회 */
	public List<Member> selectAll() throws Exception{
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		String sql = " select * from members order by name asc";
		
		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql) ;
		
		rs = pstmt.executeQuery() ;
		
		List<Member> lists = new ArrayList<Member>();
		
		while(rs.next()) {
			lists.add(getBeanData(rs)) ;
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return lists;
	}

  /* [st] 아이디 랜덤 생성 */
	public static String RandomName() {
		List<String> firstname = Arrays.asList("빛나는", "눈부시게웃는", "떠들썩한", "소리치는", "들떠있는", "찾는", "발견하는", "차마시는", "햇볕에맞는",
				"비온뒤햇볕맞는", "미소짓는", "이야기하는", "드라마보는", "머리긁는", "빨간옷입는", "햇볕에맞아스노쿨하는", "역도하는", "배트맨처럼날아다니는", "고양이처럼뒤로구르는",
				"죽은척하는", "마법을부리는", "해변에서모래성짓는", "물고기처럼물속에서헤엄치는", "호랑이처럼울부짖는", "닌자처럼민첩하게움직이는", "반란을일으키는", "눈을감고별빛을만드는",
				"꿈속에서어른이되어보는", "시간을되돌리는", "우주를날아다니는", "미래를예지하는", "사물을생명을불어넣는", "초능력을가진", "모든언어를구사하는", "자신을복제하는",
				"사람들을환호하게하는", "물을순식간에얼려버리는", "불을뿜는", "바람을일으키는", "지진을일으키는", "물결을일으키는", "신비로운물건을찾는", "자유를향해달리는",
				"행운을불러오는", "떠돌이여행을떠나는", "정체불명의생명체와만나는", "무한한우주를탐험하는", "별빛을따라우주를여행하는", "전설속의동물과친구되는", "모든문제를해결하는",
				"다른차원으로여행하는", "소원을이뤄주는", "사랑을심어주는", "미래의예언자가되는", "시간을멈추는", "감정을읽어내는", "감정을조종하는", "불안을달래주는", "이상한꿈을꾸게하는",
				"물건을소환하는", "먹고마시지않고도힘이나는", "어둠을밝히는", "모든비밀을알아내는", "불을뿜어물을데우는", "악몽을떨쳐버리는", "행운의여우가되어모든일이행운으로끝나는",
				"얼굴에파이를던지는", "엉덩이를흔들어춤을추는", "고양이소리를내는", "머리에꽃을심는", "뒷걸음질치는", "물오리를흉내내는", "하늘에서비행기를내리는", "안구를굴리는",
				"코뼈를드럼으로만지는", "몸을나무로만드는", "전기충격을내리는", "종이비행기를날리는", "자기몸을고무로만들어튕기는", "오물을먹는척하는", "꽃에서물을빨아먹는",
				"코에서물을빨아마시는", "눈에서고드름을먹는", "배에서소리를내는", "뼈를통과시키는", "다리를머리에묶는", "칫솔로머리를감는", "발에치킨다리를신는", "머리에삼각모자를쓰는",
				"발뒤꿈치에램프를비추는", "코에서무지개를뿜는", "손목을비행기로만드는", "입에서물총을쏘는", "어깨를핸드백으로만드는", "머리에서미사일을발사하는", "코에서불을뿜는",
				"손톱을햄버거로만드는", "눈에서눈덩이를던지는", "머리에서토끼를뽑아내는", "입에서비행기를날리는", "코에서뱀을뿜는", "머리에전구를끼우는", "입에서파도를일으키는",
				"발에로켓을달아날아가는", "머리에서꽃을피우는", "손에서물을뿌려신난다는척하는", "발로불을뿜는", "머리에새를둥지에서꺼내는", "뒤에서나무를베는", "손으로구름을만지는",
				"발로지진을일으키는", "머리에서물을분출하는", "입에서고양이를빼내는", "손목에서빛을내는", "머리에새우를얹는", "손으로달을끌어내는");
		List<String> lastname = Arrays.asList("푸른하늘", "붉은장미", "바다소녀", "백조의호수", "우주탐험가", "컴퓨터프로그래머", "도서관사서", "음악가", "화가",
				"의사", "소방관", "경찰관", "선생님", "요리사", "배우", "기자", "디자이너", "운전사", "정비공", "요가강사", "의료진", "변호사", "농부", "작가",
				"건축가", "광부", "의무원", "물리학자", "천문학자", "환경보호자", "화학자", "생물학자", "수의사", "미용사", "영화감독", "프로게이머", "헬스트레이너",
				"코미디언", "화가", "서점주인", "사진작가", "판매원", "정치인", "과학자", "연예인매니저", "댄서", "음악프로듀서", "여행작가", "자동차디자이너", "커피마스터",
				"사람", "강아지", "고양이", "새", "말", "쥐", "코끼리", "원숭이", "곰", "돼지", "사슴", "소", "토끼", "거북이", "앵무새", "뱀", "곤충",
				"양", "기린", "돌고래", "상어", "치타", "랫서팬더", "해마", "팽귄", "사자", "호랑이", "다람쥐", "늑대", "고릴라", "하마",
				"카멜레온", "표범", "오리", "기러기", "하이에나", "물소", "하마스터", "캥거루", "악어", "악어거북", "오랑우탄", "아나콘다", "비버", "빅풋",
				"바다표범", "아프리카코끼리", "코뿔소", "골든리트리버");
		Collections.shuffle(firstname);
		Collections.shuffle(lastname);
		return firstname.get(0) + "_" + lastname.get(0);
	}

	public static void main(String[] args) { // 이름
		for (int i = 0; i < 500; i++) {
			if (i % 10 == 0) {
				System.err.println();
			}
			System.err.print(RandomName() + "  ");
		}
	}
	/* [ed] 아이디 랜덤 생성 */

	
	public List<Member> getSameProfileName(String profile) throws Exception {
		// 회원수정에서 삭제하려는 프로필이미지를 혹시 사용하는 사람이 있는지 명단을 반환합니다.
		// MyUtility.deleteOldProfileImageFile에서 사용합니다.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " select * from members ";
		sql += " where profile = ?";

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, profile);
		
		rs = pstmt.executeQuery();

		List<Member> lists = new ArrayList<Member>();
		

		while (rs.next()) {
			lists.add(getBeanData(rs));
		}

		if (rs != null) {rs.close();}
		if (pstmt != null) {pstmt.close();}
		if (conn != null) {conn.close();}

		return lists;
	}

	public int deleteAllMyStore(String id) throws Exception{
		// id회원이 가지고 있는 모든 가게들을 삭제한다.
		// 회원탈퇴 시, 회원수정 시(사업자->일반회원 변경) 사용합니다.
		// 성격 상 추후 StoreDao로 옮기는 게 나을 수도 있겠습니다.
		int cnt = -1 ;
		String sql = "" ;
		PreparedStatement pstmt = null;
 		
		conn = super.getConnection();
		conn.setAutoCommit(false);
		
		// STORE테이블에서 id가 회원id와 일치하는 가게들을 모두 지웁니다.
		sql = " delete from store where id = ? " ;
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		cnt = pstmt.executeUpdate();
		
		if(pstmt!=null) {pstmt.close();}
		
		conn.commit();
		if(conn!=null) {conn.close();}
		return cnt ;
	}

	public Map<String, String> getIdNickMap() throws Exception{
		// <투표 리스트> 페이지에서 닉네임을 표시하기 위해사용하는 메서드입니다.
		// 모든 회원의 아이디(키)와 닉네임(값)을 가지는 맵을 반환합니다.
		Map<String, String> map = new HashMap<String, String>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " select id, nickname from members ";

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			map.put(rs.getString("id"), rs.getString("nickname"));
		}

		if (rs != null) {rs.close();}
		if (pstmt != null) {pstmt.close();}
		if (conn != null) {conn.close();}
		
		return map;
	}

	public List<String> getIdListByNick(String keyword) throws Exception {
		// 투표 리스트에서 작성자 닉네임으로 검색할 때 사용하는 메서드입니다.
		// 검색할 닉네임 키워드를 입력받아, 닉네임에 키워드를 포함하는 아이디의 리스트를 반환합니다.
		List<String> lists_ID =  new ArrayList<String>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = " select id from members ";
		sql += " where nickname like '%" + keyword + "%' " ;

		conn = super.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			lists_ID.add(rs.getString("id"));
		}

		if (rs != null) {rs.close();}
		if (pstmt != null) {pstmt.close();}
		if (conn != null) {conn.close();}
		
		return lists_ID;
	}

}

