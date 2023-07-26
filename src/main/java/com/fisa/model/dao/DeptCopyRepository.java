/* CrudRepository
 * 	- 별도의 메소드 재정의 없이 crud 기능의 메소드 호출만으로 실행 가능
 *  - 단 제약 조건(개발시 주의 사항 rule)
 *  - CrudRepository<T, ID>
 * 		T : 사용하고자 하는 entity type, table과 매핑된 entity 타입이어야함
 * 		ID : 해당 entity의 pk, 즉 ID 변수 타입
 */
package com.fisa.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fisa.model.domain.entity.DeptCopy;

@Repository
public interface DeptCopyRepository extends CrudRepository<DeptCopy, Integer>{

}
