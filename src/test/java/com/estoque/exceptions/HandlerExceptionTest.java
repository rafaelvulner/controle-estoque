package com.estoque.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class HandlerExceptionTest {
    /**
     * Method under test: {@link HandlerException#handleVendaNotFoundException(VendaNotFoundException, WebRequest)}
     */
    @Test
    void testHandleVendaNotFoundException() {
        HandlerException handlerException = new HandlerException();
        VendaNotFoundException vendaNotFoundException = new VendaNotFoundException("An error occurred");
        ResponseEntity<?> actualHandleVendaNotFoundExceptionResult = handlerException
                .handleVendaNotFoundException(vendaNotFoundException, new ServletWebRequest(new MockHttpServletRequest()));
        assertEquals("An error occurred", actualHandleVendaNotFoundExceptionResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleVendaNotFoundExceptionResult.getStatusCode());
        assertTrue(actualHandleVendaNotFoundExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link HandlerException#handleVendaNotFoundException(VendaNotFoundException, WebRequest)}
     */
    @Test
    void testHandleVendaNotFoundException3() {

        HandlerException handlerException = new HandlerException();
        VendaNotFoundException vendaNotFoundException = mock(VendaNotFoundException.class);
        when(vendaNotFoundException.getMessage()).thenReturn("Not all who wander are lost");
        ResponseEntity<?> actualHandleVendaNotFoundExceptionResult = handlerException
                .handleVendaNotFoundException(vendaNotFoundException, new ServletWebRequest(new MockHttpServletRequest()));
        assertEquals("Not all who wander are lost", actualHandleVendaNotFoundExceptionResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleVendaNotFoundExceptionResult.getStatusCode());
        assertTrue(actualHandleVendaNotFoundExceptionResult.getHeaders().isEmpty());
        verify(vendaNotFoundException).getMessage();
    }
}

