package interface_adapter.sub_menu;

import org.junit.Before;
import org.junit.Test;
import use_case.sub_menu.SubViewInputBoundary;
import use_case.sub_menu.SubViewInputData;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SubViewControllerTest {

    private SubViewInputBoundaryStub subViewInputBoundaryStub;
    private SubViewController subViewController;

    @Before
    public void setUp() {
        subViewInputBoundaryStub = new SubViewInputBoundaryStub();
        subViewController = new SubViewController(subViewInputBoundaryStub);
    }

    @Test
    public void testExecute() {
        // Arrange
        String subViewCriteria = "recommendation";

        // Act
        subViewController.execute(subViewCriteria);

        // Assert
        assertTrue(subViewInputBoundaryStub.executeCalled);
        assertNotNull(subViewInputBoundaryStub.lastExecuteSubViewData);
        // You can add more assertions based on your specific requirements
    }

    // Stub class for SubViewInputBoundary
    private static class SubViewInputBoundaryStub implements SubViewInputBoundary {
        boolean executeCalled = false;
        SubViewInputData lastExecuteSubViewData;

        @Override
        public void execute(SubViewInputData subViewInputData) {
            executeCalled = true;
            lastExecuteSubViewData = subViewInputData;
        }
    }
}
