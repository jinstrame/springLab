import org.junit.jupiter.api.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lab.model.UsualPerson;
import lab.model.Person;
import lab.model.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {

	private static final String APPLICATION_CONTEXT_CLASSPATH = "application-context.xml";

	private UsualPerson expectedPerson;

	private AbstractApplicationContext context;

	@BeforeEach
	void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_CLASSPATH);
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
