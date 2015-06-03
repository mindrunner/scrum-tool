import de.mindrunner.scrum.base.controller.ScrumController;
import de.mindrunner.scrum.base.exception.InvalidOperationException;
import de.mindrunner.scrum.base.model.UserStory;
import de.mindrunner.scrum.simple.factory.SimpleScrumFactory;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Lukas Elsner [open@mindrunner.de]
 * @version 1.0
 * @since 03-06-2015
 * <p>
 *
 */
public class SimpleScrumControllerTests {
    SimpleScrumFactory scrumFactory = new SimpleScrumFactory();

    @Test(expected = InvalidOperationException.class)
    public void createTaskForNonExistingStory () throws InvalidOperationException {
        ScrumController scrumController = scrumFactory.createNonPersistentScrumController();
        scrumController.createTask("Test1", "Test1", "Test1");
    }


    @Test
    public void listStories () throws InvalidOperationException {
        List<String> expectedList = new LinkedList<>();
        ScrumController scrumController = scrumFactory.createNonPersistentScrumController();
        scrumController.createStory("Story1", "Story");
        scrumController.createStory("Story2", "Story");
        scrumController.createStory("Story3", "Story");
        scrumController.createStory("Story4", "Story");
        scrumController.createStory("Story5", "Story");
        expectedList.add("Story1 Story");
        expectedList.add("Story2 Story");
        expectedList.add("Story3 Story");
        expectedList.add("Story4 Story");
        expectedList.add("Story5 Story");
        List<String> stories = scrumController.listStories();
        stories.sort(null);
        assertThat(stories, is(expectedList));
    }

    @Test
    public void testUserStory() {
        UserStory userStory = scrumFactory.createUserStory("Test1");
        assertEquals(userStory.getDescription(), "Test1");
        assertTrue(userStory.getTaskIds().isEmpty());
        userStory.setDescription("Test2");
        assertEquals(userStory.getDescription(), "Test2");
        assertFalse(userStory.containsTask("NotExist"));
    }

}

