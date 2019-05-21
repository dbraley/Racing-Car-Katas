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
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(Alarm.LowPressureThreshold);

        alarm.check();
        assertEquals(false, alarm.isAlarmOn());
    }

    @Test
    public void testAlarmOffWhenPressureUpperBoundary() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(Alarm.HighPressureThreshold);

        alarm.check();
        assertEquals(false, alarm.isAlarmOn());
    }

    @Test
    public void testAlarmOffWhenPressureBelowBottomBoundary() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(Alarm.LowPressureThreshold - .1);

        alarm.check();
        assertEquals(true, alarm.isAlarmOn());
    }

    @Test
    public void testAlarmOffWhenPressureAboveBottomBoundary() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(Alarm.LowPressureThreshold + .1);

        alarm.check();
        assertEquals(false, alarm.isAlarmOn());
    }

    @Test
    public void testAlarmOffWhenPressureAboveUpperBoundary() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(Alarm.HighPressureThreshold + .1);

        alarm.check();
        assertEquals(true, alarm.isAlarmOn());
    }

    @Test
    public void testAlarmOffWhenPressureBelowUpperBoundary() {
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(Alarm.HighPressureThreshold - .1);

        alarm.check();
        assertEquals(false, alarm.isAlarmOn());
    }

}
