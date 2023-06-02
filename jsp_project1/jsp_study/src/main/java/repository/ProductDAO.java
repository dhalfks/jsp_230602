package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.Query;

import domain.ProductVO;
import orm.DatabaseConnector;

public class ProductDAO implements DAO {
	//DB Connector
	private Connection conn;
	//DB 연결후 sql 전송 객체
	private PreparedStatement pst;
	//쿼리문 작성 변수
	private String qurey="";
	
	public ProductDAO() {
		//싱글톤 방식의 객체 생성방식
		DatabaseConnector dbc = DatabaseConnector.getInstance();
		conn = dbc.getConnection();
	}

	@Override
	public int insert(ProductVO pvo) {
		System.out.println(">>> DAO 접근완료");
		qurey="insert into product(pname,price,madeby) values(?,?,?)";
		try {
			pst = conn.prepareStatement(qurey);
			pst.setString(1, pvo.getPname());
			pst.setInt(2, pvo.getPrice());
			pst.setString(3, pvo.getMadeby());
			//executeUpdate : insert, update, delete 
			//executeQuery : select에서 사용
			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<ProductVO> selectList() {
		System.out.println(">>> DAO 접근완료");
		qurey = "select * from product order by pno desc";
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		try {
			pst = conn.prepareStatement(qurey);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				list.add(new ProductVO(rs.getInt("pno"), 
						rs.getString("pname"), 
						rs.getString("regdate")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductVO selectOne(int pno) {
		System.out.println(">>> DAO 접근완료");
		qurey="select * from product where pno=?";
		try {
			pst = conn.prepareStatement(qurey);
			pst.setInt(1, pno);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				return new ProductVO(rs.getInt("pno"), 
						rs.getString("pname"), 
						rs.getInt("price"), 
						rs.getString("regdate"), 
						rs.getString("madeby"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(ProductVO pvo2) {
		System.out.println(">>> DAO 접근완료");
		qurey="update product set pname=?, price=?, madeby=?, regdate=now() where pno=?";
		try {
			pst = conn.prepareStatement(qurey);
			pst.setString(1, pvo2.getPname());
			pst.setInt(2, pvo2.getPrice());
			pst.setString(3, pvo2.getMadeby());
			pst.setInt(4, pvo2.getPno());
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int pno) {
		System.out.println("DAO 진입성공~!!");
		qurey="delete from product where pno = ?";
		try {
			pst = conn.prepareStatement(qurey);
			pst.setInt(1, pno);
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}

}
