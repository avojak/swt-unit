package com.thedesertmonk.util.swt.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link WidgetHierarchyInspector}.
 *
 * @author Andrew Vojak
 */
public class WidgetHierarchyInspectorTest {

	private Display display;
	private Shell shell;
	private Composite parent;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		display = new Display();
		shell = new Shell(display);
		parent = new Composite(shell, SWT.NONE);
	}

	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
		display.dispose();
	}

	/**
	 * Tests that {@link WidgetHierarchyInspector#getChildren(Composite, Class)}
	 * throws an exception when the given parent {@link Composite} is
	 * {@code null}.
	 */
	@Test(expected = NullPointerException.class)
	public void testGetChildren_NullParentComposite() {
		WidgetHierarchyInspector.getChildren((Composite) null, Composite.class);
	}

	/**
	 * Tests that {@link WidgetHierarchyInspector#getChildren(Composite, Class)}
	 * throws an exception when the given {@link Class} is {@code null}.
	 */
	@Test(expected = NullPointerException.class)
	public void testGetChildren_NullClass() {
		WidgetHierarchyInspector.getChildren(parent, (Class<Composite>) null);
	}

	/**
	 * Tests {@link WidgetHierarchyInspector#getChildren(Composite, Class)} when
	 * there are no children.
	 */
	@Test
	public void testGetChildren_NoChildren() {
		assertTrue(WidgetHierarchyInspector.getChildren(parent, Control.class).isEmpty());
	}

	/**
	 * Tests {@link WidgetHierarchyInspector#getChildren(Composite, Class)} when
	 * there there is one child.
	 */
	@Test
	public void testGetChildren_OneChild() {
		final Composite child = new Composite(parent, SWT.NONE);

		final List<Composite> children = WidgetHierarchyInspector.getChildren(parent, Composite.class);
		assertEquals(1, children.size());
		assertEquals(child, children.get(0));
	}

	/**
	 * Tests {@link WidgetHierarchyInspector#getChildren(Composite, Class)} when
	 * there are multiple children of the same type.
	 */
	@Test
	public void testGetChildren_MultipleChildren_SameType() {
		final Composite child1 = new Composite(parent, SWT.NONE);
		final Composite child2 = new Composite(parent, SWT.NONE);
		final Composite child3 = new Composite(parent, SWT.NONE);

		final List<Composite> children = WidgetHierarchyInspector.getChildren(parent, Composite.class);
		assertEquals(3, children.size());
		assertEquals(child1, children.get(0));
		assertEquals(child2, children.get(1));
		assertEquals(child3, children.get(2));
	}

	/**
	 * Tests {@link WidgetHierarchyInspector#getChildren(Composite, Class)} when
	 * there are multiple children of different types.
	 */
	@Test
	public void testGetChildren_MultipleChildren_DifferentTypes() {
		final Composite child1 = new Composite(parent, SWT.NONE);
		final Label child2 = new Label(parent, SWT.NONE);
		final Button child3 = new Button(parent, SWT.NONE);
		final Composite child4 = new Composite(parent, SWT.NONE);
		final Text child5 = new Text(parent, SWT.NONE);
		final Button child6 = new Button(parent, SWT.NONE);

		final List<Composite> compositeChildren = WidgetHierarchyInspector.getChildren(parent, Composite.class);
		assertEquals(2, compositeChildren.size());
		assertEquals(child1, compositeChildren.get(0));
		assertEquals(child4, compositeChildren.get(1));

		final List<Label> labelChildren = WidgetHierarchyInspector.getChildren(parent, Label.class);
		assertEquals(1, labelChildren.size());
		assertEquals(child2, labelChildren.get(0));

		final List<Button> buttonChildren = WidgetHierarchyInspector.getChildren(parent, Button.class);
		assertEquals(2, buttonChildren.size());
		assertEquals(child3, buttonChildren.get(0));
		assertEquals(child6, buttonChildren.get(1));

		final List<Text> textChildren = WidgetHierarchyInspector.getChildren(parent, Text.class);
		assertEquals(1, textChildren.size());
		assertEquals(child5, textChildren.get(0));
	}

}
