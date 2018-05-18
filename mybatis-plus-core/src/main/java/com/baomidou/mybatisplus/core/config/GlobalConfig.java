/*
 * Copyright (c) 2011-2014, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.mybatisplus.core.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import lombok.Data;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * <p>
 * Mybatis 全局缓存
 * </p>
 *
 * @author Caratacus
 * @since 2016-12-06
 */
@Data
public class GlobalConfig implements Serializable {

    /**
     * 是否刷新 mapper
     */
    private boolean refresh = false;
    /**
     * 缓存 Sql 解析初始化
     */
    private boolean sqlParserCache = false;
    /**
     * 数据库相关配置
     */
    private DbConfig dbConfig;
    /**
     * SQL注入器
     */
    private ISqlInjector sqlInjector;
    /**
     * 单例重用SqlSession
     */
    private SqlSession sqlSession;
    /**
     * 缓存当前Configuration的SqlSessionFactory
     */
    private SqlSessionFactory sqlSessionFactory;
    /**
     * 缓存已注入CRUD的Mapper信息
     */
    private Set<String> mapperRegistryCache = new ConcurrentSkipListSet<>();
    /**
     * 元对象字段填充控制器
     */
    private MetaObjectHandler metaObjectHandler = MetaObjectHandler.getInstance();

    /**
     * <p>
     * 标记全局设置 (统一所有入口)
     * </p>
     *
     * @param sqlSessionFactory
     * @return
     */
    public SqlSessionFactory signGlobalConfig(SqlSessionFactory sqlSessionFactory) {
        if (null != sqlSessionFactory) {
            GlobalConfigUtils.setGlobalConfig(sqlSessionFactory.getConfiguration(), this);
        }
        return sqlSessionFactory;
    }
}
