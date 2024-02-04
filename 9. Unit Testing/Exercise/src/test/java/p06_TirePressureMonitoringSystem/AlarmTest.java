package p06_TirePressureMonitoringSystem;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AlarmTest {

    private static final double NORMAL_PRESSURE_PSI_VALUE = 18.0;
    private static final double LOW_PRESSURE_PSI_VALUE = 14.0;
    private static final double HIGH_PRESSURE_PSI_VALUE = 25.0;

    private Sensor sensor;
    private Alarm alarm;

    @Before
    public void setUp() {
        this.sensor = mock(Sensor.class);
        this.alarm = new Alarm(sensor);
    }

    @Test
    public void testAlarmOffAtPressureInRange() {
        when(this.sensor.popNextPressurePsiValue()).thenReturn(NORMAL_PRESSURE_PSI_VALUE);

        this.alarm.check();

        assertFalse(this.alarm.getAlarmOn());
    }

    @Test
    public void testAlarmOnAtLowPressure() {
        when(this.sensor.popNextPressurePsiValue()).thenReturn(LOW_PRESSURE_PSI_VALUE);

        this.alarm.check();

        assertTrue(this.alarm.getAlarmOn());
    }

    @Test
    public void testAlarmOnAtHighPressure() {
        when(this.sensor.popNextPressurePsiValue()).thenReturn(HIGH_PRESSURE_PSI_VALUE);

        this.alarm.check();

        assertTrue(this.alarm.getAlarmOn());
    }
}
