package com.ljq.demo.springboot.mybatisplus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljq.demo.springboot.mybatisplus.common.api.ApiResult;
import com.ljq.demo.springboot.mybatisplus.common.util.PageUtil;
import com.ljq.demo.springboot.mybatisplus.common.util.QueryUtil;
import com.ljq.demo.springboot.mybatisplus.dao.ClassDao;
import com.ljq.demo.springboot.mybatisplus.model.entity.ClassEntity;
import com.ljq.demo.springboot.mybatisplus.model.entity.ClassTeacherEntity;
import com.ljq.demo.springboot.mybatisplus.model.param.clazz.*;
import com.ljq.demo.springboot.mybatisplus.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

/**
 * 班级业务层具体实现类
 *
 * @author junqiang.lu
 * @date 2020-10-14 16:18:38
 */
@Service("classService")
@Transactional(rollbackFor = {Exception.class})
@Slf4j
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassDao classDao;

	/**
	 * 保存(单条)
	 *
	 * @param classSaveParam
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult save(ClassSaveParam classSaveParam) {
		// 请求参数获取
		ClassEntity classParam = new ClassEntity();
		BeanUtil.copyProperties(classSaveParam,classParam,CopyOptions.create()
				.setIgnoreNullValue(true).setIgnoreError(true));
		// 保存
		// mybatis-plus
		classDao.insert(classParam);
		log.debug("{}", classParam);
		// local
		classDao.save(classParam);
		log.debug("{}", classParam);

		return ApiResult.success();
	}

	/**
	 * 班级教师批量保存
	 *
	 * @param classTeacherSaveBatchParam
	 * @return
	 */
	@Override
	public ApiResult saveClassTeacherBatch(ClassTeacherSaveBatchParam classTeacherSaveBatchParam) {
		ClassTeacherEntity classTeacherParam;
		List<ClassTeacherEntity> classTeacherListParam = new ArrayList<>(16);
		for (int i = 0; i < classTeacherSaveBatchParam.getTeacherIds().length; i++) {
			classTeacherParam = new ClassTeacherEntity();
			classTeacherParam.setClassId(classTeacherSaveBatchParam.getClassId());
			classTeacherParam.setTeacherId(classTeacherSaveBatchParam.getTeacherIds()[i]);
			classTeacherListParam.add(classTeacherParam);
		}
		// 批量保存
		classDao.saveClassTeacherBatch(classTeacherListParam);
		return ApiResult.success();
	}

	/**
	 * 查询详情(单条)
	 *
	 * @param classInfoParam
	 * @return
	 */
	@Override
	public ApiResult info(ClassInfoParam classInfoParam) {
		// 查询
		// mybatis-plus
		ClassEntity classDBPlus = classDao.selectById(classInfoParam.getId());
		log.debug("mybatis-plus result: {}", classDBPlus);
		// local
		ClassEntity classDBLocal = classDao.queryOne(classInfoParam.getId());
		log.debug("local result: {}", classDBLocal);

		return ApiResult.success(classDBLocal);
	}

	/**
	 * 查询班级集合
	 *
	 * @param collectionParam
	 * @return
	 */
	@Override
	public ApiResult collection(ClassCollectionParam collectionParam) {
		LambdaQueryWrapper<ClassEntity> queryWrapper = new QueryWrapper<ClassEntity>().lambda();
		/// or 用法
//		Arrays.asList(collectionParam.getIds()).stream().forEach(id -> {
//			queryWrapper.or(clazz -> clazz.eq(ClassEntity::getId,id));
//		});

		// in 用法(推荐)
		queryWrapper.in(ClassEntity::getId, collectionParam.getIds());
		List<ClassEntity> classDBList = classDao.selectList(queryWrapper);
		return ApiResult.success(classDBList);
	}

	/**
	 * 查询列表
	 *
	 * @param classListParam
	 * @return
	 */
	@Override
	public ApiResult list(ClassListParam classListParam) throws SQLException {
		// mybatis-plus
		IPage page = new Page(classListParam.getCurrentPage(), classListParam.getPageSize());
		LambdaQueryWrapper<ClassEntity> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.like(StrUtil.isNotBlank(classListParam.getName()), ClassEntity::getName, classListParam.getName())
				.notIn(ArrayUtil.isNotEmpty(classListParam.getExcludeIds()), ClassEntity::getId,
						classListParam.getExcludeIds())
				.orderBy(true, classListParam.getDirection().equalsIgnoreCase(SqlKeyword.ASC.name()),
						ClassEntity::getId);
		page = classDao.selectPage(page, queryWrapper);
		log.debug("mybatis-plus result: total:{},records size:{}, records: {}", page.getTotal(),
				page.getRecords().size(), page.getRecords());

		// local
		QueryUtil queryMap = new QueryUtil(BeanUtil.beanToMap(classListParam));
		ClassEntity classParam = new ClassEntity();
		BeanUtil.copyProperties(classListParam, classParam,CopyOptions.create()
				.setIgnoreNullValue(true).setIgnoreError(true));
		int count = classDao.queryCount(queryMap);
		PageUtil pageUtil = new PageUtil(Collections.emptyList(), count, classListParam.getPageSize(),
				classListParam.getCurrentPage());
		// 分页查询
		if (count > 0) {
			List<ClassEntity> classDBList = classDao.queryList(queryMap);
			pageUtil.setList(classDBList);
		}
		log.debug("local result: records size:{}, all: {}", pageUtil.getList().size(), pageUtil);

		// mybatis-plus & local
		IPage page2 = new Page(classListParam.getCurrentPage(), classListParam.getPageSize());
		page2 = classDao.queryPage(page2, queryMap);
		log.debug("mybatis-plus & local result: total:{},records size:{},records: {}",page2.getTotal(),
				page2.getRecords().size(),page2.getRecords());

		// mybatis-plus $ local 2(一对多分页测试)
		IPage page3 = new Page(classListParam.getCurrentPage(), classListParam.getPageSize());
		page3 = classDao.queryPage2(page3, queryMap);
		log.debug("mybatis-plus & local(一对多分页测试),total: {},records size:{},records: {}", page3.getTotal(),
				page3.getRecords().size(), page3.getRecords());

		// local 2(一对多分页测试)
		PageUtil pageUtil2 = new PageUtil(Collections.emptyList(), count, classListParam.getPageSize(),
				classListParam.getCurrentPage());
		if (count > 0) {
			List<ClassEntity> classDBList = classDao.queryList2(queryMap);
			pageUtil2.setList(classDBList);
		}
		log.debug("local result: records size:{}, all: {}", pageUtil.getList().size(), pageUtil);

		return ApiResult.success(pageUtil);
	}

	/**
	 * 更新(单条)
	 *
	 * @param classUpdateParam
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult update(ClassUpdateParam classUpdateParam) {
		// 请求参数获取
		ClassEntity classParam = new ClassEntity();
		BeanUtil.copyProperties(classUpdateParam, classParam, CopyOptions.create().ignoreNullValue().ignoreError());
		// 更新对象
		// mybatis-plus
		classDao.updateById(classParam);
		// local
		classDao.updateOne(classParam);
		return ApiResult.success();
	}

	/**
	 * 删除(单条)
	 *
	 * @param classDeleteParam
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public ApiResult delete(ClassDeleteParam classDeleteParam) {
		// mybatis-plus
		classDao.deleteById(classDeleteParam.getId());
		// local
		classDao.deleteOne(classDeleteParam.getId());
		return ApiResult.success();
	}

	/**
	 * 批量删除
	 *
	 * @param deleteBatchParam
	 * @return
	 */
	@Override
	public ApiResult deleteBatch(ClassDeleteBatchParam deleteBatchParam) {
		// mybatis-plus
		classDao.deleteBatchIds(Arrays.asList(deleteBatchParam.getIds()));
		// local
		classDao.deleteBatch(deleteBatchParam.getIds());
		return ApiResult.success();
	}


}
