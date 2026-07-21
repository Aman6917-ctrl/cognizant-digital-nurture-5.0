package com.cognizant.mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MyServiceTest {
    @Test
    public void testVoidMethodThrowsException() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        doThrow(new RuntimeException("API Error")).when(mockApi).sendData(anyString());
        MyService service = new MyService(mockApi);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> service.sendData("Test Data"));

        verify(mockApi).sendData("Test Data");
    }
}
