package com.springboot.sorting;

import java.util.HashMap;
import java.util.Map;

import com.springboot.entity.Customer;

public class SortUtils {
	private SortUtils() {
	}

	/**
	 * Key:   URL Sort Parameter
	 * Value: Table Column
	 */
	public static Map<String, String> CUSTOMER_ORDER_MAP = new HashMap<String, String>() {
		private static final long serialVersionUID = 4293312786696007788L;

		{
			put("orderByFirstName", Customer.FIRST_NAME);
			put("orderByLastName", Customer.LAST_NAME);
			put("orderByEmail", Customer.EMAIL);
		}
	};
}