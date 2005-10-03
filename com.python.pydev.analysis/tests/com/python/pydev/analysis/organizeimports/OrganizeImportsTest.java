/*
 * Created on 25/09/2005
 */
package com.python.pydev.analysis.organizeimports;

import org.eclipse.jface.text.Document;
import org.python.pydev.editor.actions.PySelection;

import com.python.pydev.analysis.IAnalysisPreferences;
import com.python.pydev.analysis.additionalinfo.AdditionalInfoTestsBase;
import com.python.pydev.analysis.ctrl_1.MarkerStub;

public class OrganizeImportsTest extends AdditionalInfoTestsBase{

    private OrganizeImports organizer;

    public static void main(String[] args) {
        junit.textui.TestRunner.run(OrganizeImportsTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        organizer = new OrganizeImports();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testOrganizeImports() throws Exception {
        String s = "import xxx"; //unused import
        Document document = new Document(s);
        MarkerStub stub = createMarkerStub(0, s.length(), IAnalysisPreferences.TYPE_UNUSED_IMPORT);
        
//        organizer.performArrangeImports(new PySelection(document), stub);
        
        
    }
}
