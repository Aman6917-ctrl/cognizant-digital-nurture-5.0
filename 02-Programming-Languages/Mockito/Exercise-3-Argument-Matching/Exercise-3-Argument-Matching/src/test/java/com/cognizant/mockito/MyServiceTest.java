package com.cognizant.mockito;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MyServiceTest {
    @Test
    public void testSendDataWithMatchingArgument() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        new MyService(mockApi).sendData("Test Data");
        verify(mockApi).sendData(eq("Test Data"));
    }
}
