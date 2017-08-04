package org.blockzter.mqservice.utils;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mblock2 on 4/27/17.
 *
 * Copy bean properties w/ options for skipping nulls values and empty strings.
 *
 */
public class BeanCopy extends BeanUtilsBean {
	private boolean copyNulls = true;
	private boolean copyBlankStrings = true;
	private List<String> ignoreList;
	private List<String> explicitList;

	public BeanCopy() {}

	/**
	 *
	 * @param copyNulls copy nulls or not
	 * @param copyBlankStrings copy blank strings or not
	 */
	public BeanCopy(boolean copyNulls, boolean copyBlankStrings) {
		this.copyNulls = copyNulls;
		this.copyBlankStrings = copyBlankStrings;
	}

	/**
	 * Copy the individual property.
	 *
	 * @param copyNulls copy nulls or not
	 * @param copyBlankStrings copy blank strings or not
	 * @param propertiesToignore properties to be ignored
	 * @param explicitProperties only these proprerties
	 */
	public BeanCopy(boolean copyNulls, boolean copyBlankStrings, String[] propertiesToignore, String[] explicitProperties) {
		this.copyNulls = copyNulls;
		this.copyBlankStrings = copyBlankStrings;
		this.ignoreList = (propertiesToignore != null ? Arrays.asList(propertiesToignore) : null);
		this.explicitList = (explicitProperties != null ? Arrays.asList(explicitProperties) : null);
	}

	/**
	 * Copy the individual property.
	 *
	 * @param bean
	 * @param name
	 * @param value
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Override
	public void copyProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException {
		if (explicitList != null) {
			if (!explicitList.contains(name)) return;
		} else if (ignoreList != null) {
			if (ignoreList.contains(name)) return;
		}

		if (!copyNulls && value == null) return;

		if (!copyBlankStrings && (value instanceof String)) {
			if (StringUtils.isBlank( ((String) value)) ) return;
		}

		super.copyProperty(bean, name, value);
	}
}
