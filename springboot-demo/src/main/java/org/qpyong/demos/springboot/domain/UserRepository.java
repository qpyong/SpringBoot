package org.qpyong.demos.springboot.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <ul>
 * <li>不用实现该接口，Spring会自动生成代理实现类。</li>
 * <li>该接口也无需标注<code>@Repository</code>注解</li>
 *
 * </ul>
 * <p>
 * 这里也可以继承{@link org.springframework.data.jpa.repository.JpaRepository}
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User queryByUserName(String username);

}
