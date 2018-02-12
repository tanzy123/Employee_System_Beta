package com.beta.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldCopyUtil {
	public static <T> void setFields(T from, T to) {

		Class<?> fromClass = from.getClass();
		Class<?> toClass = to.getClass();

		while (fromClass.getSuperclass() != null) {
			Field[] declaredFields = fromClass.getDeclaredFields();
			List<Field> nonStaticFields = new ArrayList<Field>();

			for (Field field : declaredFields) {
				if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())
						|| !java.lang.reflect.Modifier.isFinal(field.getModifiers())) {
					nonStaticFields.add(field);
				}
			}
			for (Field field : nonStaticFields) {
				try {
					Field fieldFrom = fromClass.getDeclaredField(field.getName());
					fieldFrom.setAccessible(true);
					Object value = fieldFrom.get(from);
					if (value != null) {
						Field fieldTo = toClass.getDeclaredField(field.getName());
						fieldTo.setAccessible(true);
						fieldTo.set(to, value);
						// to.getClass().getDeclaredField(field.getName()).set(to, value);
					}

				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				}
			}
			
			fromClass = fromClass.getSuperclass();
			toClass = toClass.getSuperclass();
		}
	}
}
