package com.capg.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.entity.Activity;

import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@SpringBootTest
public class ActivityTest {

	
	@Before
	public void initInput() {
		// do that
	}
	
	Integer activityUIN = 2;

	
	@Test()
	public final void testEquals() throws NullPointerException {
		Activity f1 = new Activity();
		assertNotNull(f1);
		Activity f2 = null;
		Activity f3 = new Activity(activityUIN, 5, "Rollercoster");
		Activity f4 = new Activity(activityUIN, 5 ,"Rollercoster");
		assertTrue(f3.equals(f4));
		assertFalse(f3.equals(f2));
	}
	
	@Test()
	public void testActivity() {
		Activity activity = new Activity(activityUIN, 5,"Rollercoster");
		assertEquals(activityUIN, activity.getActivityUIN());
		assertEquals("Rollercoster", activity.getActivityName());
	}
	@Test
	public final void testToString() {
		Activity f1 = new Activity(activityUIN, 5,"Rollercoster");
		String result = String.format("Activity [activityUIN=%s,pnrNumber=%3s,activityName=%5s]",
				f1.getActivityUIN(), f1.getPnrNumber() ,f1.getActivityName() );
		System.out.println(result);
		System.out.println(f1.toString());
		assertEquals(result, f1.toString());
	}
}
