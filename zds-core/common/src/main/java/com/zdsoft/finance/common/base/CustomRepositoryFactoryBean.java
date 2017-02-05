package com.zdsoft.finance.common.base;

import com.zdsoft.finance.common.base.impl.CustomRepositoryImpl;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * 创建自定义factroryBean代替默认
 * @author LiaoGuoWei
 * @create 2016-10-27 11:24
 **/
public class CustomRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

    @SuppressWarnings("rawtypes")
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new AbstractRepositoryFactory(entityManager);
    }

    private static class AbstractRepositoryFactory<T extends BaseEntity, I extends Serializable> extends JpaRepositoryFactory {

        private final EntityManager entityManager;

        public AbstractRepositoryFactory(EntityManager em) {
            super(em);
            this.entityManager = em;
        }

        @SuppressWarnings({ "unchecked", "unused" })
        protected Object getTargetRepository(RepositoryMetadata metadata) {
            return new CustomRepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), entityManager);
        }

        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return CustomRepositoryImpl.class;
        }
    }
}
