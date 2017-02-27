import org.junit.jupiter.api.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import lab.model.UsualPerson;
import lab.model.Person;
import lab.model.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {

	protected static final String APPLICATION_CONTEXT_XML_FILE_NAME = "src/main/resources/application-context.xml";

	private UsualPerson expectedPerson;

	private AbstractApplicationContext context;

	@BeforeEach
	void setUp() throws Exception {
		context = new FileSystemXmlApplicationContext(APPLICATION_CONTEXT_XML_FILE_NAME);
		expectedPerson = getExpectedPerson();
	}

	@Test
	void testInitPerson() {
		Person person = context.getBean("person", Person.class);
		assertEquals(expectedPerson, person);
		System.out.println(person);
	}

	private UsualPerson getExpectedPerson() {
		return new UsualPerson(
				"John Smith",
				new Country(1, "Russia", "RU"),
				35);
	}

	@AfterEach
	void tearDown() throws Exception {
		if (context != null)
			context.close();
	}
}
