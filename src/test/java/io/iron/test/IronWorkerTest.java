package io.iron.test;

import io.iron.ironworker.client.entities.*;
import io.iron.ironworker.client.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class IronWorkerTest {
    private String queueName = "java-testing-queue";
    private Client client;

    @Before
    public void setUp() throws Exception {
        client = new Client("WRxxxxxxxxxxxxxxxxxxxxxxZ0c", "52f4419d13a66a0009000004");
    }

    @Test
    public void testCreatingTask() throws IOException, APIException {
        String id = client.createTask("HelloWorkerMono").getId();

        Assert.assertTrue(id.length() > 0);
    }

    @Test
    public void testViewingTaskInfoAfterTaskCreation() throws IOException, APIException {
        TaskEntity task = client.createTask("HelloWorkerMono");

        Assert.assertTrue(task.getId().length() > 0);
        Assert.assertNull(task.getCodeId());
        Assert.assertNull(task.getCodeName());
        Assert.assertNull(task.getStatus());
        Assert.assertNull(task.getPayload());
        Assert.assertNull(task.getCreatedAt());
        Assert.assertNull(task.getUpdatedAt());
    }

    @Test
    public void testGetTaskInfo() throws IOException, APIException, InterruptedException {
        String id = client.createTask("HelloWorkerMono").getId();
        Thread.sleep(1000);
        TaskEntity task = client.getTask(id);

        Assert.assertTrue(task.getId().length() > 0);
        Assert.assertTrue(task.getCodeId().length() > 0);
        Assert.assertTrue(task.getCodeName().length() > 0);
        Assert.assertTrue(task.getStatus().length() > 0);
        Assert.assertTrue(task.getPayload().length() > 0);
        Assert.assertNotNull(task.getCreatedAt());
        Assert.assertNotNull(task.getUpdatedAt());
    }

    @Test
    public void testTaskReloadInfo() throws IOException, APIException, InterruptedException {
        TaskEntity task = client.createTask("HelloWorkerMono");
        Thread.sleep(1000);
        client.reload(task);

        Assert.assertTrue(task.getId().length() > 0);
        Assert.assertTrue(task.getCodeId().length() > 0);
        Assert.assertTrue(task.getCodeName().length() > 0);
        Assert.assertTrue(task.getStatus().length() > 0);
        Assert.assertTrue(task.getPayload().length() > 0);
        Assert.assertNotNull(task.getCreatedAt());
        Assert.assertNotNull(task.getUpdatedAt());
    }

    @Test
    public void testSchedulingTask() throws IOException, APIException {
        String id = client.createSchedule("HelloWorkerMono").getId();

        Assert.assertTrue(id.length() > 0);
    }

    @Test
    public void testSchedulingInfo() throws IOException, APIException, InterruptedException {
        String id = client.createSchedule("HelloWorkerMono").getId();
        Thread.sleep(1000);
        ScheduleEntity schedule = client.getSchedule(id);

        Assert.assertTrue(schedule.getId().length() > 0);
        Assert.assertTrue(schedule.getCodeName().length() > 0);
        Assert.assertTrue(schedule.getStatus().length() > 0);
        Assert.assertTrue(schedule.getPayload().length() > 0);
        Assert.assertNotNull(schedule.getCreatedAt());
        Assert.assertNotNull(schedule.getUpdatedAt());
    }

    @Test
    public void testReloadSchedule() throws IOException, APIException, InterruptedException {
        ScheduleEntity schedule = client.createSchedule("HelloWorkerMono");
        Thread.sleep(1000);
        client.reload(schedule);

        Assert.assertTrue(schedule.getId().length() > 0);
        Assert.assertTrue(schedule.getCodeName().length() > 0);
        Assert.assertTrue(schedule.getStatus().length() > 0);
        Assert.assertTrue(schedule.getPayload().length() > 0);
        Assert.assertNotNull(schedule.getCreatedAt());
        Assert.assertNotNull(schedule.getUpdatedAt());
    }
}
