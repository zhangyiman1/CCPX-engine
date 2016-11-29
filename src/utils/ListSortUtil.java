package utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * sort function of list object
 * 
 * @param <E>
 */
public class ListSortUtil<E> {
	/**
	 * 
	 * @param list list collection
	 * @param method the get method of sort attribute
	 * @param sort desc or asc 
	 */
	public void Sort(List<E> list, final String method, final String sort) {
		// 用内部类实现排序
		Collections.sort(list, new Comparator<E>() {

			public int compare(E a, E b) {
				int ret = 0;
				try {
					// 获取m1的方法名
					Method m1 = a.getClass().getMethod(method, null);
					// 获取m2的方法名
					Method m2 = b.getClass().getMethod(method, null);
					
					if (sort != null && "desc".equals(sort)) {

						ret = m2.invoke(((E)b), null).toString().compareTo(m1.invoke(((E)a),null).toString());

					} else {
						// sort asc
						ret = m1.invoke(((E)a), null).toString().compareTo(m2.invoke(((E)b), null).toString());
					}
				} catch (NoSuchMethodException ne) {
					System.out.println(ne);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return ret;
			}
		});
	}
}