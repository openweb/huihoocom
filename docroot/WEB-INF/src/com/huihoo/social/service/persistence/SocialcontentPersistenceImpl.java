/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.huihoo.social.service.persistence;

import com.huihoo.social.NoSuchSocialcontentException;
import com.huihoo.social.model.Socialcontent;
import com.huihoo.social.model.impl.SocialcontentImpl;
import com.huihoo.social.model.impl.SocialcontentModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the socialcontent service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Baihua Huang
 * @see SocialcontentPersistence
 * @see SocialcontentUtil
 * @generated
 */
public class SocialcontentPersistenceImpl extends BasePersistenceImpl<Socialcontent>
	implements SocialcontentPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SocialcontentUtil} to access the socialcontent persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SocialcontentImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentModelImpl.FINDER_CACHE_ENABLED,
			SocialcontentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByuserId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentModelImpl.FINDER_CACHE_ENABLED,
			SocialcontentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByuserId", new String[] { Long.class.getName() },
			SocialcontentModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByuserId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentModelImpl.FINDER_CACHE_ENABLED,
			SocialcontentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBycompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentModelImpl.FINDER_CACHE_ENABLED,
			SocialcontentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBycompanyId", new String[] { Long.class.getName() },
			SocialcontentModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBycompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentModelImpl.FINDER_CACHE_ENABLED,
			SocialcontentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBygroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentModelImpl.FINDER_CACHE_ENABLED,
			SocialcontentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBygroupId", new String[] { Long.class.getName() },
			SocialcontentModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBygroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYIDANDGROUPID =
		new FinderPath(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentModelImpl.FINDER_CACHE_ENABLED,
			SocialcontentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBycompanyIdAndGroupId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYIDANDGROUPID =
		new FinderPath(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentModelImpl.FINDER_CACHE_ENABLED,
			SocialcontentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBycompanyIdAndGroupId",
			new String[] { Long.class.getName(), Long.class.getName() },
			SocialcontentModelImpl.COMPANYID_COLUMN_BITMASK |
			SocialcontentModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYIDANDGROUPID = new FinderPath(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBycompanyIdAndGroupId",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentModelImpl.FINDER_CACHE_ENABLED,
			SocialcontentImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentModelImpl.FINDER_CACHE_ENABLED,
			SocialcontentImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the socialcontent in the entity cache if it is enabled.
	 *
	 * @param socialcontent the socialcontent
	 */
	public void cacheResult(Socialcontent socialcontent) {
		EntityCacheUtil.putResult(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentImpl.class, socialcontent.getPrimaryKey(),
			socialcontent);

		socialcontent.resetOriginalValues();
	}

	/**
	 * Caches the socialcontents in the entity cache if it is enabled.
	 *
	 * @param socialcontents the socialcontents
	 */
	public void cacheResult(List<Socialcontent> socialcontents) {
		for (Socialcontent socialcontent : socialcontents) {
			if (EntityCacheUtil.getResult(
						SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
						SocialcontentImpl.class, socialcontent.getPrimaryKey()) == null) {
				cacheResult(socialcontent);
			}
			else {
				socialcontent.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all socialcontents.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SocialcontentImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SocialcontentImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the socialcontent.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Socialcontent socialcontent) {
		EntityCacheUtil.removeResult(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentImpl.class, socialcontent.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Socialcontent> socialcontents) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Socialcontent socialcontent : socialcontents) {
			EntityCacheUtil.removeResult(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
				SocialcontentImpl.class, socialcontent.getPrimaryKey());
		}
	}

	/**
	 * Creates a new socialcontent with the primary key. Does not add the socialcontent to the database.
	 *
	 * @param id the primary key for the new socialcontent
	 * @return the new socialcontent
	 */
	public Socialcontent create(long id) {
		Socialcontent socialcontent = new SocialcontentImpl();

		socialcontent.setNew(true);
		socialcontent.setPrimaryKey(id);

		return socialcontent;
	}

	/**
	 * Removes the socialcontent with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the socialcontent
	 * @return the socialcontent that was removed
	 * @throws com.huihoo.social.NoSuchSocialcontentException if a socialcontent with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Socialcontent remove(long id)
		throws NoSuchSocialcontentException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the socialcontent with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the socialcontent
	 * @return the socialcontent that was removed
	 * @throws com.huihoo.social.NoSuchSocialcontentException if a socialcontent with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Socialcontent remove(Serializable primaryKey)
		throws NoSuchSocialcontentException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Socialcontent socialcontent = (Socialcontent)session.get(SocialcontentImpl.class,
					primaryKey);

			if (socialcontent == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSocialcontentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(socialcontent);
		}
		catch (NoSuchSocialcontentException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Socialcontent removeImpl(Socialcontent socialcontent)
		throws SystemException {
		socialcontent = toUnwrappedModel(socialcontent);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, socialcontent);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(socialcontent);

		return socialcontent;
	}

	@Override
	public Socialcontent updateImpl(
		com.huihoo.social.model.Socialcontent socialcontent, boolean merge)
		throws SystemException {
		socialcontent = toUnwrappedModel(socialcontent);

		boolean isNew = socialcontent.isNew();

		SocialcontentModelImpl socialcontentModelImpl = (SocialcontentModelImpl)socialcontent;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, socialcontent, merge);

			socialcontent.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SocialcontentModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((socialcontentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(socialcontentModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(socialcontentModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((socialcontentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(socialcontentModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						Long.valueOf(socialcontentModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((socialcontentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(socialcontentModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] {
						Long.valueOf(socialcontentModelImpl.getGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((socialcontentModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYIDANDGROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(socialcontentModelImpl.getOriginalCompanyId()),
						Long.valueOf(socialcontentModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYIDANDGROUPID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYIDANDGROUPID,
					args);

				args = new Object[] {
						Long.valueOf(socialcontentModelImpl.getCompanyId()),
						Long.valueOf(socialcontentModelImpl.getGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYIDANDGROUPID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYIDANDGROUPID,
					args);
			}
		}

		EntityCacheUtil.putResult(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
			SocialcontentImpl.class, socialcontent.getPrimaryKey(),
			socialcontent);

		return socialcontent;
	}

	protected Socialcontent toUnwrappedModel(Socialcontent socialcontent) {
		if (socialcontent instanceof SocialcontentImpl) {
			return socialcontent;
		}

		SocialcontentImpl socialcontentImpl = new SocialcontentImpl();

		socialcontentImpl.setNew(socialcontent.isNew());
		socialcontentImpl.setPrimaryKey(socialcontent.getPrimaryKey());

		socialcontentImpl.setId(socialcontent.getId());
		socialcontentImpl.setUserId(socialcontent.getUserId());
		socialcontentImpl.setPortraitUrl(socialcontent.getPortraitUrl());
		socialcontentImpl.setScreenName(socialcontent.getScreenName());
		socialcontentImpl.setCompanyId(socialcontent.getCompanyId());
		socialcontentImpl.setGroupId(socialcontent.getGroupId());
		socialcontentImpl.setType(socialcontent.getType());
		socialcontentImpl.setContent(socialcontent.getContent());
		socialcontentImpl.setCreatedAt(socialcontent.getCreatedAt());

		return socialcontentImpl;
	}

	/**
	 * Returns the socialcontent with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the socialcontent
	 * @return the socialcontent
	 * @throws com.liferay.portal.NoSuchModelException if a socialcontent with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Socialcontent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the socialcontent with the primary key or throws a {@link com.huihoo.social.NoSuchSocialcontentException} if it could not be found.
	 *
	 * @param id the primary key of the socialcontent
	 * @return the socialcontent
	 * @throws com.huihoo.social.NoSuchSocialcontentException if a socialcontent with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Socialcontent findByPrimaryKey(long id)
		throws NoSuchSocialcontentException, SystemException {
		Socialcontent socialcontent = fetchByPrimaryKey(id);

		if (socialcontent == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchSocialcontentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return socialcontent;
	}

	/**
	 * Returns the socialcontent with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the socialcontent
	 * @return the socialcontent, or <code>null</code> if a socialcontent with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Socialcontent fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the socialcontent with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the socialcontent
	 * @return the socialcontent, or <code>null</code> if a socialcontent with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Socialcontent fetchByPrimaryKey(long id) throws SystemException {
		Socialcontent socialcontent = (Socialcontent)EntityCacheUtil.getResult(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
				SocialcontentImpl.class, id);

		if (socialcontent == _nullSocialcontent) {
			return null;
		}

		if (socialcontent == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				socialcontent = (Socialcontent)session.get(SocialcontentImpl.class,
						Long.valueOf(id));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (socialcontent != null) {
					cacheResult(socialcontent);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(SocialcontentModelImpl.ENTITY_CACHE_ENABLED,
						SocialcontentImpl.class, id, _nullSocialcontent);
				}

				closeSession(session);
			}
		}

		return socialcontent;
	}

	/**
	 * Returns all the socialcontents where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public List<Socialcontent> findByuserId(long userId)
		throws SystemException {
		return findByuserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the socialcontents where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of socialcontents
	 * @param end the upper bound of the range of socialcontents (not inclusive)
	 * @return the range of matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public List<Socialcontent> findByuserId(long userId, int start, int end)
		throws SystemException {
		return findByuserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the socialcontents where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of socialcontents
	 * @param end the upper bound of the range of socialcontents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public List<Socialcontent> findByuserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<Socialcontent> list = (List<Socialcontent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SOCIALCONTENT_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SocialcontentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				list = (List<Socialcontent>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first socialcontent in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching socialcontent
	 * @throws com.huihoo.social.NoSuchSocialcontentException if a matching socialcontent could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Socialcontent findByuserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSocialcontentException, SystemException {
		List<Socialcontent> list = findByuserId(userId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSocialcontentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last socialcontent in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching socialcontent
	 * @throws com.huihoo.social.NoSuchSocialcontentException if a matching socialcontent could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Socialcontent findByuserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSocialcontentException, SystemException {
		int count = countByuserId(userId);

		List<Socialcontent> list = findByuserId(userId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSocialcontentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the socialcontents before and after the current socialcontent in the ordered set where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param id the primary key of the current socialcontent
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next socialcontent
	 * @throws com.huihoo.social.NoSuchSocialcontentException if a socialcontent with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Socialcontent[] findByuserId_PrevAndNext(long id, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchSocialcontentException, SystemException {
		Socialcontent socialcontent = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Socialcontent[] array = new SocialcontentImpl[3];

			array[0] = getByuserId_PrevAndNext(session, socialcontent, userId,
					orderByComparator, true);

			array[1] = socialcontent;

			array[2] = getByuserId_PrevAndNext(session, socialcontent, userId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Socialcontent getByuserId_PrevAndNext(Session session,
		Socialcontent socialcontent, long userId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALCONTENT_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(SocialcontentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialcontent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Socialcontent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the socialcontents where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public List<Socialcontent> findBycompanyId(long companyId)
		throws SystemException {
		return findBycompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the socialcontents where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of socialcontents
	 * @param end the upper bound of the range of socialcontents (not inclusive)
	 * @return the range of matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public List<Socialcontent> findBycompanyId(long companyId, int start,
		int end) throws SystemException {
		return findBycompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the socialcontents where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of socialcontents
	 * @param end the upper bound of the range of socialcontents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public List<Socialcontent> findBycompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<Socialcontent> list = (List<Socialcontent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SOCIALCONTENT_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SocialcontentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<Socialcontent>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first socialcontent in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching socialcontent
	 * @throws com.huihoo.social.NoSuchSocialcontentException if a matching socialcontent could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Socialcontent findBycompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchSocialcontentException, SystemException {
		List<Socialcontent> list = findBycompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSocialcontentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last socialcontent in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching socialcontent
	 * @throws com.huihoo.social.NoSuchSocialcontentException if a matching socialcontent could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Socialcontent findBycompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchSocialcontentException, SystemException {
		int count = countBycompanyId(companyId);

		List<Socialcontent> list = findBycompanyId(companyId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSocialcontentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the socialcontents before and after the current socialcontent in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param id the primary key of the current socialcontent
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next socialcontent
	 * @throws com.huihoo.social.NoSuchSocialcontentException if a socialcontent with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Socialcontent[] findBycompanyId_PrevAndNext(long id, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchSocialcontentException, SystemException {
		Socialcontent socialcontent = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Socialcontent[] array = new SocialcontentImpl[3];

			array[0] = getBycompanyId_PrevAndNext(session, socialcontent,
					companyId, orderByComparator, true);

			array[1] = socialcontent;

			array[2] = getBycompanyId_PrevAndNext(session, socialcontent,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Socialcontent getBycompanyId_PrevAndNext(Session session,
		Socialcontent socialcontent, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALCONTENT_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(SocialcontentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialcontent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Socialcontent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the socialcontents where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public List<Socialcontent> findBygroupId(long groupId)
		throws SystemException {
		return findBygroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the socialcontents where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of socialcontents
	 * @param end the upper bound of the range of socialcontents (not inclusive)
	 * @return the range of matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public List<Socialcontent> findBygroupId(long groupId, int start, int end)
		throws SystemException {
		return findBygroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the socialcontents where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of socialcontents
	 * @param end the upper bound of the range of socialcontents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public List<Socialcontent> findBygroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<Socialcontent> list = (List<Socialcontent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SOCIALCONTENT_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SocialcontentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				list = (List<Socialcontent>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first socialcontent in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching socialcontent
	 * @throws com.huihoo.social.NoSuchSocialcontentException if a matching socialcontent could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Socialcontent findBygroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSocialcontentException, SystemException {
		List<Socialcontent> list = findBygroupId(groupId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSocialcontentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last socialcontent in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching socialcontent
	 * @throws com.huihoo.social.NoSuchSocialcontentException if a matching socialcontent could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Socialcontent findBygroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSocialcontentException, SystemException {
		int count = countBygroupId(groupId);

		List<Socialcontent> list = findBygroupId(groupId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSocialcontentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the socialcontents before and after the current socialcontent in the ordered set where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param id the primary key of the current socialcontent
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next socialcontent
	 * @throws com.huihoo.social.NoSuchSocialcontentException if a socialcontent with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Socialcontent[] findBygroupId_PrevAndNext(long id, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSocialcontentException, SystemException {
		Socialcontent socialcontent = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Socialcontent[] array = new SocialcontentImpl[3];

			array[0] = getBygroupId_PrevAndNext(session, socialcontent,
					groupId, orderByComparator, true);

			array[1] = socialcontent;

			array[2] = getBygroupId_PrevAndNext(session, socialcontent,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Socialcontent getBygroupId_PrevAndNext(Session session,
		Socialcontent socialcontent, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALCONTENT_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(SocialcontentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialcontent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Socialcontent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the socialcontents where companyId = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @return the matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public List<Socialcontent> findBycompanyIdAndGroupId(long companyId,
		long groupId) throws SystemException {
		return findBycompanyIdAndGroupId(companyId, groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the socialcontents where companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of socialcontents
	 * @param end the upper bound of the range of socialcontents (not inclusive)
	 * @return the range of matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public List<Socialcontent> findBycompanyIdAndGroupId(long companyId,
		long groupId, int start, int end) throws SystemException {
		return findBycompanyIdAndGroupId(companyId, groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the socialcontents where companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param start the lower bound of the range of socialcontents
	 * @param end the upper bound of the range of socialcontents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public List<Socialcontent> findBycompanyIdAndGroupId(long companyId,
		long groupId, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYIDANDGROUPID;
			finderArgs = new Object[] { companyId, groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYIDANDGROUPID;
			finderArgs = new Object[] {
					companyId, groupId,
					
					start, end, orderByComparator
				};
		}

		List<Socialcontent> list = (List<Socialcontent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_SOCIALCONTENT_WHERE);

			query.append(_FINDER_COLUMN_COMPANYIDANDGROUPID_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYIDANDGROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(SocialcontentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				list = (List<Socialcontent>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first socialcontent in the ordered set where companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching socialcontent
	 * @throws com.huihoo.social.NoSuchSocialcontentException if a matching socialcontent could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Socialcontent findBycompanyIdAndGroupId_First(long companyId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchSocialcontentException, SystemException {
		List<Socialcontent> list = findBycompanyIdAndGroupId(companyId,
				groupId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSocialcontentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last socialcontent in the ordered set where companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching socialcontent
	 * @throws com.huihoo.social.NoSuchSocialcontentException if a matching socialcontent could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Socialcontent findBycompanyIdAndGroupId_Last(long companyId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchSocialcontentException, SystemException {
		int count = countBycompanyIdAndGroupId(companyId, groupId);

		List<Socialcontent> list = findBycompanyIdAndGroupId(companyId,
				groupId, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSocialcontentException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the socialcontents before and after the current socialcontent in the ordered set where companyId = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param id the primary key of the current socialcontent
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next socialcontent
	 * @throws com.huihoo.social.NoSuchSocialcontentException if a socialcontent with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Socialcontent[] findBycompanyIdAndGroupId_PrevAndNext(long id,
		long companyId, long groupId, OrderByComparator orderByComparator)
		throws NoSuchSocialcontentException, SystemException {
		Socialcontent socialcontent = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			Socialcontent[] array = new SocialcontentImpl[3];

			array[0] = getBycompanyIdAndGroupId_PrevAndNext(session,
					socialcontent, companyId, groupId, orderByComparator, true);

			array[1] = socialcontent;

			array[2] = getBycompanyIdAndGroupId_PrevAndNext(session,
					socialcontent, companyId, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Socialcontent getBycompanyIdAndGroupId_PrevAndNext(
		Session session, Socialcontent socialcontent, long companyId,
		long groupId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SOCIALCONTENT_WHERE);

		query.append(_FINDER_COLUMN_COMPANYIDANDGROUPID_COMPANYID_2);

		query.append(_FINDER_COLUMN_COMPANYIDANDGROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(SocialcontentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(socialcontent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Socialcontent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the socialcontents.
	 *
	 * @return the socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public List<Socialcontent> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the socialcontents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of socialcontents
	 * @param end the upper bound of the range of socialcontents (not inclusive)
	 * @return the range of socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public List<Socialcontent> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the socialcontents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of socialcontents
	 * @param end the upper bound of the range of socialcontents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public List<Socialcontent> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Socialcontent> list = (List<Socialcontent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SOCIALCONTENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SOCIALCONTENT.concat(SocialcontentModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Socialcontent>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Socialcontent>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the socialcontents where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByuserId(long userId) throws SystemException {
		for (Socialcontent socialcontent : findByuserId(userId)) {
			remove(socialcontent);
		}
	}

	/**
	 * Removes all the socialcontents where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBycompanyId(long companyId) throws SystemException {
		for (Socialcontent socialcontent : findBycompanyId(companyId)) {
			remove(socialcontent);
		}
	}

	/**
	 * Removes all the socialcontents where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBygroupId(long groupId) throws SystemException {
		for (Socialcontent socialcontent : findBygroupId(groupId)) {
			remove(socialcontent);
		}
	}

	/**
	 * Removes all the socialcontents where companyId = &#63; and groupId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBycompanyIdAndGroupId(long companyId, long groupId)
		throws SystemException {
		for (Socialcontent socialcontent : findBycompanyIdAndGroupId(
				companyId, groupId)) {
			remove(socialcontent);
		}
	}

	/**
	 * Removes all the socialcontents from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Socialcontent socialcontent : findAll()) {
			remove(socialcontent);
		}
	}

	/**
	 * Returns the number of socialcontents where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public int countByuserId(long userId) throws SystemException {
		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SOCIALCONTENT_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of socialcontents where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public int countBycompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SOCIALCONTENT_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of socialcontents where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public int countBygroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SOCIALCONTENT_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of socialcontents where companyId = &#63; and groupId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @return the number of matching socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public int countBycompanyIdAndGroupId(long companyId, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYIDANDGROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SOCIALCONTENT_WHERE);

			query.append(_FINDER_COLUMN_COMPANYIDANDGROUPID_COMPANYID_2);

			query.append(_FINDER_COLUMN_COMPANYIDANDGROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYIDANDGROUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of socialcontents.
	 *
	 * @return the number of socialcontents
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SOCIALCONTENT);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the socialcontent persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.huihoo.social.model.Socialcontent")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Socialcontent>> listenersList = new ArrayList<ModelListener<Socialcontent>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Socialcontent>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(SocialcontentImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = SocialcontentPersistence.class)
	protected SocialcontentPersistence socialcontentPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_SOCIALCONTENT = "SELECT socialcontent FROM Socialcontent socialcontent";
	private static final String _SQL_SELECT_SOCIALCONTENT_WHERE = "SELECT socialcontent FROM Socialcontent socialcontent WHERE ";
	private static final String _SQL_COUNT_SOCIALCONTENT = "SELECT COUNT(socialcontent) FROM Socialcontent socialcontent";
	private static final String _SQL_COUNT_SOCIALCONTENT_WHERE = "SELECT COUNT(socialcontent) FROM Socialcontent socialcontent WHERE ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "socialcontent.userId = ?";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "socialcontent.companyId = ?";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "socialcontent.groupId = ?";
	private static final String _FINDER_COLUMN_COMPANYIDANDGROUPID_COMPANYID_2 = "socialcontent.companyId = ? AND ";
	private static final String _FINDER_COLUMN_COMPANYIDANDGROUPID_GROUPID_2 = "socialcontent.groupId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "socialcontent.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Socialcontent exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Socialcontent exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SocialcontentPersistenceImpl.class);
	private static Socialcontent _nullSocialcontent = new SocialcontentImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Socialcontent> toCacheModel() {
				return _nullSocialcontentCacheModel;
			}
		};

	private static CacheModel<Socialcontent> _nullSocialcontentCacheModel = new CacheModel<Socialcontent>() {
			public Socialcontent toEntityModel() {
				return _nullSocialcontent;
			}
		};
}