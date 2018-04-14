/**
 * 
 */
package com.test.hibernate.xml.pojo.interceptor;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.CallbackException;
import org.hibernate.EntityMode;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

/**
 * @author edinjos
 *
 */
public class LoggerInterceptor implements Interceptor {

	@Override
	public boolean onLoad(Object paramObject, Serializable paramSerializable,
			Object[] paramArrayOfObject, String[] paramArrayOfString,
			Type[] paramArrayOfType) throws CallbackException {

		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName()
				+ LoggerInterceptor.class.getName() + " onLoad " + paramObject);
		return false;
	}

	@Override
	public boolean onFlushDirty(Object paramObject,
			Serializable paramSerializable, Object[] paramArrayOfObject1,
			Object[] paramArrayOfObject2, String[] paramArrayOfString,
			Type[] paramArrayOfType) throws CallbackException {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName() + " onFlushDirty "
				+ paramObject);
		return false;
	}

	@Override
	public boolean onSave(Object paramObject, Serializable paramSerializable,
			Object[] paramArrayOfObject, String[] paramArrayOfString,
			Type[] paramArrayOfType) throws CallbackException {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName() + " onSave " + paramObject);
		return false;
	}

	@Override
	public void onDelete(Object paramObject, Serializable paramSerializable,
			Object[] paramArrayOfObject, String[] paramArrayOfString,
			Type[] paramArrayOfType) throws CallbackException {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName() + " onDelete "
				+ paramObject);

	}

	@Override
	public void onCollectionRecreate(Object paramObject,
			Serializable paramSerializable) throws CallbackException {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName() + " onCollectionRecreate "
				+ paramObject);

	}

	@Override
	public void onCollectionRemove(Object paramObject,
			Serializable paramSerializable) throws CallbackException {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName() + " onCollectionRemove "
				+ paramObject);

	}

	@Override
	public void onCollectionUpdate(Object paramObject,
			Serializable paramSerializable) throws CallbackException {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName() + " onCollectionUpdate "
				+ paramObject);

	}

	@Override
	public void preFlush(Iterator paramIterator) throws CallbackException {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName() + " preFlush "
				+ paramIterator);

	}

	@Override
	public void postFlush(Iterator paramIterator) throws CallbackException {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName() + " postFlush "
				+ paramIterator);

	}

	@Override
	public Boolean isTransient(Object paramObject) {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName() + " isTransient "
				+ paramObject);
		return null;
	}

	@Override
	public int[] findDirty(Object paramObject, Serializable paramSerializable,
			Object[] paramArrayOfObject1, Object[] paramArrayOfObject2,
			String[] paramArrayOfString, Type[] paramArrayOfType) {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName() + " findDirty "
				+ paramObject);
		return null;
	}

	@Override
	public Object instantiate(String paramString, EntityMode paramEntityMode,
			Serializable paramSerializable) throws CallbackException {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName() + " instantiate "
				+ paramString);
		return null;
	}

	@Override
	public String getEntityName(Object paramObject) throws CallbackException {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName() + " getEntityName "
				+ paramObject);
		return null;
	}

	@Override
	public Object getEntity(String paramString, Serializable paramSerializable)
			throws CallbackException {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName() + " getEntity "
				+ paramString);
		return null;
	}

	@Override
	public void afterTransactionBegin(Transaction paramTransaction) {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName() + " afterTransactionBegin "
				+ paramTransaction);

	}

	@Override
	public void beforeTransactionCompletion(Transaction paramTransaction) {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName()
				+ " beforeTransactionCompletion " + paramTransaction);

	}

	@Override
	public void afterTransactionCompletion(Transaction paramTransaction) {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName()
				+ " afterTransactionCompletion " + paramTransaction);

	}

	@Override
	public String onPrepareStatement(String paramString) {
		System.out.println(Thread.currentThread()
				+ LoggerInterceptor.class.getName() + " onPrepareStatement "
				+ paramString);
		return null;
	}

}
