package tddmicroexercises.tirepressuremonitoringsystem;


import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TestAlarm {

    @Mock
    Sensor sensor;

    @InjectMocks
    Alarm alarm;

    @Test
    public void testAlarmInitializedToFalse() {
        Alarm alarm = new Alarm();
        assertEquals(false, alarm.isAlarmOn());
    }

    @Test
    public void testAlarmOnWhenPressureTooLow() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn((double) 5);

        alarm.check();
        assertEquals(true, alarm.isAlarmOn());
    }

    @Test
    public void testAlarmOnWhenPressureTooHigh() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn((double) 30);

        alarm.check();
        assertEquals(true, alarm.isAlarmOn());
    }

    @Test
    public void testAlarmOffWhenPressureBottomBoundary() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn((double) 17);

        alarm.check();
        assertEquals(false, alarm.isAlarmOn());
    }

    @Test
    public void testAlarmOffWhenPressureUpperBoundary() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn((double) 21);

        alarm.check();
        assertEquals(false, alarm.isAlarmOn());
    }

}
