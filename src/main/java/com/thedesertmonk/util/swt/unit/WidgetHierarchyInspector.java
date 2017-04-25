package com.thedesertmonk.util.swt.unit;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Provides methods to inspect widget hierarchies.
 *
 * @author Andrew Vojak
 */
@SuppressWarnings("nls")
public class WidgetHierarchyInspector {

	/**
	 * Retrieves all children of the given {@link Composite} which are instances
	 * of the given class.
	 *
	 * @param <T> The type of the children to retrieve. Must be a subclass of
	 *            {@link Control}.
	 *
	 * @param parent The parent {@link Composite} for which child {@link Control
	 *            Controls} will be retrieved. Cannot be null.
	 * @param clazz The {@link Class} for which all retrieved child
	 *            {@link Control Controls} will be instances of. Cannot be null.
	 * @return The non-null, possibly empty {@code List} of {@link Control
	 *         Controls}.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Control> List<T> getChildren(final Composite parent, final Class<T> clazz) {
		checkNotNull(parent, "parent cannot be null");
		checkNotNull(clazz, "clazz cannot be null");

		final List<T> children = new ArrayList<T>();
		for (final Control control : parent.getChildren()) {
			if (clazz.isInstance(control)) {
				children.add((T) control);
			}
		}
		return children;
	}

}
