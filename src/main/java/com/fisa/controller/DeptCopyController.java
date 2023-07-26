package com.fisa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisa.exception.DeptNotFoundException;
import com.fisa.model.dao.DeptCopyRepository;
import com.fisa.model.domain.entity.DeptCopy;

@RestController
public class DeptCopyController {

	@Autowired //타입에 맞는 spring bean 등록
	private DeptCopyRepository dao;
	
	//데이터 저장 - S save(S entity)
	/* 참고 :
	 *  	1. entity와 dto는 구분 필요
	 *  	2. dto : view, model, controller간에 전송되는 ㄴ데이터 표현용 객체
	 *  	3. entity : table과 밀착
	 *  private int deptno; 
		private String dname;
		private String loc;
	 */
	
	@PostMapping("insert")
	public DeptCopy insertDept(DeptCopy datas) {
		System.out.println("============" + datas);
		return dao.save(datas); //db에 저장
	}

	@GetMapping("deptone")
	public DeptCopy getDept(int deptno) throws Exception {
		System.out.println(dao.findById(deptno).get()); 
		Optional<DeptCopy> dept = dao.findById(deptno);
		System.out.println(dept);  
		dept.orElseThrow(Exception::new);  
		
		return dept.get();	
	}
	

	
	@GetMapping("deptall") 
	public Iterable<DeptCopy> getDeptAll(){
		//System.out.println(dao.findAll());		
		return dao.findAll();
	}
	
	
	@GetMapping("deptdelete")   // 그냥 url 매핑 
	public String deleteDept(int deptno) throws DeptNotFoundException {
		dao.findById(deptno).orElseThrow(DeptNotFoundException::new);//로직 중지
		dao.deleteById(deptno);//존재할 경우실행되는 라인, 따로 객체를 저장하지는 않음 
		return "delete 성공";
	}
	
	@ExceptionHandler(DeptNotFoundException.class)
	public String exHandler(DeptNotFoundException e) {
		e.printStackTrace();		
		return "해당 부서 번호는 존재하지 않습니다.";
	}
		
	@ExceptionHandler
	public String exHandler(Exception e) {
		e.printStackTrace();		
		return "요청시 입력 데이터 재 확인 부탁합니다";
	}
	
	
}