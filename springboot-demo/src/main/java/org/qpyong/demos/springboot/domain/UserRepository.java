package org.qpyong.demos.springboot.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * 不用实现该接口，Spring会自动生成代理实现类。
 * <p>
 * 这里也可以继承{@link org.springframework.data.jpa.repository.JpaRepository}
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
