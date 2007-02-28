package org.python.pydev.refactoring.tests.visitors;

import java.util.Iterator;

import org.eclipse.jface.text.Document;
import org.python.pydev.refactoring.ast.adapters.ClassDefAdapter;
import org.python.pydev.refactoring.ast.adapters.FunctionDefAdapter;
import org.python.pydev.refactoring.ast.adapters.ModuleAdapter;
import org.python.pydev.refactoring.ast.adapters.SimpleAdapter;
import org.python.pydev.refactoring.ast.visitors.VisitorFactory;
import org.python.pydev.refactoring.ast.visitors.context.ScopeVariablesVisitor;
import org.python.pydev.refactoring.tests.adapter.PythonNatureStub;
import org.python.pydev.refactoring.tests.core.AbstractIOTestCase;

public class ScopeVarVisitorTestCase extends AbstractIOTestCase {

	public ScopeVarVisitorTestCase(String name) {
		super(name);
	}

	@Override
	public void runTest() throws Throwable {
		StringBuffer buffer = new StringBuffer();
		ModuleAdapter module = VisitorFactory.createModuleAdapter(null, null, new Document(getSource()), new PythonNatureStub());

		for (FunctionDefAdapter func : module.getFunctions()) {

			ScopeVariablesVisitor visitor = VisitorFactory.createContextVisitor(ScopeVariablesVisitor.class, func.getASTNode(), module,
					func);

			printAttributes(buffer, visitor, func.getName());
		}
		for (ClassDefAdapter clazz : module.getClasses()) {

			ScopeVariablesVisitor visitor = VisitorFactory.createContextVisitor(ScopeVariablesVisitor.class, clazz.getASTNode(), module,
					clazz);

			printAttributes(buffer, visitor, clazz.getName());
		}

		this.setTestGenerated(buffer.toString().trim());

		assertEquals(getExpected(), getGenerated());
	}

	private void printAttributes(StringBuffer buffer, ScopeVariablesVisitor scopeVisitor, String scopeName) {
		Iterator<SimpleAdapter> iter = scopeVisitor.iterator();
		buffer.append("\n# " + scopeName + " " + scopeVisitor.getAll().size() + "\n");
		while (iter.hasNext()) {
			SimpleAdapter adapter = iter.next();
			buffer.append("## " + adapter.getName() + "\n");
		}
	}

	@Override
	public String getExpected() {
		return getResult();
	}

}