package service;

import java.util.List;

import domain.ProductVO;
import repository.DAO;
import repository.ProductDAO;

public class ProductService implements Service {

	private DAO dao;
	
	public ProductService() {
		dao = new ProductDAO();
	}
	
	@Override
	public int register(ProductVO pvo) {
		// jsp에서 받은 객체 pvo를 가지고 db에 넣어달라고 요청 dao에게...
		// dao에게 연결할 메서드는 db 구문으로 하는 것이 일반적임.
		System.out.println(">>> register service 진입!!");
		return dao.insert(pvo);
	}

	@Override
	public List<ProductVO> list() {
		System.out.println(">>> list service 진입!!");
		return dao.selectList();
	}

	@Override
	public ProductVO detail(int pno) {
		System.out.println(">>> detail service 진입!!");
		return dao.selectOne(pno);
	}

	@Override
	public int edit(ProductVO pvo2) {
		System.out.println(">>> edit service 진입!!");
		return dao.update(pvo2);
	}

	@Override
	public int remove(int pno) {
		System.out.println(">>> remove service 진입!!");
		return dao.delete(pno);
	}

}
